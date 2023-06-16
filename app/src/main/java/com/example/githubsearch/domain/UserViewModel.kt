package com.example.githubsearch.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearch.ui.models.UIState
import com.example.githubsearch.ui.models.UserOnListUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListGetUserListUseCase
): ViewModel() {

    private val _usersList = MutableLiveData<UIState<List<UserOnListUIModel>>>()
    val usersList: LiveData<UIState<List<UserOnListUIModel>>>
        get() = _usersList

    fun loadUserDetail(userName: String){
    }

    fun loadUserList(){
        viewModelScope.launch {
            _usersList.value = UIState.Loading
            getUserListUseCase.execute()
                .catch {
                    _usersList.value = UIState.Error(cause = it)
                }
                .collect{
                   _usersList.value = UIState.Success(it)
                }
        }
    }
}