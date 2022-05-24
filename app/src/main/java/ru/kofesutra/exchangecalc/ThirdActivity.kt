package ru.kofesutra.exchangecalc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

//        this.setTitle("TREWQ")

//        val toolbar: ActionBar? = supportActionBar
//        if (toolbar != null) {
//            val cal: Calendar = Calendar.getInstance()
//            val dynamicTitle: String = cal.getTime().toString()
//            //Setting a dynamic title at runtime. Here, it displays the current time.
//            toolbar.setTitle(dynamicTitle)
//        }

        val test: TextView = findViewById(R.id.textViewThird)
        test.text = "Обо, про, за, и прочее"

        supportActionBar?.apply {
//            title = "Display Logo On ActionBar"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setLogo(R.drawable.ic_launcher_foreground)
            setDisplayUseLogoEnabled(true)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}