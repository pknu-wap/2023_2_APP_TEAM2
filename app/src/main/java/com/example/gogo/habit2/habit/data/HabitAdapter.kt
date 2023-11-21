package com.example.gogo.habit2.habit.data

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.gogo.databinding.HabitListItemBinding




class HabitAdapter(private val itemList: List<String>) :RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {
    private lateinit var mOnItemClickListener: OnItemClickListener
    private lateinit var mOnRemoveItemClickListener: OnItemClickListener
    private var habitDatabase: HabitDatabase? = null
    inner class HabitViewHolder(private val binding: HabitListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            binding.habitTextView.apply {
             text = itemList[position]
             setOnClickListener {
                 mOnItemClickListener.onItemClick(it,position)
             }
            }
            binding.checkBox
            binding.removeButton.setOnClickListener {
                val removeHabit = itemList[position]
                mOnItemClickListener.onRemoveItemClick(it, removeHabit)
            }
        }
    }


    interface OnItemClickListener  {
        fun onItemClick(view: View, position: Int)
        fun onRemoveItemClick(view: View, habitName : String)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = HabitListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HabitViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(position)
    }
}