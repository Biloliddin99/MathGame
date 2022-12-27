package com.bilol.mathgame

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findRandomQuestion()
        edit_result.addTextChangedListener {
            if (answer.toString().length == edit_result.text.toString().length) {
                checkAnswer()
            }
        }
    }

    var number1 = 0
    var number2 = 0
    var play = 0
    var answer = 0
    var count = 0

    private fun findRandomQuestion() {
        number1 = Random().nextInt(20)
        number2 = Random().nextInt(20)
        play = Random().nextInt(4)

        when (play) {
            0 -> {
                tv_question.text = "$number1 + $number2"
                answer = number1 + number2
            }
            1 -> {
                if (number1 < number2) {
                    return findRandomQuestion()
                }
                tv_question.text = "$number1 - $number2"
                answer = number1 - number2

            }
            2 -> {
                tv_question.text = "$number1 * $number2"
                answer = number1 * number2

            }
            3 -> {
                if (number1 < number2 && number1 % number2 != 0) {
                    return findRandomQuestion()
                }
                tv_question.text = "$number1 / $number2"
                answer = number1 / number2

            }
        }

    }

    private fun checkAnswer() {
        if (answer == edit_result.text.toString().toInt()) {
            count += 1
            score.text = count.toString()

            val mediaPlayer = MediaPlayer.create(this, R.raw.correct)
            mediaPlayer.start()
            Toast.makeText(this, "To'g'ri javob", Toast.LENGTH_SHORT).show()
        } else {
            val mediaPlayer = MediaPlayer.create(this, R.raw.wrong)
            mediaPlayer.start()
            Toast.makeText(this, "Nato'g'ri javob", Toast.LENGTH_SHORT).show()
        }
        edit_result.text.clear()
        findRandomQuestion()
    }
}