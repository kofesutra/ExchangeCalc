package ru.kofesutra.exchangecalc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.kofesutra.exchangecalc.data.api.ApiCommon
import ru.kofesutra.exchangecalc.data.api.ApiService
import ru.kofesutra.exchangecalc.model.data
import ru.kofesutra.exchangecalc.start.StartAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mService: ApiService
    lateinit var adapter: StartAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = ApiCommon.retrofitService

//        this.setTitle("TREWQ")

        supportActionBar?.apply {
//            title = "Display Logo On ActionBar"
//            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setLogo(R.drawable.ic_launcher_foreground)
            setDisplayUseLogoEnabled(true)
        }

        runLoader()
        runFAB()
        runSwipeOn()
//        runButton()


//        val cardView = findViewById<CardView>(R.id.cardViewID)
//        cardView.setOnClickListener {
////        val intent = Intent(this, SecondActivity::class.java)
////        startActivity(intent)
////        Toast.makeText(applicationContext, "Кард-ля!", Toast.LENGTH_SHORT).show()
//        }

    } // - override fun onCreate



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    } // - override fun onCreateOptionsMenu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                Toast.makeText(applicationContext, "Обо!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ThirdActivity::class.java)

//                intent.putExtra(ThirdActivity.test,"888")
                startActivity(intent)
                return true
            }
            R.id.action_nothing -> {
                Toast.makeText(applicationContext, "Ничего не происходит", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    } // - override fun onOptionsItemSelected

//    private fun runButton() {
//    val butt = findViewById<Button>(R.id.buttonForw)
//    butt.setOnClickListener {
//        val intent = Intent(this, SecondActivity::class.java)
//        startActivity(intent)
//        Toast.makeText(applicationContext, "Ёп-та!", Toast.LENGTH_SHORT).show()
//    }
//    } // - private fun runButton


    private fun runSwipeOn() {
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            Toast.makeText(applicationContext, "Обновление данных", Toast.LENGTH_SHORT).show()

            runLoader()

            swipeRefresh.postDelayed(Runnable {
                swipeRefresh.setRefreshing(false)
                Toast.makeText(applicationContext, "Обновление завершено", Toast.LENGTH_SHORT).show()
            }, 3000)
        }
        swipeRefresh.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
    } // - private fun runSwipeOn

    private fun runFAB() {
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(applicationContext, "Обновление данных", Toast.LENGTH_SHORT).show()

            runLoader()

        }
    } // - private fun runFAB

    private fun runLoader() {
        mService.getData().enqueue(object : Callback<List<data>> {
     override fun onResponse(call: Call<List<data>>, response: Response<List<data>>) {
                adapter = StartAdapter(baseContext, response.body() as List<data>)
                val recyclerviewUsers = findViewById<RecyclerView>(R.id.recView)
                recyclerviewUsers.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<data>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    } // - private fun runLoader

   // =====================================
} // - class MainActivity
