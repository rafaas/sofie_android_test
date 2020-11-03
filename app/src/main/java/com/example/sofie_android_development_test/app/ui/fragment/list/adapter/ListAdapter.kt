package com.example.sofie_android_development_test.app.ui.fragment.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofie_android_development_test.R
import com.example.sofie_android_development_test.domain.model.Task
import kotlinx.android.synthetic.main.task_item.view.*

class ListAdapter(private  val items: List<Task>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListAdapterViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.task_item, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ListAdapterViewHolder).bind(position)
    }

    override fun getItemCount() = items.size

    inner class ListAdapterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            val task = items[position]
            view.title.text = task.title
            view.email.text = task.email
            view.description.text = task.description
        }
    }
}