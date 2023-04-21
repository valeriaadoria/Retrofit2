package edu.iest.rfit

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import edu.iest.rfit.models.ImageRandom
import edu.iest.rfit.models.ImagesBreed
import edu.iest.rfit.network.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiCall = API().crearServicioAPI()

       val ivImagen = findViewById<ImageView>(R.id.ivperro)
        val tvURL = findViewById<TextView>(R.id.tvurl)
        apiCall.imagenAleatoria().enqueue(object : Callback<ImageRandom> {

            override fun onResponse(call: Call<ImageRandom>, response: Response<ImageRandom>) {
                Log.d("API_PRUEBA", "status es " + response.body()?.status)
                Log.d("API_PRUEBA ", "message es " + response.body()?.message)

                // Aqui hacer lo que necesitemos con los datos
                Picasso.get().load(response.body()?.message).into(ivImagen);
                tvURL.text= response.body()?.message
            }

            override fun onFailure(call: Call<ImageRandom>, t: Throwable) {
                Toast.makeText( this@MainActivity, "No fue posible conectar a API", Toast.LENGTH_SHORT ).show() }
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.option_menu_list_images) {
            Toast.makeText(this, "Option menu 1", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
            finish()
            /*
            val apiCall = API().crearServicioAPI()
            apiCall.listaImagenesDePerrosPorRaza("hound").enqueue(object: Callback<ImagesBreed> {
                override fun onResponse(call: Call<ImagesBreed>, response: Response<ImagesBreed>) {
                    val dogs = response.body()?.message
                    //response.body()?.status
                    Log.d("PRUEBAS", "Status de la respuesta ${response.body()?.status}")
                    if(dogs != null){
                        for (dog in dogs){
                            Log.d("PRUEBAS", "Perro es $dog")
                        }
                    }
                }

                override fun onFailure(call: Call<ImagesBreed>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })*/
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?):Boolean{
        menuInflater.inflate(R.menu.menu_images,menu)
        return super.onCreateOptionsMenu(menu)
    }

}