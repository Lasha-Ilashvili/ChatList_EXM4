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
import com.example.chatlist_exm4.view_model.ChatViewModel
import kotlinx.coroutines.launch

class ChatListFragment : BaseFragment<FragmentChatListBinding>(FragmentChatListBinding::inflate) {

    private val chatViewModel: ChatViewModel by viewModels()

    private lateinit var adapter: ChatItemAdapter


    override fun setData() {
        val json = getJsonDataFromAsset(requireContext(), "chats.json")!!
        chatViewModel.setInitialList(json)
    }

    override fun setRecycler() {
        adapter = ChatItemAdapter()
        binding.rvChatList.adapter = adapter
    }

    override fun setListeners() {
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