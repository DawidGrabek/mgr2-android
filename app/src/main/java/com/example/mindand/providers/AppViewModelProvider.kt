package com.example.mindand.providers

//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewmodel.CreationExtras
//import androidx.lifecycle.viewmodel.initializer
//import androidx.lifecycle.viewmodel.viewModelFactory
//import com.example.mindand.MasterAndApplication
//import com.example.mindand.view_models.GameViewModel
//import com.example.mindand.view_models.ProfileViewModel
//import com.example.mindand.view_models.ResultsViewModel
//
//object AppViewModelProvider {
//    val Factory = viewModelFactory {
//        initializer {
//            ProfileViewModel(masterAndApplication().container.playersRepository)
//        }
//        initializer {
//            GameViewModel(
//                masterAndApplication().container.playersRepository,
//                masterAndApplication().container.scoresRepository
//            )
//        }
//        initializer {
//            ResultsViewModel(masterAndApplication().container.playerScoresRepository)
//        }
//    }
//}
//
//fun CreationExtras.masterAndApplication(): MasterAndApplication =
//    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MasterAndApplication)