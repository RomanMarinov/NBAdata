package com.dev_marinov.nbadata

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {


    lateinit var hashMap: HashMap<Int, ObjectListPlayers>
    lateinit var hashMapTeams: HashMap<Int, ObjectListTeams>
    lateinit var hashMapGames: HashMap<Int, ObjectListGames>
    var lastTab: Int? = null
    var lastVisibleItem: Int? = null
    var lastVisibleItemTeams: Int? = null
    var lastVisibleItemGames: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hashMap = HashMap()
        hashMapTeams = HashMap()
        hashMapGames = HashMap()

        supportActionBar?.hide() // скрыть экшенбар
        setWindow()
        hideSystemUI() // сетинг для фул скрин по соответствующему сдк

        val fragmentTabViewPager2 = FragmentTabViewPager2()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.llFragViewPager2, fragmentTabViewPager2)
        fragmentTransaction.commit()
    }

    fun setWindow() {
        val window = window

        // FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS Флаг, указывающий, что это Окно отвечает за отрисовку фона для системных полос.
        // Если установлено, системные панели отображаются с прозрачным фоном, а соответствующие области в этом окне заполняются
        // цветами, указанными в Window#getStatusBarColor()и Window#getNavigationBarColor().
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent); // прозрачный статус бар
        window.navigationBarColor  = ContextCompat.getColor(this, android.R.color.black); // черный бар навигации
    }

    private fun hideSystemUI() {
        // если сдк устройства больше или равно сдк приложения
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else { // иначе
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    //or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    //or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // Hide the nav bar and status bar
                    //or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    //or View.SYSTEM_UI_FLAG_FULLSCREEN
                    )
        }
    }


}