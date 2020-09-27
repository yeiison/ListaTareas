package com.example.listatareas

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
//import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.PrintStream
import java.util.*


    class MainActivity : AppCompatActivity() {
        var listaTareas: ArrayList<String>? = null
        var adaptador1: ArrayAdapter<String>? = null
        var vistaLista: ListView? = null
        var ingresarTarea: EditText? = null
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            listaTareas = ArrayList()
            Log.d("myTag", "**************************************************************************************");
            readTareas(listaTareas!!)
            //listaTareas!!.add("Ejercicio Electiva ll")
            //listaTareas!!.add("Trabajo de grado")
            //listaTareas!!.add("Solucion taler ingenieria")
            adaptador1 = ArrayAdapter(
                this, android.R.layout.simple_list_item_1,
                listaTareas!!
            )
            vistaLista = findViewById<View>(R.id.listView) as ListView
            vistaLista!!.adapter = adaptador1
            ingresarTarea = findViewById<View>(R.id.editText) as EditText
            vistaLista!!.onItemLongClickListener = OnItemLongClickListener {
                    adapterView, view, i, l ->
                    val dialogo1 = AlertDialog.Builder(this@MainActivity)
                    dialogo1.setTitle("Importante")
                    dialogo1.setMessage("¿ Elimina esta tarea ?")
                    dialogo1.setCancelable(false)
                    dialogo1.setPositiveButton("Confirmar")
                    { dialogo1, id ->
                        listaTareas!!.removeAt(i)
                        adaptador1!!.notifyDataSetChanged()
                    }
                    dialogo1.setNegativeButton(
                        "Cancelar"
                    ) { dialogo1, id -> }
                    dialogo1.show()
                    false
                }
        }

        fun añadir(v: View?) {
            listaTareas!!.add(ingresarTarea!!.text.toString())
            adaptador1!!.notifyDataSetChanged()
            saveTarea(ingresarTarea!!.text.toString())
           // ingresarTarea!!.setText(" ")
        }

        fun readTareas(listaTareas: ArrayList<String>) {
            //abrir archivo
                        //val input=Scanner(resources.openRawResource(R.raw.default_names))
            val input=Scanner(openFileInput("tareas.txt"))
            //Leer Archivo
            val tareas = arrayListOf<String>()
            while (input.hasNextLine()){
                val tarea = input.nextLine()
                tareas.add(this.listaTareas.toString())

                //Mostrar Nombres
            }
            //tareas = findViewById<View>(R.id.listView) as ListView
            //listView.textView = "$tarea"
            input.close()
        }

        fun saveTarea(ingresarTarea: String?){
            val output = PrintStream( openFileOutput("tareas.txt",Activity.MODE_APPEND))
            output.println(ingresarTarea)
            output.close()

        }
}