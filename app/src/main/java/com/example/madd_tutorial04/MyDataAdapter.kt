package com.example.madd_tutorial04

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyDataAdapter(
    private val data: List<MyTable>,
    private val onUpdateClicked: (MyTable) -> Unit,
    private val onDeleteClicked: (MyTable) -> Unit
) : RecyclerView.Adapter<MyDataVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDataVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        return MyDataVH(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyDataVH, position: Int) {
        val singleData = data[position]
        holder.tvItemName.text = if (!singleData.dateOfBirth.isNullOrEmpty()) {
            "${singleData.name} (${singleData.dateOfBirth})"
        } else {
            singleData.name ?: ""
        }

        holder.btnUpdateItem.setOnClickListener {
            onUpdateClicked(singleData)
        }

        holder.btnDeleteItem.setOnClickListener {
            onDeleteClicked(singleData)
        }
    }
}
