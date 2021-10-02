package com.example.mysidneyapprequiem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var mPoi: ArrayList<Poi>
    private lateinit var mAdapter: PoiAdapter
    private lateinit var recycler: RecyclerView

    //TODO: agregar ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.rvListPoi)
        setupRecycleView()
        generatePoi()
    }


    private fun setupRecycleView() {
        mPoi = arrayListOf()
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = linearLayoutManager
        mAdapter = PoiAdapter(mPoi)
        recycler.adapter = mAdapter
    }

    private fun generatePoi() {
        val poiString = readPoiJsonFile()
        try {
            val poisJson = JSONArray(poiString)

            for (i in 0 until poisJson.length()) {
                val poiJson = poisJson.getJSONObject(i)
                val poiItem = Poi(
                    poiJson.getString("name"),
                    poiJson.getString("description"),
                    poiJson.getString("imageURL")
                )
                Log.d("MainActivity", "generatePoi: $poiItem")
                mPoi.add(poiItem)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun readPoiJsonFile(): String? {
        var poiString: String? = null
        try {
            val inputStream = assets.open("data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            poiString = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return poiString
    }
}