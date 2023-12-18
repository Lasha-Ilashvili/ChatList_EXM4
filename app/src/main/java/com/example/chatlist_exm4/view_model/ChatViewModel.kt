package com.example.chatlist_exm4.view_model

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.example.chatlist_exm4.model.Chat
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel : ViewModel() {

    private val _chatFlow = MutableStateFlow(emptyList<Chat>())
    val chatFlow get() = _chatFlow.asStateFlow()

    private lateinit var json: String

    fun setInitialList(newJson: String) {
        json = newJson
        _chatFlow.value = getData()
    }

    private fun getData(): List<Chat> {
        val type = Types.newParameterizedType(
            List::class.java,
            Chat::class.java
        )

        val moshi = Moshi.Builder().build()

        val adapter = moshi.adapter<List<Chat>>(type)

        return adapter.fromJson(json) ?: emptyList()
    }

    fun search(owner: Editable) {
        _chatFlow.value = getData().filter {
            it.owner.contains(owner, ignoreCase = true)
        }
    }
}