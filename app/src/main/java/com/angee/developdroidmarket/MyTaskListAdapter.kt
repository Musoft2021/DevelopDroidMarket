package com.angee.developdroidmarket

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class MyTaskListAdapter (context: AppCompatActivity,
                         val info: Bundle) : RecyclerView.Adapter<MyTaskListAdapter.MyViewHolder>() {

    class MyViewHolder(val layout: View) : RecyclerView.ViewHolder(layout)

    private var context: AppCompatActivity = context
    private var data: Bundle = info

    var myTaskReferencia: ArrayList<String> = data.getStringArrayList("Referencia") as ArrayList<String>
    var myTaskMarca: ArrayList<String> = data.getStringArrayList("Marca") as ArrayList<String>
    var myTaskPrecio: ArrayList<String> = data.getStringArrayList("Precio") as ArrayList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val layout = LayoutInflater.from(parent.context).inflate(R.layout.task_list_items,parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var textViewReferencia = holder.layout.findViewById<TextView>(R.id.textViewRefe)
        textViewReferencia.text = myTaskReferencia[position]

        var textViewMarca = holder.layout.findViewById<TextView>(R.id.textViewMarca)
        textViewMarca.text = myTaskReferencia[position]

        holder.layout.setOnClickListener {
            Toast.makeText(holder.itemView.context, textViewReferencia.text, Toast.LENGTH_LONG)
                .show()
            val datos = Bundle()
            datos.putString("Referencia",textViewReferencia.text as String)
            datos.putString("Marca",textViewMarca.text as String)


        }
    }

    override fun getItemCount(): Int {
        return myTaskReferencia.size
    }
}



