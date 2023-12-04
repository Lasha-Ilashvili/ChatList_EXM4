package com.example.chatlist_exm4.fragment

import android.text.Editable
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.chatlist_exm4.adapter.ChatItemAdapter
import com.example.chatlist_exm4.base.BaseFragment
import com.example.chatlist_exm4.databinding.FragmentChatListBinding
import com.example.chatlist_exm4.json_reader.getJsonDataFromAsset
import com.example.chatlist_exm4.model.Chat
import com.example.chatlist_exm4.view_model.ChatViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class ChatListFragment : BaseFragment<FragmentChatListBinding>(FragmentChatListBinding::inflate) {

    private val chatViewModel: ChatViewModel by viewModels()

    private lateinit var adapter: ChatItemAdapter

    private lateinit var chats: List<Chat>

//    override fun setData() {
//        chatViewModel.chatFlow = getData()
//    }

    private fun getData(): SharedFlow<List<Chat>> {
        val json = getJsonDataFromAsset(requireContext(), "chats.json")

        return Gson().fromJson(json, object : TypeToken<List<Chat>>() {}.type)
    }

    override fun setRecycler() {
        adapter = ChatItemAdapter().apply {
            submitList(chats)
        }
        binding.rvChatList.adapter = adapter
    }


    private fun setUpSearch() {
        binding.etSearch.addTextChangedListener { owner: Editable? ->
            owner?.let { chatViewModel.search(it) }
        }
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                chatViewModel.chatFlow.collect {
                    adapter.submitList(it)
                }
            }
        }
    }
}