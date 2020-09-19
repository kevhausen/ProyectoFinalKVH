package com.example.proyectofinalkvh.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.viewmodel.MovieVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            initializeFragment(R.id.frameLayout,PopularMovieFragment.newInstance())

            favorites_button.setOnClickListener {
                //inicializar fragmento favoritos
            }

    }





    fun initializeFragment(fragmentId:Int,fragment:Fragment,backStackName:String){
        supportFragmentManager.beginTransaction().addToBackStack(backStackName).replace(fragmentId,fragment).commit()
    }
    fun initializeFragment(fragmentId:Int,fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(fragmentId,fragment).commit()
    }
    fun initializeFragment(fragment: Fragment,backStackName: String){
        supportFragmentManager.beginTransaction().addToBackStack(backStackName).replace(R.id.frameLayout,fragment).commit()
    }
}