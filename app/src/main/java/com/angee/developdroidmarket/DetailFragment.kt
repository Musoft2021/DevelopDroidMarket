package com.angee.developdroidmarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater:LayoutInflater,container:ViewGroup?,
        savedInstanceState:Bundle?
    ): View?{
        val fragmento= inflater.inflate(R.layout.fragment_detail,container,false)

        var Marca= requireArguments().getString("Marca")
        var Referencia= requireArguments().getString("Referencia")
        var Precio= requireArguments().getString("Precio")

        var textViewMarca: TextView=fragmento.findViewById(R.id.textViewMarca)
        var textViewReferencia: TextView=fragmento.findViewById(R.id.textViewRef)
        var textViewPrecio: TextView=fragmento.findViewById(R.id.textViewPrecio)

        textViewMarca.text=Marca
        textViewReferencia.text=Referencia
        textViewPrecio.text=Precio

        return fragmento
    }

}