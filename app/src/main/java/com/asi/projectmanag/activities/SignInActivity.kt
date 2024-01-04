package com.asi.projectmanag.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.asi.projectmanag.R
import com.asi.projectmanag.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.core.content.ContextCompat


class SignInActivity : BaseActivity<ActivitySignInBinding>() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth


        // set a window to fullscreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setToolbar()

        binding?.btnSignin?.setOnClickListener {
            signIn()
        }
    }

    override fun getViewBinding(): ActivitySignInBinding {
        return ActivitySignInBinding.inflate(layoutInflater)
    }


    private fun setToolbar(){
        setSupportActionBar(binding?.toolbarSignin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "SIGN IN"

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_backicon)
        binding?.toolbarSignin?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun signIn(){
        val email = binding?.tvemail?.text.toString().trim{ it <= ' '}
        val password = binding?.tvpassword?.text.toString().trim{ it <=' '}
        if(validateForm(email, password)){
            showProgressBarDialog("Please Wait")
            auth
                .signInWithEmailAndPassword(email, password).addOnCompleteListener { task->

                    dismissProgressBarDialog()
                    if (task.isSuccessful) {
                        val currentUser = auth.currentUser
                        Log.d("Success", currentUser.toString())
                        Toast.makeText(
                            this, "$currentUser, Welcome In", Toast.LENGTH_LONG
                        ).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    }else {
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
        }


        }

    private fun validateForm(email:String, password:String):Boolean{
        return when{

            TextUtils.isEmpty(email)->{
                showErrorSnackBar("Please enter a email")
                false
            }

            TextUtils.isEmpty(password)->{
                showErrorSnackBar("Please enter your password")
                false
            }
            else-> {
                true
            }
        }

    }

}