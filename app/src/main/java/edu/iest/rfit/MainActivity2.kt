package edu.iest.rfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.iest.rfit.adapter.PerroAdapter
import edu.iest.rfit.models.ImagesBreed
import edu.iest.rfit.network.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    lateinit var recycleview: RecyclerView
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        spinner = findViewById<Spinner>(R.id.spinner)
        recycleview=findViewById(R.id.RVPerros)
        var administradorDeLayouts = GridLayoutManager(this,2)
        recycleview.layoutManager = administradorDeLayouts


        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Acciones a realizar cuando se selecciona una opción en el Spinner
                val selectedOption = parent?.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity2, "Opción seleccionada: $selectedOption", Toast.LENGTH_SHORT).show()
                mostrarperro(selectedOption)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })
    }

    fun mostrarperro(raza:String){
        val apiCall= API().crearServicioAPI()
        apiCall.listaImagenesDePerrosPorRaza("$raza")
            .enqueue(object: Callback<ImagesBreed> {
                override fun onResponse(call: Call<ImagesBreed>, response: Response<ImagesBreed>) {
                    val dogs=response.body()?.message//array
                    val status=response.body()?.status//array
                    Log.d("perro","Respuesta: ${response.body()?.status}")
                    if(dogs!=null){
                        val reducedDogs = dogs.take(5) // Tomar solo los primeros 10 elementos de la lista
                        var adapter= PerroAdapter(reducedDogs,this@MainActivity2)
                        var administradorDeLayouts = LinearLayoutManager(this@MainActivity2,
                            LinearLayoutManager.VERTICAL, false)
                        recycleview.layoutManager=administradorDeLayouts
                        recycleview.adapter=adapter
                    }else{
                        Log.d("perro","Respuesta: ${response.body()?.status}")

                    }

                }

                override fun onFailure(call: Call<ImagesBreed>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

}