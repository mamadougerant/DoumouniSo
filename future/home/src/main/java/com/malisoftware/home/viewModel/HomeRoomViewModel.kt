package com.malisoftware.home.viewModel

import androidx.lifecycle.ViewModel
import com.malisoftware.local.repository.LocalRepository
import javax.inject.Inject

class HomeRoomViewModel @Inject constructor(
    private val roomDb: LocalRepository
) : ViewModel(){

}