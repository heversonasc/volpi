package com.hasc.volpi.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hasc.volpi.Items
import com.hasc.volpi.R

class CompanyAdapter (
    private val dataList: ArrayList<Items>):
    RecyclerView.Adapter<CompanyAdapter.MainViewHolder>(), Filterable {

    private var driverFiltered = dataList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.res_item, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentItem = driverFiltered[position]
        holder.rvImage.setImageResource(currentItem.image)
        holder.rvTitle.text = currentItem.name
        holder.rvTitleDesc.text = currentItem.desc

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.url))
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return driverFiltered.size
    }

    override fun getFilter(): Filter {
        return driverFilter
    }

    private val driverFilter = object: Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val text = constraint?.toString().orEmpty()

            val resultList = ArrayList<Items>()

            if (text.isEmpty()) {
                resultList.addAll(dataList)
            } else {
                dataList
                    .filter { it.name.lowercase().contains(text.lowercase()) }
                    .forEach{ driver ->
                        resultList.add(driver)
                    }
            }

            return FilterResults().apply {
                values = resultList
                count = resultList.size
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

            val result = if (results?.values == null) {
                ArrayList()
            } else {
                results.values as ArrayList<Items>
            }
            setNewData(result)
        }
    }


    fun setNewData(data: List<Items>?) {
        driverFiltered = data.orEmpty() as ArrayList<Items>
        notifyDataSetChanged()
    }

    class MainViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvImage:ImageView = itemView.findViewById(R.id.image)
        val rvTitle: TextView = itemView.findViewById(R.id.tittle)
        val rvTitleDesc: TextView = itemView.findViewById(R.id.tittle_desc)

    }


}
