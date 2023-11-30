package com.example.gogo.gogo2.completedhabit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gogo.R
import com.example.gogo.databinding.CompletedhabitListItemBinding
import com.example.gogo.databinding.HabitListItemBinding

class CompletedHabitAdapter(private val completedhabts: ArrayList<String>): RecyclerView.Adapter<CompletedHabitAdapter.CompletedHabitViewHolder>() {
    inner class CompletedHabitViewHolder(binding: CompletedhabitListItemBinding):RecyclerView.ViewHolder(binding.root) {
        val textView = binding.completedHabitTitle
            fun bind(position: Int) {
                textView.text = completedhabts[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedHabitAdapter.CompletedHabitViewHolder {
        val binding = CompletedhabitListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CompletedHabitViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return completedhabts.size
    }

    override fun onBindViewHolder(holder: CompletedHabitViewHolder, position: Int) {
        holder.bind(position)
    }

}