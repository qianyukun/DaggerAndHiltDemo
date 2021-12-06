package com.qyk.daggerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var cat: Cat
    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainComponent.inject(this)

        text.text = cat.name
        android.util.Log.e("xxxx", "$cat")
        val userById = userRepository.getUserById("1234")
        android.util.Log.e("xxxx", "$userById")
    }
}