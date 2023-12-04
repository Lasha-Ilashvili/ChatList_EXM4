package com.example.tictactoe_exm3

import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe_exm3.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var board: Array<Array<Position>>
    private var turn: Position = Position.Cross

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val boardSize = intent.getIntExtra("size", 3)
        initializeBoard(boardSize)
        setupButtons()
    }

    private fun initializeBoard(size: Int) {
        board = Array(size) { Array(size) { Position.Neither } }
    }

    private fun setupButtons() {
        for (i in 0 until board.size) {
            for (j in 0 until board[i].size) {
                val button = createButton(i, j)
                binding.gridLayout.addView(button)
            }
        }
    }

    private fun createButton(row: Int, col: Int): ImageButton {
        val button = ImageButton(this)
        val params = GridLayout.LayoutParams()
        params.rowSpec = GridLayout.spec(row)
        params.columnSpec = GridLayout.spec(col)
        button.layoutParams = params
        button.setImageResource(R.drawable.ic_launcher_background) // Set your empty image resource

        button.setOnClickListener {
            onButtonClick(row, col, button)
        }

        return button
    }

    private fun onButtonClick(row: Int, col: Int, button: ImageButton) {
        if (board[row][col] == Position.Neither) {
            board[row][col] = turn
            button.setImageResource(if (turn == Position.Cross) R.drawable.ic_x else R.drawable.ic_o)
            checkGameStatus()
            toggleTurn()
        }
    }

    private fun toggleTurn() {
        turn = if (turn == Position.Cross) Position.Nought else Position.Cross
    }

    private fun checkGameStatus() {
        // Implement your logic to check for a winner or a draw
    }

    // Position.kt
    enum class Position {
        Cross,
        Nought,
        Neither
    }
}