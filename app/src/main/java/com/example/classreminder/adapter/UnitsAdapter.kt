package com.example.classreminder.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.classreminder.R
import com.example.classreminder.data.UnitModel

class UnitsAdapter: RecyclerView.Adapter<UnitsAdapter.MyViewHolder>() {

    lateinit var context: Context
    var count: Int =0
    private var unitsList: MutableList<UnitModel>? = null

    fun setUnitsList(unitsList:MutableList<UnitModel>, context: Context){
        this.unitsList = unitsList
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.unit_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(unitsList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if (unitsList == null) 0
        else unitsList?.size!!
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val unitTitle = itemView.findViewById<TextView>(R.id.unitTitle)
        val unitPercentage = itemView.findViewById<TextView>(R.id.unitPercentage)

        init {
        }

        @SuppressLint("SetTextI18n")
        fun bind(data:UnitModel){
            unitTitle.text = data.unitName
            unitPercentage.text = data.count.toString()+"/14"

        }


    }
}