package edu.iest.rfit.network

import android.widget.ImageView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    private val URL_BASE = "https://dog.ceo/api/"

    fun crearServicioAPI(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: ApiInterface = retrofit.create(ApiInterface::class.java)
        return service
    }
    //fun cargarImagenConPicasso(url: String, imageView: ImageView) {
      //  Picasso.get()
        //    .load(url)
         //   .into(imageView)
  //  }
}