package com.dikacandra.sebaran_kuliner_kota_pekanbaru

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.model.Restoran

class KulinerAdapter (
    private val listItems: ArrayList<Restoran>,
    private val listener: RestoranListener
    ) : RecyclerView.Adapter<KulinerAdapter.RestoranViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestoranViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            return RestoranViewHolder(view)
        }

        override fun getItemCount(): Int {
            return listItems.size
        }

        override fun onBindViewHolder(holder: RestoranViewHolder, position: Int) {
            val item = listItems[position]
            holder.textViewNamaRestoran.text = item.namaRestoran
            holder.textViewLokasi.text = item.lokasi
            holder.itemView.setOnClickListener {
                listener.OnItemClicked(item)
            }
        }

        class RestoranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var textViewNamaRestoran = itemView.findViewById<TextView>(R.id.text_view_restoran_empty)
            var textViewLokasi = itemView.findViewById<TextView>(R.id.text_view_lokasi)
        }

        interface RestoranListener{
            fun OnItemClicked(restoran: Restoran)
        }
}