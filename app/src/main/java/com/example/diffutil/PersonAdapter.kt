package com.example.diffutil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    private val personList = mutableListOf<Person>()

    fun setData(newData: List<Person>) {
        val diffUtil = MyDiffUtil(personList, newData)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        personList.clear()      // Clear old data
        personList.addAll(newData)  // Add new data
        diffResult.dispatchUpdatesTo(this)  // Efficiently updates only the changed items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.bind(person)
    }

    override fun getItemCount() = personList.size

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(person: Person) {
            itemView.findViewById<TextView>(R.id.name).text = person.name
            itemView.findViewById<TextView>(R.id.age).text = person.age.toString()
        }
    }
}
