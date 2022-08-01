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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var bindingActivity: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingActivity = DataBindingUtil.setContentView(this, R.layout.activity_main)

        hideSystemUI()
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
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
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

//        // костыль для повторного открытия диалога если перевернули экран
//        mainActivityViewModel.status = true
//        dialog.setOnDismissListener {
//            mainActivityViewModel.status = false
//        }
//
//        bindingDialog.btNo.setOnClickListener {
//            dialog.dismiss()
//            dialog.cancel()
//        }
//        bindingDialog.btYes.setOnClickListener{
//            dialog.dismiss()
//            finish()
//        }
    }

}