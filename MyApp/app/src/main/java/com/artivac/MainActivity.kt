package com.artivac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: CountryAdapter
    private val refreshLayout by lazy {
        findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBar()
        refreshLayout.setOnRefreshListener {
            adapter.clear()
            getCountry()
        }
        getCountry()
    }

    private fun setToolBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
    }

    private fun getCountry() {
        val country = CountryService.countryInstance.getCountryData(1)
        refreshLayout.isRefreshing = true
        country.enqueue(object : Callback<Title> {
            override fun onResponse(call: Call<Title>?, response: Response<Title>) {
                refreshLayout.isRefreshing = false
                val title = response.body()
                supportActionBar?.title = title.title
                if (title != null) {
                    Log.d("DIP", title.toString())
                    adapter = CountryAdapter(this@MainActivity, title.rows)
                    var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<Title>?, t: Throwable?) {
                Log.d("DIP", "Error in fetching data: ", t)
                refreshLayout.isRefreshing = false
            }
        })

    }
}