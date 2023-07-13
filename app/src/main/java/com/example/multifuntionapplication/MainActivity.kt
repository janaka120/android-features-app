package com.example.multifuntionapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    lateinit var speakBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        speakBtn = findViewById(R.id.buttonSpeak)

        val fragmentManager: FragmentManager = supportFragmentManager

        speakBtn.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            val speakToTextFragment = SpeakToTextFragment()
            fragmentTransaction.replace(R.id.fragmentContainerView, speakToTextFragment)
            fragmentTransaction.commit()
        }
    }
}