package ru.kofesutra.exchangecalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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

    lateinit var mService: ApiService
    lateinit var adapter: StartAdapter
    lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = ApiCommon.retrofitService

        runLoader()
        runFAB()
        runSwipeOn()

    } // - override fun onCreate

    private fun runSwipeOn() {
        // SwipeRefresh
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            Toast.makeText(applicationContext, "Обновление данных", Toast.LENGTH_SHORT).show()

            runLoader()

            swipeRefresh.postDelayed(Runnable {
                swipeRefresh.setRefreshing(false)
                // говорим о том, что собираемся закончить
                Toast.makeText(applicationContext, "Капеццц", Toast.LENGTH_SHORT).show()
            }, 3000)
        }
        swipeRefresh.setColorSchemeResources(
            android.R.color.holo_blue_bright, android.R.color.holo_green_light,
            android.R.color.holo_orange_light, android.R.color.holo_red_light
        ) // - SwipeRefresh
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
                adapter.notifyDataSetChanged()
                var recyclerview_users = findViewById<RecyclerView>(R.id.recView)
                recyclerview_users.adapter = adapter
            }

            override fun onFailure(call: Call<List<data>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    } // - private fun runLoader
   // =====================================
} // - class MainActivity
