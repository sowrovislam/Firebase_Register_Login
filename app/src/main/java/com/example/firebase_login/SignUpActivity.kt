package com.example.firebase_login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebase_login.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
// Initialize Firebse Auth

        auth=FirebaseAuth.getInstance()
//        auth = Firebase.auth


        binding.SignUp.setOnClickListener {

            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finish()

        }

        binding.Register.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            // Get text from EditText fields
            val email = binding.Email.text.toString().trim()
            val username = binding.username.text.toString().trim()
            val password = binding.Password.text.toString().trim()
            val repeatPassword = binding.Repeatpassword.text.toString().trim()

            // Check for empty fields
            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()

            }

            // Check if passwords match
            if (password != repeatPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()

            }

            // Create a new user with Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    binding.progress.visibility = View.GONE
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                        binding.progress.visibility = View.GONE
                        // Navigate to login screen
                        val intent = Intent(this, LoginScreen::class.java)
                        startActivity(intent)

                        // Optional: Finish the current activity
                        finish()
                    } else {
                        binding.progress.visibility = View.VISIBLE
                        // Handle registration failure
                        val errorMessage = task.exception?.localizedMessage ?: "Registration failed"
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }

}