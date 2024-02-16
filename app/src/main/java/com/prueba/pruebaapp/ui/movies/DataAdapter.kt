package com.prueba.pruebaapp.ui.movies
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prueba.pruebaapp.R
import com.prueba.pruebaapp.domain.model.DataModel
import com.prueba.pruebaapp.domain.model.DetailModel
import com.prueba.pruebaapp.domain.model.MovieModel

class DataAdapter (private var list : List<DetailModel>, private val onItemClick: (DetailModel) -> Unit): RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent,false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        Glide.with(holder.image).load(list.get(position).posterPath).into(holder.image)
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    fun updateData(list: ArrayList<DetailModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    //Use the method for item changed
/*    fun itemChanged() {
        // remove last item for test purposes
        this.list[0] = (MovieModel(R.drawable.londonlove, "Thi is cool"))
        notifyItemChanged(0)

    }*/

    //Use the method for checking the itemRemoved
    fun removeData() {
        // remove last item for test purposes
        val orgListSize = list.size
        this.list = this.list.subList(0, orgListSize - 1).toList() as ArrayList<DetailModel>
        notifyItemRemoved(orgListSize - 1)
    }
}