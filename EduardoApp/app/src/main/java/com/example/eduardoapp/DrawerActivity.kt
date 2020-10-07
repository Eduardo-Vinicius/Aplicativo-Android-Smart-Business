package com.example.eduardoapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.eduardoapp.MainActivity
import com.example.eduardoapp.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.toolbar.*


open class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var drawerLayout: DrawerLayout? = null
    var navView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun configuraMenuLateral() {
        var toogle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_closed
        )
        toogle.isDrawerIndicatorEnabled = true
        drawerLayout?.addDrawerListener(toogle)
        toogle.syncState()

        navView?.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        var intent = Intent(this, Funcionario::class.java)

        when(item.itemId) {
            R.id.nav_funcionario -> {
                intent.putExtra("title", "Funcionário")
                startActivity(intent)
            }
            R.id.nav_config -> {
                intent.putExtra("title", "Configurações")
                startActivity(intent)
            }
            R.id.nav_bicicleta -> {
                intent.putExtra("title", "Bicicleta")
                startActivity(intent)
            }


        }
        drawerLayout?.closeDrawer(GravityCompat.START)
        return true
    }

}