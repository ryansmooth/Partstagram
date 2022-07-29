package com.example.partstagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.partstagram.fragments.ComposeFragment
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        if (ParseUser.getCurrentUser() != null){
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            val username = findViewById<EditText>(R.id.Username).text.toString()
            val password = findViewById<EditText>(R.id.Password).text.toString()
            loginUser(username, password)
        }

        findViewById<Button>(R.id.Sbutton).setOnClickListener {
            val username = findViewById<EditText>(R.id.Username).text.toString()
            val password = findViewById<EditText>(R.id.Password).text.toString()
            signUpUser(username, password)
        }
    }

   private fun signUpUser(username: String, password: String) {
       // Create the ParseUser
       val user = ParseUser()

// Set fields for the user to be created
       user.setUsername(username)
       user.setPassword(password)

       user.signUpInBackground { e ->
           if (e == null) {
               // Hooray! Let them use the app now.
           } else {
               e.printStackTrace()
           }
       }
   }


    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(
            username, password, ({ user, e ->
                if (user != null) {
                    Log.i(TAG, "successful")
                    goToMainActivity()

                } else {
                    e.printStackTrace()
                }

            })
        )
    }

    private fun goToMainActivity(){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object{
        const val TAG = "LoginActivity"
    }
}