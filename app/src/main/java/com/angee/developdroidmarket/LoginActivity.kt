package com.angee.developdroidmarket

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.facebook.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity() {

    private var emailEditText: EditText? = null
    private var passwordEditTex: EditText? = null

    private val GOOGLE_SING_IN = 200
    private val callbackManager = CallbackManager.Factory.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Theme_DevelopDroidMarket)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString(
            "Firebase",
            "Firebase"
        )
        analytics.logEvent("Godevelopdroidmarket", bundle)

        emailEditText = findViewById(R.id.editUserName);
        passwordEditTex = findViewById(R.id.passwordEditTex);

        title = resources.getString(R.string.text_Login)


        session()

    }

    override fun onStart() {
        super.onStart()

        val prefs =
            getSharedPreferences(resources.getString(R.string.preds_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email == null && provider == null) {
            var loginLayout = findViewById<LinearLayout>(R.id.loginLayaut);
            loginLayout.visibility = View.VISIBLE
        }


    }

    private fun session() {
        val prefs =
            getSharedPreferences(resources.getString(R.string.preds_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null) {
            var loginLayout = findViewById<LinearLayout>(R.id.loginLayaut)
            loginLayout.visibility = View.INVISIBLE

            showHome(email, ProviderType.valueOf(provider))
        }

    }
    private fun showHome(username: String, provider: ProviderType) {

        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", username)
            putExtra("provider", provider.toString())
        }

        startActivity(homeIntent)

        getToast(resources.getString(R.string.text_welcome));
    }

    fun onAccount(view: android.view.View) {
        val formIntent = Intent(this, Form::class.java)
        startActivity(formIntent)
        getToast(resources.getString(R.string.text_register));
    }



    private fun getToast(message: String) {
        Toast.makeText(
            applicationContext,
            message,
            Toast.LENGTH_LONG
        ).show();
    }


    fun OnLogin(view: android.view.View) {
        var username = emailEditText!!.text.toString();
        var password = passwordEditTex!!.text.toString();

        if (username.isNotEmpty() && password.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(username, ProviderType.BASIC)
                    } else {
                        getToast(resources.getString(R.string.text_errorAuth));
                    }
                }

        } else {
            getToast(resources.getString(R.string.text_errorlogin));
        }

    }

    fun OnGoogle(view: android.view.View) {
        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(resources.getString(R.string.default_web_client_id2))
            .requestEmail().build()

        val googleClient = GoogleSignIn.getClient(this, googleConf)
        googleClient.signOut()
        startActivityForResult(googleClient.signInIntent, GOOGLE_SING_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
       super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == GOOGLE_SING_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credencial = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credencial)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                showHome(account.email ?: "", ProviderType.GOOGLE)
                            } else {
                                getToast(resources.getString(R.string.text_errorAuth));
                            }
                        }
                }
            } catch (e: ApiException) {
                getToast(resources.getString(R.string.text_errorAuth)+e.message.toString());
            }


        }
    }

    fun onRecovery(view: android.view.View) {
        val recoveryIntent = Intent(this, RecoverActivity::class.java)
        startActivity(recoveryIntent)
        getToast(resources.getString(R.string.test_Recovery));
    }
}




