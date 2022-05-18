package com.dev_marinov.nbadata.presentation

import android.app.Dialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.transition.*
import com.airbnb.lottie.LottieAnimationView
import com.dev_marinov.nbadata.CircularRevealTransition
import com.dev_marinov.nbadata.R

class MainActivity : AppCompatActivity() {

    var animationView: LottieAnimationView? = null // анимация на старте

    lateinit var viewGroup: ViewGroup
    lateinit var btNo: Button
    lateinit var btYes: Button

    var mySavedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mySavedInstanceState = savedInstanceState

        supportActionBar?.hide() // скрыть экшенбар
        setWindow()
        hideSystemUI() // сетинг для фул скрин по соответствующему сдк
        animationView = findViewById(R.id.animationView)

        // берем белый frameLayout, который растянут во весь экран и который находиться в activity_main
        viewGroup = findViewById(R.id.fl_viewGroup)

        showScene1() // сцена 1 старт с анимации баскетболиста
    }

    fun showScene1(){

        val runnable1 = Runnable{ // анимация шарики при старте
            animationView?.playAnimation()
        }
        Handler(Looper.getMainLooper()).postDelayed(runnable1, 0)
        animationView?.cancelAnimation()

        // запускаем showScene2 через 1.2сек
        val runnable = Runnable {
            showScene2()
        }
        Handler(Looper.getMainLooper()).postDelayed(runnable, 1200)

        val runnable2 = Runnable{ // задержка 2 сек перед переходом во FragmentList
            if(mySavedInstanceState == null) {

            val fragmentTabViewPager2 = FragmentTabViewPager2()
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.llFragViewPager2, fragmentTabViewPager2)
            fragmentTransaction.commit()
            }
        }
        Handler(Looper.getMainLooper()).postDelayed(runnable2, 2000)
    }

    fun showScene2(){
        val root = layoutInflater.inflate(R.layout.scene_animation, viewGroup, false) as? ViewGroup
        if(root != null) {
            val scene = Scene(viewGroup, root)
            val transition = getScene2Transition()
            TransitionManager.go(scene, transition)
        }
    }

    fun getScene2Transition() : Transition {
        val transitionSet: TransitionSet = TransitionSet()
        // прменяем созданный класс анимации CircularRevealTransition3
        val circularRevealTransition: CircularRevealTransition = CircularRevealTransition()
        circularRevealTransition.addTarget(R.id.cl_scene)
        circularRevealTransition.setStartDelay(150)
        circularRevealTransition.setDuration(800)
        transitionSet.addTransition(circularRevealTransition)

        return transitionSet
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

    override fun onBackPressed() {
        //  если flag false значит работаем с backstack fragment
        supportFragmentManager.popBackStack() // удаление фрагментов из транзакции
        myAlertDialog() // метод реализации диалога с пользователем закрыть приложение или нет
    }

    fun myAlertDialog() {
        val dialog: Dialog = Dialog(this@MainActivity)
        dialog.setContentView(R.layout.windows_alertdialog)
        dialog.setCancelable(false)
        dialog.show()

        btNo = dialog.findViewById<Button>(R.id.btNo)
        btYes = dialog.findViewById<Button>(R.id.btYes)

        btNo.setOnClickListener {
            dialog.dismiss()
            dialog.cancel()
        }
        btYes.setOnClickListener{
            dialog.dismiss()
            finish()
        }
    }

}