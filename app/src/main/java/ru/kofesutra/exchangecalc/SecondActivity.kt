package ru.kofesutra.exchangecalc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var test: TextView = findViewById(R.id.item_name_2)
        test.text = "kkk"

//        runButton()

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "New Activity"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    } // - override fun onCreate

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

//    private fun runButton() {
//        val butt = findViewById<Button>(R.id.buttonBack)
//        butt.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            Toast.makeText(applicationContext, "Ёп-та-та!", Toast.LENGTH_SHORT).show()
//        }
//    } // - private fun runButton

}