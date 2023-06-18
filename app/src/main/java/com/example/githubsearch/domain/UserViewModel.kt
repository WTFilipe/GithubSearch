package com.example.githubsearch.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearch.ui.models.UIState
import com.example.githubsearch.ui.models.UserOnListUIModel
import com.example.githubsearch.ui.models.UserUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val getFavoriteUsersListUseCase: IGetFavoriteUsersListUseCase,
    private val getAddFavoriteUserUseCase: IAddFavoriteUserUseCase,
    private val getDeleteFavoriteUserUseCase: IRemoveFavoriteUserUseCase,
): ViewModel() {

    private val _usersList = MutableLiveData<UIState<List<UserOnListUIModel>>>()
    val usersList: LiveData<UIState<List<UserOnListUIModel>>>
        get() = _usersList

    private val _favoriteUsersList = MutableLiveData<UIState<List<UserOnListUIModel>>>()
    val favoriteUsersList: LiveData<UIState<List<UserOnListUIModel>>>
        get() = _favoriteUsersList

    private val _user = MutableLiveData<UIState<UserUIModel>>()
    val user: LiveData<UIState<UserUIModel>>
        get() = _user

    private val _addFavoriteSuccess = MutableLiveData<UIState<Unit>>()
    val addFavoriteSuccess: LiveData<UIState<Unit>>
        get() = _addFavoriteSuccess

    private val _removeFavoriteSuccess = MutableLiveData<UIState<Unit>>()
    val removeFavoriteSuccess: LiveData<UIState<Unit>>
        get() = _removeFavoriteSuccess

    fun loadUserDetail(userName: String){
        viewModelScope.launch {
            _user.value = UIState.Loading
            getUserDetailUseCase.execute(userName)
                .catch {
                    _user.value = UIState.Error(cause = it)
                }
                .collect{
                    _user.value = UIState.Success(it)
                }
        }
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

    fun loadFavoriteUserList() {
        viewModelScope.launch {
            _favoriteUsersList.value = UIState.Loading
            getFavoriteUsersListUseCase.execute()
                .catch {
                    _favoriteUsersList.value = UIState.Error(cause = it)
                }
                .collect{
                    _favoriteUsersList.value = UIState.Success(it)
                }
        }
    }

    fun addFavoriteUser(userUIModel: UserUIModel){
        viewModelScope.launch {
            _addFavoriteSuccess.value = UIState.Loading
            getAddFavoriteUserUseCase.execute(userUIModel)
                .catch {
                    _addFavoriteSuccess.value = UIState.Error()
                }
                .collect{
                    _addFavoriteSuccess.value = UIState.Success(Unit)
                }

        }
    }

    fun deleteFavoriteUser(userUIModel: UserUIModel){
        viewModelScope.launch {
            _removeFavoriteSuccess.value = UIState.Loading
            getDeleteFavoriteUserUseCase.execute(userUIModel)
                .catch {
                    _removeFavoriteSuccess.value = UIState.Error()
                }
                .collect{
                    _removeFavoriteSuccess.value = UIState.Success(Unit)
                }
        }
    }
}