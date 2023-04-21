package edu.iest.rfit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import edu.iest.rfit.R

class PerroAdapter(
private val perros: List<String>,
private val context: Context):
RecyclerView.Adapter<PerroAdapter.PerroViewHolder>() {
    inner class PerroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgDog: ImageView = itemView.findViewById(R.id.img_dog)
        val textDog: TextView = itemView.findViewById(R.id.tvurl)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerroViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.perro, parent, false)
        return PerroViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: PerroViewHolder, position: Int) {
        val imgUrl = perros[position]
        holder.textDog.text=imgUrl
        // Aquí se carga la imagen en el ImageView usando Picasso
        Picasso.get()
            .load(imgUrl)
            .placeholder(R.drawable.imagesrcview_foreground) // Puedes usar una imagen de placeholder mientras se carga la imagen real
            .error(R.drawable.imagesrcview_foreground) // Puedes usar una imagen de error en caso de que no se pueda cargar la imagen real
            .into(holder.imgDog)
    }
    override fun getItemCount(): Int {
        return perros.size
    }
}
