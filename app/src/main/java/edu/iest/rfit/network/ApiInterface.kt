package edu.iest.rfit.network

import edu.iest.rfit.models.ImageRandom
import edu.iest.rfit.models.ImagesBreed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("breeds/image/random")
    fun imagenAleatoria(): Call<ImageRandom>

    @GET("breed/{raza}/images")
    fun listaImagenesDePerrosPorRaza
                (@Path("raza") raza: String): Call<ImagesBreed>
    //si raza = "chihuahua url seria
}