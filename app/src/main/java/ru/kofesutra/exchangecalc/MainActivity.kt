package ru.kofesutra.exchangecalc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.kofesutra.exchangecalc.data.api.ApiCommon
import ru.kofesutra.exchangecalc.data.api.ApiService
import ru.kofesutra.exchangecalc.dialogfragment.Dialog42
import ru.kofesutra.exchangecalc.model.Data
import ru.kofesutra.exchangecalc.start.StartAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mService: ApiService
    lateinit var adapter: StartAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var snackLay: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = ApiCommon.retrofitService
        snackLay = findViewById(R.id.mainView)

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

    } // - override fun onCreate

    // Меню на ActionBar (три точки)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    } // - override fun onCreateOptionsMenu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
//                Toast.makeText(applicationContext, "Обо!", Toast.LENGTH_SHORT).show()
//                Snackbar.make(snackLay, "Обо!", Snackbar.LENGTH_SHORT).show()
                val intent = Intent(this, ThirdActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_nothing -> {
//                Toast.makeText(applicationContext, "Ничего не происходит", Toast.LENGTH_LONG).show()
                val dialogFragment42 = Dialog42()
                val manager = supportFragmentManager
                dialogFragment42.show(manager, "dialog42")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    } // - override fun onOptionsItemSelected
    // - Меню на ActionBar (три точки)


    private fun runSwipeOn() {
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
//            Toast.makeText(applicationContext, "Обновление данных", Toast.LENGTH_SHORT).show()
            Snackbar.make(snackLay, "Обновление данных", Snackbar.LENGTH_LONG)
                .setBackgroundTint(getColor(android.R.color.holo_orange_dark))
                .setTextColor(getColor(android.R.color.white))
//                .setActionTextColor(getColor(android.R.color.white)) // цвет кнопки внутри сообщения
                .show()
            runLoader()
            swipeRefresh.postDelayed(Runnable {
                swipeRefresh.setRefreshing(false)
//                Toast.makeText(applicationContext, "Обновление завершено", Toast.LENGTH_SHORT).show()
                Snackbar.make(snackLay,"Обновление завершено",Snackbar.LENGTH_LONG).show()
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
//            Toast.makeText(applicationContext, "Обновление данных", Toast.LENGTH_SHORT).show()
            Snackbar.make(snackLay, "Обновление данных",Snackbar.LENGTH_LONG).show()
            runLoader()
        }
    } // - private fun runFAB

    private fun runLoader() {
        mService.getData().enqueue(object : Callback<List<Data>> {
     override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                adapter = StartAdapter(baseContext, response.body() as List<Data>)
                val recyclerviewUsers = findViewById<RecyclerView>(R.id.recView)
                recyclerviewUsers.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<List<Data>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    } // - private fun runLoader

   // =====================================
} // - class MainActivity
