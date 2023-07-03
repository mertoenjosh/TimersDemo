package com.mertoenjosh.timersdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private lateinit var tvSeconds: TextView
    private  lateinit var btnStart: Button
    private  lateinit var btnPause: Button
    private  lateinit var btnReset: Button
    private  lateinit var toolbar: Toolbar

    // variable for timer which will be initiated later
    private var countDownTimer: CountDownTimer? = null
    // The duration of the timer in milliseconds
    private var timerDuration: Long = 60000
    // pauseOffset = timerDuration - time left
    private var pauseOffset: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar_timer)

        setSupportActionBar(toolbar)
        val actionbar = supportActionBar

        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(false)
        }

        tvSeconds = findViewById(R.id.tvSeconds)
        btnStart = findViewById(R.id.btnStart)
        btnPause = findViewById(R.id.btnPause)
        btnReset = findViewById(R.id.btnReset)

        tvSeconds.text = "${(timerDuration/1000)}"

        btnStart.setOnClickListener {
            startTimer(pauseOffset)
        }

        btnPause.setOnClickListener {
            pauseTimer()
        }

        btnReset.setOnClickListener {
            resetTimer()
        }

    }

    private fun startTimer(pauseOffsetL: Long) {
        countDownTimer = object : CountDownTimer(timerDuration - pauseOffsetL, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timerDuration - millisUntilFinished
                tvSeconds.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Timer finished", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
        }
    }

    private fun resetTimer() {
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
            tvSeconds.text = "${timerDuration/1000}"
            countDownTimer = null
            pauseOffset = 0
        }
    }

}