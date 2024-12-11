package com.example.mindand.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mindand.db.entities.Player
import com.example.mindand.db.repositories.PlayersRepository

class ProfileViewModel (private var playersRepository: PlayersRepository) : ViewModel() {
    var id = mutableStateOf(0L)
    val name = mutableStateOf("")
    val email = mutableStateOf("")

    suspend fun savePlayer() {
        val players = playersRepository.getPlayersByEmail(email.value)
        var player: Player
        if (players.isEmpty()) {
            player = Player(
                name = name.value,
                email = email.value
            )
            val playerId = playersRepository.insertPlayer(player)
            player = playersRepository.getPlayerById(playerId)!!
        } else {
            player = players.first()
            player = player.copy(name = name.value)
            playersRepository.update(player)
        }
        playersRepository.setCurrentPlayerId(player.playerId)
//        id.value = player.playerId
    }
}