package com.asi.projectmanag.activities

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.asi.projectmanag.databinding.ActivitySignInBinding
import com.asi.projectmanag.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set a window to fullscreen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        //use your specific font
        val typeFace : Typeface = Typeface.createFromAsset(assets, "Facon.ttf")
        binding?.tvtitle?.typeface  = typeFace

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, IntroActivity::class.java))
        }, 3000)

    }

    override fun getViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }
}