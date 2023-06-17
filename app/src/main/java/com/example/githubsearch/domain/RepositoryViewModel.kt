package com.example.githubsearch.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearch.ui.models.RepositoryOnListUIModel
import com.example.githubsearch.ui.models.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val getUserRepositoriesUseCase: IGetUserRepositoriesUseCase
): ViewModel() {

    private val _repositoriesList = MutableLiveData<UIState<List<RepositoryOnListUIModel>>>()
    val repositoriesList: LiveData<UIState<List<RepositoryOnListUIModel>>>
        get() = _repositoriesList

   fun loadUserRepositories(username: String){
       viewModelScope.launch {
           _repositoriesList.value = UIState.Loading
           getUserRepositoriesUseCase.execute(username)
               .catch {
                   _repositoriesList.value = UIState.Error(cause = it)
               }
               .collect{
                   _repositoriesList.value = UIState.Success(it)
               }
       }
   }
}