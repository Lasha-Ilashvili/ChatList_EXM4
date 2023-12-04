package com.example.anagramclusterizer_exm2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anagramclusterizer_exm2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val clusters = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        saveButtonPressed()
        outputButtonPressed()
    }

    private fun saveButtonPressed() {
        binding.saveButton.setOnClickListener {
            val anagram = binding.etAnagram.text.toString()
            if (anagram.isNotEmpty()) {
                saveAnagrams(anagram)
            }
        }
    }

    private fun outputButtonPressed() {
        binding.outputButton.setOnClickListener {
            outputClusters()
        }
    }

    private fun saveAnagrams(word: String) {
        val sortedWord = word.toCharArray().sorted().toString()
        clusters.add(sortedWord)
    }

    private fun outputClusters() {
        binding.tvClusterCount.text = clusters.size.toString()
    }
}