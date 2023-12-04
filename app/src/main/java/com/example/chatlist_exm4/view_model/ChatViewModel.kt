package com.example.chatlist_exm4.view_model

import android.text.Editable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatlist_exm4.model.Chat
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private var _chatFlow = MutableSharedFlow<List<Chat>>()
    val chatFlow get() = _chatFlow.asSharedFlow()

    fun search(owner: Editable) {
        viewModelScope.launch {
            _chatFlow.emit(
                listOf<Chat>().filter {
                    it.owner.contains(owner, ignoreCase = true)
                }
            )
        }
    }
}