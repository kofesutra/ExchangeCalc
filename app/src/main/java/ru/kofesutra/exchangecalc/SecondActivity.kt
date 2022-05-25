package ru.kofesutra.exchangecalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load

class SecondActivity : AppCompatActivity() {

    companion object {
        const val intentLogo = "???"
        const val intentName = "!!!"
        const val intentPrice = "888"
        const val intentHigh = "999"
//        const val intentPercentDay = "999"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val nameOf2: TextView = findViewById(R.id.item_name_2)
        nameOf2.text = intent.getStringExtra(intentName)

        val price2: TextView = findViewById(R.id.item_price_2)
        price2.text = intent.getStringExtra(intentPrice)

        val logo2: ImageView = findViewById(R.id.item_logo_2)
        val logoInt = intent.getStringExtra(intentLogo)

        val priceHigh: TextView = findViewById(R.id.item_high_2)
        priceHigh.text = intent.getStringExtra(intentHigh)

//        var pricePercentDay: TextView = findViewById(R.id.item_percent_day)
//        pricePercentDay.text = intent.getStringExtra(intentPercentDay)

        // Обработкик SVG
        val svgImageLoader = ImageLoader.Builder(this)
            .components { add(SvgDecoder.Factory()) }.build()
        // Binding SVG
        logo2.load(logoInt, svgImageLoader) {
            size(300, 300)
        } // - Обработкик SVG Binding SVG

        supportActionBar?.apply {
            title = "Details"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
//            setLogo(R.drawable.ic_launcher_foreground)
//            setDisplayUseLogoEnabled(true)
        } // - supportActionBar?.apply
    } // - override fun onCreate

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
} // - class SecondActivity