package com.example.quizzme.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzme.R
import com.example.quizzme.activities.LoginScreen
import com.example.quizzme.activities.MainActivity
import com.example.quizzme.models.Question

class OptionAdapter(val context:Context, val question: Question) :
    RecyclerView.Adapter<OptionAdapter.OptionViewHolder>(){

    private var options: List<String> = listOf(question.option1, question.option2, question.option3, question.option4)
    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var optionView = itemView.findViewById<TextView>(R.id.quiz_option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item, parent, false)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.itemView.setOnClickListener{
            //Toast.makeText(context, options[position], Toast.LENGTH_SHORT).show()
            question.userAnswer = options[position]
            notifyDataSetChanged()
        }

        if(question.userAnswer == options[position]){
            //Toast.makeText(context, "SHOW RED BORDER", Toast.LENGTH_SHORT).show()
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }else{
            //Toast.makeText(context, "SHOW SIMPLE BORDER", Toast.LENGTH_SHORT).show()
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }
}