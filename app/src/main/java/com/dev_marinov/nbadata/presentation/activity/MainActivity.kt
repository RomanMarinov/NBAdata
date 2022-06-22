package com.dev_marinov.nbadata.presentation.activity

import android.app.Dialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.transition.*
import com.dev_marinov.nbadata.CircularRevealTransition
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.databinding.ActivityMainBinding
import com.dev_marinov.nbadata.databinding.WindowsAlertdialogBinding
import com.dev_marinov.nbadata.presentation.games.GamesViewModel
import com.dev_marinov.nbadata.presentation.viewpager2.ViewPager2Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var bindingActivity: ActivityMainBinding
    var mySavedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mySavedInstanceState = savedInstanceState

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        bindingActivity = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportActionBar?.hide() // скрыть экшенбар
        setWindow()
        hideSystemUI() // сетинг для фул скрин по соответствующему сдк

        // при создании макета проверяем статус был ли перед созданием макета открыт диалог
        // если да (true), значит запустим его снова
        if (mainActivityViewModel.status) {
            myAlertDialog()
        }

        lifecycleScope.launch(Dispatchers.Main) {
            showScene1() // старт с анимации баскетболиста
            showScene2()
            showViewPager2Fragment()
        }
    }

    private fun showScene1(){
        bindingActivity.animationView.playAnimation()
    }

    private suspend fun showScene2(){
        delay(1200)
        val root = layoutInflater.inflate(R.layout.scene_animation, bindingActivity.frameLayout, false) as? ViewGroup
        if(root != null) {
            val scene = Scene(bindingActivity.frameLayout, root)
            val transition = getScene2Transition()
            TransitionManager.go(scene, transition)
        }
    }

    private suspend fun showViewPager2Fragment() {
        delay(800)
        if(mySavedInstanceState == null) {

            val viewPager2Fragment = ViewPager2Fragment()
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.llFragViewPager2, viewPager2Fragment)
            fragmentTransaction.commit()
        }
    }

    private fun getScene2Transition() : Transition {
        val transitionSet = TransitionSet()
        // прменяем созданный класс анимации CircularRevealTransition3
        val circularRevealTransition = CircularRevealTransition()
        circularRevealTransition.addTarget(R.id.cl_scene)
        circularRevealTransition.startDelay = 150
        circularRevealTransition.duration = 800
        transitionSet.addTransition(circularRevealTransition)

        return transitionSet
    }

    private fun setWindow() {
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

    private fun myAlertDialog() {
        val bindingDialog: WindowsAlertdialogBinding = DataBindingUtil
            .inflate(LayoutInflater.from(this), R.layout.windows_alertdialog, null, false)

        val dialog = Dialog(this)
        dialog.setContentView(bindingDialog.root)
        dialog.setCancelable(true)
        dialog.show()

        // костыль для повторного открытия диалога если перевернули экран
        mainActivityViewModel.status = true
        dialog.setOnDismissListener {
            mainActivityViewModel.status = false
        }

        bindingDialog.btNo.setOnClickListener {
            dialog.dismiss()
            dialog.cancel()
        }
        bindingDialog.btYes.setOnClickListener{
            dialog.dismiss()
            finish()
        }
    }

}