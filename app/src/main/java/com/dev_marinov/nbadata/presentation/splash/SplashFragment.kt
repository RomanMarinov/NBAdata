package com.dev_marinov.nbadata.presentation.splash

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.transition.Scene
import androidx.transition.Transition
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.dev_marinov.nbadata.CircularRevealTransition
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.databinding.FragmentSplashBinding
import com.dev_marinov.nbadata.presentation.viewpager2.ViewPager2Fragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return initInterFace(inflater, container)
    }

    private fun initInterFace(inflater: LayoutInflater, container: ViewGroup?): View{
        container?.let { container.removeAllViewsInLayout() }

        val orientation = requireActivity().resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        }

        setLayout()

        return binding.root
    }

    private fun setLayout(){

        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        // при создании макета проверяем статус был ли перед созданием макета открыт диалог
        // если да (true), значит запустим его снова
        if (viewModel.status) {
           // myAlertDialog()
        }

        lifecycleScope.launch(Dispatchers.Main) {
            showScene1() // старт с анимации баскетболиста
            showScene2()
            showViewPager2Fragment()
        }
    }

    private fun showScene1(){
        binding.animationView.playAnimation()
    }

    private suspend fun showScene2(){
        delay(1200)
        val root = layoutInflater.inflate(R.layout.scene_animation, binding.frameLayout, false) as? ViewGroup
        if(root != null) {
            val scene = Scene(binding.frameLayout, root)
            val transition = getScene2Transition()
            TransitionManager.go(scene, transition)
        }
    }

    private suspend fun showViewPager2Fragment() {
        delay(800)
        findNavController().navigate(R.id.action_splashFragment_to_viewPager2Fragment)
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

}