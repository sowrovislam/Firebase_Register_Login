package com.example.firebase_login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebase_login.databinding.ActivityLoginScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginScreen : AppCompatActivity() {
    lateinit var binding: ActivityLoginScreenBinding

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginScreenBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth=FirebaseAuth.getInstance()

       



        binding.buttonSignup.setOnClickListener {


            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()

        }


        binding.buttonlogin.setOnClickListener {

            val userName = binding.userName.text.toString()
            val password = binding.passwrd.text.toString()



            if (userName.isEmpty() || password.isEmpty()) {

                Toast.makeText(this, "PlaseFill All The Data", Toast.LENGTH_SHORT).show()

            } else {


                auth.signInWithEmailAndPassword(userName, password)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {

                            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, UserFileActivity::class.java)
                            startActivity(intent)


                        } else {

                            Toast.makeText(
                                this,
                                "Login Err!${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()


                        }

                    }


            }
        }

    }


//    public override fun onStart() {
//          super.onStart()
//        val currentUser: FirebaseUser? = auth.currentUser
//        if (currentUser!=null){
//            val intent = Intent(this, UserFileActivity::class.java)
//            startActivity(intent)
//        }
//    }



    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }
}