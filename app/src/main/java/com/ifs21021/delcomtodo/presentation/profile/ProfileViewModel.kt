package com.ifs21021.delcomtodo.presentation.profile

import com.ifs21021.delcomtodo.data.remote.MyResult
import com.ifs21021.delcomtodo.data.remote.response.DataUserResponse
import com.ifs21021.delcomtodo.data.repository.AuthRepository
import com.ifs21021.delcomtodo.data.repository.UserRepository
import com.ifs21021.delcomtodo.presentation.ViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProfileViewModel(
  private val authRepository: AuthRepository,
  private val userRepository: UserRepository
) : ViewModel() {

  fun logout() {
    viewModelScope.launch {
      authRepository.logout()
    }
  }

  fun getMe(): LiveData<MyResult<DataUserResponse>> {
    return userRepository.getMe().asLiveData()
  }

  companion object {
    @Volatile
    private var INSTANCE: ProfileViewModel? = null
    fun getInstance(
      authRepository: AuthRepository,
      userRepository: UserRepository
    ): ProfileViewModel {
      synchronized(ViewModelFactory::class.java) {
        INSTANCE = ProfileViewModel(
          authRepository,
          userRepository
        )
      }
      return INSTANCE as ProfileViewModel
    }
  }
}