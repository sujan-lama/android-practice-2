package com.example.a20220606_sujanlama_nycschools.ui.schoolList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a20220606_sujanlama_nycschools.databinding.SchoolListItemBinding
import com.example.a20220606_sujanlama_nycschools.model.School

class SchoolListAdapter(private val onSchoolClickListener: OnSchoolClickListener) :
    ListAdapter<School, SchoolListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SchoolListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun updateList(list: List<School>) = submitList(list)


    inner class ViewHolder(private val binding: SchoolListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(school: School) {
            binding.tvSchoolName.text = school.schoolName
            binding.tvSchoolEmail.text = school.schoolEmail ?: "N/A"
            binding.btnShowDetails.setOnClickListener {
                onSchoolClickListener.onClick(school)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<School>() {
        override fun areItemsTheSame(oldItem: School, newItem: School) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: School, newItem: School) =
            oldItem == newItem
    }
}


interface OnSchoolClickListener {
    fun onClick(school: School)
}