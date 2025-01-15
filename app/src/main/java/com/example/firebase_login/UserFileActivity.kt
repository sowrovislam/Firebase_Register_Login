package com.example.firebase_login

import android.content.Intent

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebase_login.databinding.ActivityUserFileActavatyBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class UserFileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var binding: ActivityUserFileActavatyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserFileActavatyBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = Firebase.auth


       binding.signOutButton.setOnClickListener {

           if (auth.currentUser!=null){


               auth.signOut()
               val intent = Intent(this, LoginScreen::class.java)
               startActivity(intent)


           }



        }
















    }



    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        finish()
    }
}