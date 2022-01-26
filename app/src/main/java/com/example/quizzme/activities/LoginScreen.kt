package com.example.quizzme.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizzme.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreen : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        firebaseAuth = FirebaseAuth.getInstance()

        btnLoginAct.setOnClickListener{
            login()
        }

        btngotoSignUp.setOnClickListener(){
            val intent = Intent(this, SignUpScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login(){
        val email= etEmailAddressLogin.text.toString()
        val password = etPasswordLogin.text.toString()

        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this, "Email or password cannot be empty", Toast.LENGTH_SHORT).show()
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}