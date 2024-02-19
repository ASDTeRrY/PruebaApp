package com.prueba.pruebaapp.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun <ITEM> RecyclerView.setUp(
    items: List<ITEM>,
    layoutResId: Int,
    bindHolder: View.(ITEM) -> Unit,
    itemClick: ITEM.() -> Unit,
    manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
): Kadapter<ITEM> {
    return Kadapter(items, layoutResId, {
        bindHolder(it)
    }, {
        itemClick()
    }).apply {
        layoutManager = manager
        adapter = this
    }
}

class Kadapter<ITEM>(
    items: List<ITEM>,
    layoutResId: Int,
    private val bindHolder: View.(ITEM) -> Unit
) : AbstractAdapter<ITEM>(items, layoutResId) {

    private var itemClick: ITEM.() -> Unit = {}

    constructor(
        items: List<ITEM>,
        layoutResId: Int,
        bindHolder: View.(ITEM) -> Unit,
        itemClick: ITEM.() -> Unit = {}
    ) : this(items, layoutResId, bindHolder) {
        this.itemClick = itemClick
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.bindHolder(itemList[position])
    }

    override fun onItemClick(itemView: View, position: Int) {
        itemList[position].itemClick()
    }
}

abstract class AbstractAdapter<ITEM>(
    protected var itemList: List<ITEM>,
    private val layoutResId: Int
) : RecyclerView.Adapter<AbstractAdapter.Holder>() {

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itemList[position]
        holder.itemView.bind(item)
    }

    protected abstract fun onItemClick(itemView: View, position: Int)

    protected open fun View.bind(item: ITEM) {
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}