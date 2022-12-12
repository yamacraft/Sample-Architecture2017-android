package io.github.yamacraft.app.sample.architecture2017.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.yamacraft.app.sample.architecture2017.repository.GithubRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private var _repositoriesLiveData = MutableLiveData<List<String>>()
    val repositoriesLiveData: LiveData<List<String>> = _repositoriesLiveData

    fun getRepositories(organizationName: String) {
        viewModelScope.launch {
            val result = githubRepository.getOrganizationRepositories(organizationName)
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _repositoriesLiveData.value = it
                }
            }
        }
    }
}
