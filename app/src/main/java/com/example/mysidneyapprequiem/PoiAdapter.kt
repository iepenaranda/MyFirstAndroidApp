package com.example.mysidneyapprequiem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PoiAdapter (
    private val dataSet: ArrayList<Poi>,
    private val onClick: (Poi) -> Unit
) : RecyclerView.Adapter<PoiAdapter.PoiHolder>() {

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
    inner class PoiHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var nameLabel: TextView = view.findViewById(R.id.tvNameItem)
        private var descLabel: TextView = view.findViewById(R.id.tvDescItem)
        private var imageLabel: ImageView = view.findViewById(R.id.imageViewItem)
        private var currentPoi: Poi? = null

        init {
            view.setOnClickListener {
                currentPoi?.let {
                    onClick(it)
                }
            }
        }

        fun render(poi: Poi){
            currentPoi = poi

            nameLabel.text = poi.name
            descLabel.text = poi.description
            Picasso.get().load(poi.imageUrl).into(imageLabel)
        }
    }
}