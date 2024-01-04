package com.asi.projectmanag.activities

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.asi.projectmanag.databinding.ActivityIntroBinding
import com.asi.projectmanag.databinding.ActivitySignInBinding

class IntroActivity : BaseActivity<ActivityIntroBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set a woindow to fullscreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val typeface : Typeface = Typeface.createFromAsset(assets, "Facon.ttf")
        binding?.tvIntro?.typeface = typeface

        binding?.btnSignup?.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding?.btnSignin?.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }
    override fun getViewBinding(): ActivityIntroBinding {
        return ActivityIntroBinding.inflate(layoutInflater)
    }
}