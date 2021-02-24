package com.gallotti.appcafebarrestaurante.feature.store.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gallotti.appcafebarrestaurante.R
import com.gallotti.appcafebarrestaurante.network.model.Store
import kotlin.properties.Delegates

class StoreListAdapter(private val onClick: OnClickListener) :androidx.recyclerview.widget.RecyclerView.Adapter<StoreViewHolder>() {

    var items: List<Store> by Delegates.observable(emptyList()) { _, old, new ->
        if (old != new) notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        var item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClick.onClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    class OnClickListener(val clickListener: (featureItem: Store) -> Unit){
        fun onClick(featureItem: Store) = clickListener(featureItem)
    }
}

class StoreViewHolder(private val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)  {
    fun bind(store: Store) {
        //popular o item da lista com o objeto store
    }
}