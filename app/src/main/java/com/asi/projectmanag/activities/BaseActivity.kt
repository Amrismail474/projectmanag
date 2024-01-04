package com.asi.projectmanag.activities

import android.app.Dialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.TextView
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.asi.projectmanag.R
import com.asi.projectmanag.databinding.ActivityBaseBinding
import com.google.android.material.snackbar.Snackbar

import com.google.firebase.auth.FirebaseAuth

open class BaseActivity<B:ViewBinding> : AppCompatActivity() {
    var doublebackpressedtoexitpressedonce = false
    private lateinit var mprogressdialog : Dialog
    var binding : B? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding?.root)
    }

    protected open fun getViewBinding(): B {
        throw NotImplementedError("Subclasses must implement getViewBinding()")
    }




    fun showProgressBarDialog(text:String){
        mprogressdialog = Dialog(this)

        mprogressdialog.setContentView(R.layout.dialog_custom_progressbar)
        var textview = mprogressdialog.findViewById<TextView>(R.id.tvprogressbar)
        textview.text = text

        mprogressdialog.show()
    }

    fun dismissProgressBarDialog(){
        mprogressdialog.dismiss()
    }

    fun getCurrentUserId():String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }

    fun dounleBactToExit(){

        if(doublebackpressedtoexitpressedonce){
            super.onBackPressed()
            return
        }

        this.doublebackpressedtoexitpressedonce = true
        Toast.makeText(this, "Press once more to exit", Toast.LENGTH_LONG).show()

        Handler().postDelayed({
            doublebackpressedtoexitpressedonce =false
        }, 2000)

    }

    fun showErrorSnackBar(message: String){
       val snackbar= Snackbar.make(findViewById(android.R.id.content), message,
           Snackbar.LENGTH_LONG)
        val snackBarView = snackbar.view
        snackBarView.setBackgroundColor(Color.RED)
        snackbar.show()
    }

}