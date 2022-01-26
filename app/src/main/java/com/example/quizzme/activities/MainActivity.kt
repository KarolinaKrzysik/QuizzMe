package com.example.quizzme.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizzme.R
import com.example.quizzme.adapters.QuizAdapter
import com.example.quizzme.models.Quiz
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer.*

class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    lateinit var firebaseAuth: FirebaseAuth
    private var quizlist = mutableListOf<Quiz>()
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }




    fun setUpView(){
        setUpFireStore()
        setUpDrawerLayout()
        setUpRecycleView()
    }

    private fun setUpFireStore() {
    firestore= FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener {value, error ->
            if (value == null || error != null){
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            Log.d("DATA", value.toObjects(Quiz::class.java).toString())
            quizlist.clear()
            quizlist.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }


    private fun setUpRecycleView() {
        adapter = QuizAdapter(this, quizlist)
        quizzMeRecycler.layoutManager = GridLayoutManager(this, 2)
        quizzMeRecycler.adapter = adapter
    }

    fun setUpDrawerLayout(){
        setSupportActionBar(applicationBar)
        actionBarDrawerToggle= ActionBarDrawerToggle(
            this, mainDrawer,
            R.string.app_name,
            R.string.app_name,

            )
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        mainDrawer.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
        return true

        }
        return super.onOptionsItemSelected(item)
    }
}