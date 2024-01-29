package com.example.comparemania

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.comparemania.databinding.ActivityGameBinding
import java.util.Random

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private var numberOne: Int = 0
    private var numberTwo: Int = 0
    private var score: Int = 0
    private var rounds: Int = 0
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rounds = Integer.parseInt(intent.getStringExtra("ROUNDS")!!)
        generateNumbers()

    }

    private fun generateNumbers() {
        numberOne = Random().nextInt(999)
        numberTwo = Random().nextInt(999)
        rounds--
        if (rounds < 0) {
            showResult()
        } else setUpGame()
    }

    private fun setUpGame() {
        binding.tvFirstNumber.text = numberOne.toString()
        binding.tvSecondNumber.text = numberTwo.toString()
        binding.tvScore.text = score.toString()
        binding.tvRounds.text = "Remaining Rounds :  ${(rounds + 1).toString()}"
        setTimer()
        setListeners()
    }

    private fun setTimer() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(10000, 10) {
            override fun onTick(p0: Long) {
                binding.pb.progress = (p0 / 100).toInt()
            }
            override fun onFinish() {
                resetTimer()
            }
        }.start()
    }

    private fun resetTimer() {
        binding.pb.progress = 10
        countDownTimer?.cancel()
        generateNumbers()
    }

    private fun setListeners() {
        if (numberOne == numberTwo) {
            binding.btnLess.setOnClickListener {
                resetTimer()
            }
            binding.btnEqual.setOnClickListener {
                score++
                resetTimer()
            }
            binding.btnGreater.setOnClickListener {
                resetTimer()
            }
        } else if (numberOne < numberTwo) {
            binding.btnLess.setOnClickListener {
                score++
                resetTimer()
            }
            binding.btnEqual.setOnClickListener {
                resetTimer()
            }
            binding.btnGreater.setOnClickListener {
                resetTimer()
            }
        } else {
            binding.btnLess.setOnClickListener {
                resetTimer()
            }
            binding.btnEqual.setOnClickListener {
                resetTimer()
            }
            binding.btnGreater.setOnClickListener {
                score++
                resetTimer()
            }
        }

    }

    private fun showResult() {
        binding.llGame.visibility = View.GONE
        binding.llResult.visibility = View.VISIBLE
        binding.pb.visibility = View.INVISIBLE
        binding.tvScore.text = score.toString()
        binding.tvRounds.text = "Game Over!!"
        binding.tvResult.text = "Well played. Your Score is ${score}."
        binding.btnPlayAgain.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}