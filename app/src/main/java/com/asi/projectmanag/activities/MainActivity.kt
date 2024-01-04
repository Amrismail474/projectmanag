package com.asi.projectmanag.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asi.projectmanag.R
import com.asi.projectmanag.databinding.ActivityMainBinding
import com.asi.projectmanag.databinding.ActivitySignInBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }


}