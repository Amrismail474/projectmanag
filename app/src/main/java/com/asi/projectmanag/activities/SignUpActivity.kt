package com.asi.projectmanag.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.asi.projectmanag.R
import com.asi.projectmanag.databinding.ActivitySignUpBinding
import com.asi.projectmanag.firebase.FirestoreClass
import com.asi.projectmanag.models.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set a window to fullscreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setToolbar()
        binding?.btnSignin?.setOnClickListener {
            registerUsers()
        }
    }

    override fun getViewBinding(): ActivitySignUpBinding {
        return ActivitySignUpBinding.inflate(layoutInflater)
    }


    private fun setToolbar(){
        setSupportActionBar(binding?.toolbarSignup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "SIGN UP"

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_backicon)
        binding?.toolbarSignup?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun registerUsers(){
        val name = binding?.tvname?.text.toString().trim{ it <= ' '}
        val email = binding?.tvemail?.text.toString().trim{ it <= ' '}
        val password = binding?.tvpassword?.text.toString().trim{ it <= ' '}

        if(validateForm(name, email, password)){
            showProgressBarDialog("Please Wait")
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Success", name)
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        val registeredEmail = firebaseUser.email!!

                        val user = User(firebaseUser.uid, name,registeredEmail)
                        Log.d("user", "$user")

                        //this is where I implemented the FirestoreClass
                        FirestoreClass().registerUser(this, user)
                        Log.d("Success2", email)

                        startActivity(Intent(this, IntroActivity::class.java))

                        Toast.makeText(
                            this, "$name, You're Registered now, You Can Now Sign In", Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Log.d("Ammaar", task.exception!!.message.toString())
                        Toast.makeText(this, task.exception!!.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }

    }

    private fun validateForm(name:String, email:String, password:String):Boolean{
        return when{
            TextUtils.isEmpty(name)->{
                showErrorSnackBar("Please enter a name")
                false
            }

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

    fun userRegisteredSuccess() {
        dismissProgressBarDialog()
        Toast.makeText(
            this, "you have succesfully registered ", Toast.LENGTH_LONG
        ).show()
        FirebaseAuth.getInstance().signOut()
        finish()
    }
}