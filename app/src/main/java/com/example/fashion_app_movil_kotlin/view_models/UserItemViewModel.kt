package com.example.fashion_app_movil_kotlin.view_models

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fashion_app_movil_kotlin.database.item.Item
import com.example.fashion_app_movil_kotlin.database.item.ItemDAO
import com.example.fashion_app_movil_kotlin.database.user_item.UserItem
import com.example.fashion_app_movil_kotlin.database.useritem.UserItemDAO
import com.example.fashion_app_movil_kotlin.events.ItemEvent
import com.example.fashion_app_movil_kotlin.events.UserItemEvent
import com.example.fashion_app_movil_kotlin.states.ItemState
import com.example.fashion_app_movil_kotlin.states.UserItemState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class UserItemViewModel(
    private val userItemDao: UserItemDAO,
    private val context: Context
) : ViewModel() {
    private val _userItem = userItemDao.getAllUserItem()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(UserItemState())

    val state = combine(_state, _userItem) { state, userItem ->
        state.copy(
            userItem = userItem
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserItemState())

    fun onEvent(event: UserItemEvent) {
        when (event) {
            is UserItemEvent.SaveUserItem -> {
                viewModelScope.launch {
                    val userId = _state.value.userId
                    val itemId = _state.value.itemId

                    val userItem = UserItem(
                        userId = userId,
                        itemId = itemId
                    )

                    userItemDao.upsertUserItem(userItem)

                    _state.update {
                        it.copy(
                            userId = 0,
                            itemId = 0
                        )
                    }
                }
            }

            is UserItemEvent.DeleteUserItem -> {
                viewModelScope.launch {
                    userItemDao.deleteUserItem(event.userItem)
                }
            }

            is UserItemEvent.SetUserId -> {
                _state.update {
                    it.copy(
                        userId = event.userId
                    )
                }
            }

            is UserItemEvent.SetItemId -> {
                _state.update {
                    it.copy(
                        itemId = event.itemId
                    )
                }
            }
        }
    }


    fun addItemToUser(userId: Int, itemId: Int) {
        viewModelScope.launch {
            userItemDao.upsertUserItem(UserItem(userId = userId, itemId = itemId))
        }
    }
    fun getUserItems(userId: Int): LiveData<List<UserItem>> {
        return userItemDao.getUserItems(userId).asLiveData()
    }
}
