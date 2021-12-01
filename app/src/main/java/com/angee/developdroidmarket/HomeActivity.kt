package com.angee.developdroidmarket


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.facebook.login.LoginManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC,
    GOOGLE,
    FACEBOOK
}

class HomeActivity : AppCompatActivity() {

    private lateinit var appbarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->

        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        appbarConfiguration =
            AppBarConfiguration(setOf(R.id.nav_todo, R.id.nav_about, R.id.nav_product), drawerLayout)

        setupActionBarWithNavController(navController, appbarConfiguration)

        navView.setupWithNavController(navController)

        //setup Login
        title=resources.getString(R.string.text_Home)
        val bundle =intent.extras
        val email=bundle?.getString("email")
        val provider=bundle?.getString("provider")

        navView.getHeaderView(0).findViewById<TextView>(R.id.emailTextView).text = email;
        navView.getHeaderView(0).findViewById<TextView>(R.id.providerTextView).text=provider;

        //Save Data
        val prefs=getSharedPreferences(resources.getString(R.string.preds_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("provider",provider)
        prefs.apply()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        return navController.navigateUp(appbarConfiguration) || super.onSupportNavigateUp()
    }
    fun onSignoff(view: android.view.View) {
        val prefs=getSharedPreferences(resources.getString(R.string.preds_file), Context.MODE_PRIVATE).edit()
        prefs.clear()
        prefs.apply()



        FirebaseAuth.getInstance().
        signOut()
        onBackPressed()

    }
}



