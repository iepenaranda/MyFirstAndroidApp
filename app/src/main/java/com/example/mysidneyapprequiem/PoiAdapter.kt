package com.example.mysidneyapprequiem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PoiAdapter (private val dataSet: ArrayList<Poi>) :
    RecyclerView.Adapter<PoiAdapter.PoiHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoiHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_poi, parent, false)

        return PoiHolder(view)
    }

    override fun onBindViewHolder(holder: PoiHolder, position: Int) {
        holder.render(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    // Cada vez que se instancia crea un item
    inner class PoiHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private var currentPoi: Poi? = null

        fun render(poi: Poi){
            view.findViewById<TextView>(R.id.tvNameItem).text = poi.name
            view.findViewById<TextView>(R.id.tvDescItem).text = poi.description
            Picasso.get().load(poi.imageUrl).into(view.findViewById<ImageView>(R.id.imageViewItem))
        }

    }
}