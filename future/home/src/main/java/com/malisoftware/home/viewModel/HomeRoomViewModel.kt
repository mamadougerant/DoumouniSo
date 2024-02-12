package com.malisoftware.home.viewModel

import androidx.lifecycle.ViewModel
import com.malisoftware.local.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeRoomViewModel @Inject constructor(
    private val roomDb: LocalRepository
) : ViewModel(){

}