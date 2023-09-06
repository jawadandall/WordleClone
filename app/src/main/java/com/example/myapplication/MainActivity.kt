 package com.example.myapplication


import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.View


 class MainActivity : AppCompatActivity() {
     private var guess = ""
     private var answer = ""
     private var tries = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.textInput)
        val textView = findViewById<TextView>(R.id.textView)
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager



        answer = FourLetterWordList.getRandomFourLetterWord()

        button.setOnClickListener {
            tries++
            if (tries==1) {
                guess = editText.text.toString().uppercase()
                editText.setText("")
                if (guess == answer) {
                    textView.append("\n" + guess + "\n")
                    textView.append("Correct!" + "\n")
                    tries = 3
                } else {
                    textView.append("\n" + guess + "\n")
                    textView.append(checkGuess(guess, answer) + "\n")
                }
            }
            if (tries==2) {
                guess = editText.text.toString().uppercase()
                if (guess == answer) {
                    textView.append("\n" + guess + "\n")
                    textView.append("Correct!" + "\n")
                    tries = 3

                } else {
                    textView.append("\n" + guess + "\n")
                    textView.append(checkGuess(guess, answer) + "\n")
                }
            }
            if (tries ==3) {
                button.setText("Reset")
                if(guess == answer){
                    textView.append("\n" + guess + "\n")
                    textView.append("Correct!" + "\n")
                }
                if (guess != answer) {
                    textView.append("\nThe correct answer was " + answer)
                }
            } else if (tries== 4){
                this.recreate()
            }
        }
    }

    private fun checkGuess(guess: String, answer: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == answer[i]) {
                result += "O"
            }
            else if (guess[i] in answer) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }


}