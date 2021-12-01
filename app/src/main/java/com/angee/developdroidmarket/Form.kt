package com.angee.developdroidmarket

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.utilities.Validation
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

class Form : AppCompatActivity() {
    private var editUserName: EditText? = null
    private var editPassword: EditText? = null
    private var editName: EditText? = null
    private var editLastName: EditText? = null
    private var editMobile: EditText? = null
    private var stTerms: Switch? = null

    var db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        editUserName = findViewById(R.id.editUserName)
        editPassword = findViewById(R.id.editPassword)
        editName = findViewById(R.id.editName);
        editLastName = findViewById(R.id.editLastName);
        editMobile = findViewById(R.id.editMobile);
        stTerms = findViewById(R.id.stTerms);

        title = "Register"
    }


    fun onRegister(view: android.view.View) {

        var username = editUserName!!.text.toString();
        var password = editPassword!!.text.toString();
        var name = editName!!.text.toString();
        var lastname = editLastName!!.text.toString();
        var mobile = editMobile!!.text.toString();
        var terms = stTerms!!.isChecked;

        if (Validation(username, password, name, lastname, mobile, terms)) {

            //Save User
            db.collection("user").document(username).set(
                hashMapOf(
                    "password" to password,
                    "name" to name,
                    "lastname" to lastname,
                    "mobile" to mobile,
                    "terms" to terms
                )
            )
            //Save Auth
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(username, ProviderType.BASIC)
                    } else {
                        getToast(resources.getString(R.string.text_errorAuth));
                    }
                }

        } else {
            getToast(resources.getString(R.string.test_ValidateError));
        }

    }
    private fun Validation(username: String, password: String,name: String,lastname: String,mobile: String,terms: Boolean): Boolean {


        //Regex
        val uppercase: Pattern = Pattern.compile("[A-Z]")
        val lowercase: Pattern = Pattern.compile("[a-z]")
        val digit: Pattern = Pattern.compile("[0-9]")
        val character: Pattern = Pattern.compile("[!#\$%&'*+/=?^_`{|}~-]")
        val email: Pattern = Pattern.compile("^[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\$")

        //Validation
        var validation : Boolean = true;

        if (name.isEmpty()) {
            editName!!.setHint(resources.getString(R.string.test_EmptyName))
            validation=false
        }

        if (lastname.isEmpty()) {
            editLastName!!.setHint(resources.getString(R.string.test_Emptylastname))
            validation=false
        }

        if (mobile.isEmpty()) {
            editMobile!!.setHint(resources.getString(R.string.test_Emptymobile))
            validation=false
        }

        if (!terms){
            stTerms!!.setBackground(resources.getDrawable(R.drawable.customborder))
            validation=false
        }

        //Validate Password
        if (password.isEmpty()) {
            editPassword!!.setHint(resources.getString(R.string.test_emptypassword))
            validation=false

        }else{
            if (password.length < 8) {
                editPassword!!.setHint(resources.getString(R.string.test_passwordlength))
                validation=false

            }else{
                if (!lowercase.matcher(password).find()) {
                    editPassword!!.setHint(resources.getString(R.string.text_lowercase))
                    validation=false

                }else{
                    if (!uppercase.matcher(password).find()) {
                        editPassword!!.setHint(resources.getString(R.string.text_uppercase))
                        validation=false
                    }else{

                        if (!digit.matcher(password).find()){
                            editPassword!!.setHint(resources.getString(R.string.text_digit))
                            validation=false

                        }else{
                            if (!character.matcher(password).find()){
                                editPassword!!.setHint(resources.getString(R.string.text_character))
                                validation=false
                            }
                        }
                    }
                }
            }
        }

        //Validation Email
        if (username.isEmpty()) {
            editUserName!!.setHint(resources.getString(R.string.text_EmptyMail))
            validation=false
        }else{
            if (!email.matcher(username).find()){
                editUserName!!.setHint(resources.getString(R.string.text_emailerror))
                validation=false
            }
        }

        return validation;
    }
    private fun showHome(username: String, provider: ProviderType) {

        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", username)
            putExtra("provider", provider.toString())
        }

        startActivity(homeIntent)

        getToast(resources.getString(R.string.text_welcome));
    }


    private fun getToast(message: String) {
        Toast.makeText(
            applicationContext,
            message,
            Toast.LENGTH_SHORT
        ).show();
    }
    fun onReturnLogin(view: android.view.View) {


        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        getToast(resources.getString(R.string.text_Login));
    }

    fun onTerms(view: android.view.View) {
        AlertDialog.Builder(this)
            .setTitle(resources.getString(R.string.test_TermsLink))
            .setMessage(resources.getString(R.string.test_TermsMessage))
            .setPositiveButton(resources.getString(R.string.test_ok),positiveButton)
            .setNegativeButton(resources.getString(R.string.test_cancel),negativeButton)
            .create().show();

    }

    val positiveButton={ _: DialogInterface, _:Int->
        stTerms!!.setChecked(true);
    }

    val negativeButton={ _: DialogInterface, _:Int->
        stTerms!!.setChecked(false);
    }


}


        fun onRegis(view: android.view.View) {}


