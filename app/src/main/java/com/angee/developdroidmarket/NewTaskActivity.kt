package com.angee.developdroidmarket


import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import com.angee.developdroidmarket.room_db.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NewTaskActivity : AppCompatActivity () {
    lateinit var editTextReferencia: EditText
    lateinit var editTextMarca: EditText
    lateinit var editTextPrecio: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        editTextReferencia = findViewById(R.id.editTextReferencia)
        editTextMarca = findViewById(R.id.editTextMarca)
        editTextPrecio = findViewById(R.id.editTextPrecio)
    }

    fun  onSave(view: View){
    val db = ToDoDatabase.getDatabase(this)
    val dbFirebase = FirebaseFirestore.getInstance()

    val todoDAO = db.todoDAO()
    val  task = ToDo(0, editTextReferencia.text.toString(),editTextMarca.text.toString(),editTextPrecio.text.toString())

    runBlocking {
        launch {
            var  result = todoDAO.insertTask(task)
            if(result!=-1L){
            dbFirebase.collection( "ToDo").document(result.toString()).set(
               hashMapOf("marca" to editTextMarca.text.toString(),
                    "referencia" to editTextReferencia.text.toString(),
                    "precio" to editTextPrecio.text.toString())
            )
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    }




}