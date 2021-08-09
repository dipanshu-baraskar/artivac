package com.artivac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: CountryAdapter
    private val progressBar by lazy {
        findViewById<ProgressBar>(R.id.progressBar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBar()
        getCountry()
    }

    private fun setToolBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
    }

    private fun getCountry() {
        val country = CountryService.countryInstance.getCountryData(1)
        progressBar.visibility = View.VISIBLE
        country.enqueue(object : Callback<Title> {
            override fun onResponse(call: Call<Title>?, response: Response<Title>) {
                progressBar.visibility = View.GONE
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
                progressBar.visibility = View.GONE
            }
        })

    }
}