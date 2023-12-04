package com.example.tictactoe_exm3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoe_exm3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartGame.setOnClickListener {
            setUpBoard()

        }
    }

    private fun setUpBoard() {
        val boardSize = binding.etBoardSize.text.toString().toIntOrNull()
        boardSize?.let {
            if (it in 3..5) {
                startActivity(
                    Intent(this, GameActivity::class.java)
                        .putExtra("size", it)
                )
            }
        }
    }
}