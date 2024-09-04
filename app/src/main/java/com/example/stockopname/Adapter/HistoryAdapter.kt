package com.example.stockopname.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stockopname.Models.DataItem
import com.example.stockopname.R
class HistoryAdapter(private val context: Context, private val data: List<DataItem?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_history_barang, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HistoryViewHolder) {
            val historyItem = data[position]
            historyItem?.let {
                holder.bind(it)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView8 = itemView.findViewById<TextView>(R.id.tvIdbarang)
        private val textView9 = itemView.findViewById<TextView>(R.id.tvNamaBarang)
        private val textView10 = itemView.findViewById<TextView>(R.id.tvQuantity)

        fun bind(historyItem: DataItem) {
            textView8.text = historyItem.idBarang
            textView9.text = historyItem.namaBarang
            textView10.text = historyItem.jumlahMasuk
        }
    }
}

