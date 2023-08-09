package com.example.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlin.databinding.RecylerRowBinding
import com.example.retrofitkotlin.model.CryptoModel

class CryptoAdapter(private val arrayList: ArrayList<CryptoModel>,private val listener: Listener):RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    private  val colors: Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#a1fc92")
    interface Listener{
        fun onItemClik(cyrptoModel:CryptoModel)
    }

    class CryptoViewHolder(val binding: RecylerRowBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(cryptoModel:CryptoModel,colors:Array<String>,position: Int,listener: Listener){
            itemView.setOnClickListener {
                listener.onItemClik(cryptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position%7]))
            binding.txtName.text=cryptoModel.currency
            binding.txtPrice.text=cryptoModel.price



        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view=RecylerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return CryptoViewHolder(view);
    }



    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bind(arrayList[position],colors,position,listener)
    }
    override fun getItemCount(): Int {
       return  arrayList.count()
    }
}
