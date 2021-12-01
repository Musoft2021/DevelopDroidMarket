package com.angee.developdroidmarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.errorprone.annotations.Var

class ToDoFragment : Fragment() {

    private lateinit var listRecyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<MyTaskListAdapter.MyViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmento: View=inflater.inflate(R.layout.fragment_to_do, container, false)

//        val detail1: Button =fragmento.findViewById(R.id.Btn_detail_1)
//        val detail2: Button =fragmento.findViewById(R.id.Btn_detail_2)
//        val detail3: Button =fragmento.findViewById(R.id.Btn_detail_3)
//
//        detail1.setOnClickListener(View.OnClickListener {
//            val datos=Bundle()
//            datos.putString("Marca",resources.getString(R.string.text_Iphone12))
//            datos.putString("Referencia","Iphone 12")
//            datos.putString("Precio","4.500.000")
//            activity?.getSupportFragmentManager()?.beginTransaction()
//                ?.setReorderingAllowed(true)
//                ?.replace(R.id.fragment_container_view,DetailFragment::class.java,datos, "detail")
//                ?.addToBackStack("")
//                ?.commit()
//        })
//
//        detail2.setOnClickListener(View.OnClickListener {
//            val datos=Bundle()
//            datos.putString("Marca",resources.getString(R.string.text_Iphone12))
//            datos.putString("Referencia","Iphone 12 ProMax")
//            datos.putString("Precio","5.500.000")
//            activity?.getSupportFragmentManager()?.beginTransaction()
//                ?.setReorderingAllowed(true)
//                ?.replace(R.id.fragment_container_view,DetailFragment::class.java,datos, "detail")
//                ?.addToBackStack("")
//                ?.commit()
//        })
//
//
//        detail3.setOnClickListener(View.OnClickListener {
//            val datos=Bundle()
//            datos.putString("Marca",resources.getString(R.string.text_Watch))
//            datos.putString("Referencia","AppleWatch S6 ")
//            datos.putString("Precio","1.800.000")
//            activity?.getSupportFragmentManager()?.beginTransaction()
//                ?.setReorderingAllowed(true)
//                ?.replace(R.id.fragment_container_view,DetailFragment::class.java,datos, "detail")
//                ?.addToBackStack("")
//                ?.commit()
//        })


        return fragmento
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var  myTaskMarca:ArrayList<String> = ArrayList()

        myTaskMarca.add(resources.getString(R.string.text_Iphone))
        myTaskMarca.add(resources.getString(R.string.text_Iphonepro))
        myTaskMarca.add(resources.getString(R.string.text_Watch))

        var myTaskReferencia:ArrayList<String> = ArrayList()

        myTaskReferencia.add("Iphone 12")
        myTaskReferencia.add("Iphone 12 Pro Max")
        myTaskReferencia.add("Watch S6")

        var myTaskPrecio: ArrayList<String> = ArrayList()

        myTaskPrecio.add("4.500.000")
        myTaskPrecio.add("5.500.000")
        myTaskPrecio.add("1.800.000")

        var info: Bundle = Bundle()
        info.putStringArrayList("Marca", myTaskMarca)
        info.putStringArrayList("Referencia", myTaskReferencia)
        info.putStringArrayList("Precio", myTaskPrecio)


        listRecyclerView = requireView().findViewById(R.id.recyclerTodoList)
        myAdapter = MyTaskListAdapter(activity as AppCompatActivity, info)

        listRecyclerView.setHasFixedSize(true)
        listRecyclerView.adapter= myAdapter
        listRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))


    }
}