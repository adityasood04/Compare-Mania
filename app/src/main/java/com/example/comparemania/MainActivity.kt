package com.example.comparemania

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comparemania.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            if(binding.etRounds.text.toString() == "" || binding.etRounds.text == null) {
                binding.etRounds.error = "Required"
            } else {
                val i = Intent(this,GameActivity::class.java)
                i.putExtra("ROUNDS", binding.etRounds.text.toString())
                startActivity(i)
            }
        }
    }
}