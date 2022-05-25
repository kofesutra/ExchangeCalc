package ru.kofesutra.exchangecalc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.imageLoader
import coil.load

class SecondActivity : AppCompatActivity() {

//    private val context: Context

    companion object {
        const val intentLogo = "???"
        const val intentName = "!!!"
        const val intentPrice = "888"
//        const val intentPosition = "888"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var nameOf2: TextView = findViewById(R.id.item_name_2)
        nameOf2.text = intent.getStringExtra(intentName)

        var price2: TextView = findViewById(R.id.item_price_2)
        price2.text = intent.getStringExtra(intentPrice)

        var logo2: ImageView = findViewById(R.id.item_logo_2)
        var logoInt = intent.getStringExtra(intentLogo)

//// Обработкик SVG
        val svgImageLoader = ImageLoader.Builder(this)
            .components { add(SvgDecoder.Factory()) }.build()
        // Binding SVG
        logo2.load(logoInt, svgImageLoader) {
            size(300, 300)
        } // - Обработкик SVG Binding SVG

//        runButton()

        supportActionBar?.apply {
            title = "Details"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
//            setLogo(R.drawable.ic_launcher_foreground)
//            setDisplayUseLogoEnabled(true)
        }

//        //actionbar
//        val actionbar = supportActionBar
//        //set actionbar title
//        actionbar!!.title = "Details"
//        //set back button
//        actionbar.setDisplayHomeAsUpEnabled(true)
//        actionbar.setDisplayHomeAsUpEnabled(true)

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