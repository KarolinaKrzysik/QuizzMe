package com.example.quizzme.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.quizzme.R
import com.example.quizzme.models.Question
import com.example.quizzme.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.activity_result.*
import java.lang.StringBuilder

class ResultActivity : AppCompatActivity() {
    lateinit var quiz: Quiz
    lateinit var firebaseFirestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpViews()
        resultGoBack.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }
    private fun setUpViews(){
        val quizData: String? = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)
        calculateScore()
        setAnswerView()
    }



    private fun calculateScore() {
        var score = 0
        var right = 0
        var wrong = 0
        for (entry: MutableMap.MutableEntry<String, Question> in quiz.questions.entries){
            val question:Question = entry.value
            if(question.answer == question.userAnswer){
                score += 10
                right += 1
            }else{
                wrong +=1
            }
        }
        txtScore.text = "Your score: " + score + "/100"
        txtRightWrong.text = "Correct answers: " +  right + "  |  Wrong answers: " + wrong


    }

    private fun setAnswerView() {
        val builder = StringBuilder("")
        for(entry in quiz.questions.entries){
            val question = entry.value

            if("" == question.userAnswer) {
                builder.append("<font color'#11164e'><b>Question: ${question.description}</b></font><br/><br/>")
                builder.append("<font color='#960800'>Skipped   |</font><font color='#099401'>|   Correct answer: ${question.answer}</font><br/><br/>")
            }else if(question.answer == question.userAnswer) {
                builder.append("<font color'#11164e'><b>Question: ${question.description}</b></font><br/><br/>")
                builder.append("<font color='#099401'>Your answer: ${question.answer}</font><br/><br/>")
            }else if(question.answer != question.userAnswer){
                builder.append("<font color'#11164e'><b>Question: ${question.description}</b></font><br/><br/>")
                builder.append("<font color='#960800'>Your answer: ${question.userAnswer}   |</font><font color='#099401'>|   Correct answer: ${question.answer}</font><br/><br/>")
            }

        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
           txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);

        }
        else{
            txtAnswer.text = Html.fromHtml(builder.toString());
        }
    }


}


