package com.gamerguide.android.starterapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gamerguide.android.starterapp.databinding.ActivityMainBinding
import com.gamerguide.android.starterapp.fragments.FirstFragment
import com.gamerguide.android.starterapp.helpers.BlurHelper
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlin.random.Random


@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {
    private var blurHelper: BlurHelper? = null
    private lateinit var binding: ActivityMainBinding

    private var firstFragmentsCount = 0;
    private var secondFragmentsCount = 0;


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    //Restore the data stored in Bundle during configuration changes and implement your custom logic
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Build a ViewPump for hooking into font
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/sourcesanspro.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )

        //Create View Binding instance for the current activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        blurHelper = BlurHelper()



        if (savedInstanceState == null) {
            val bundle = Bundle()
            bundle.putString("name", "First Fragment")
            bundle.putInt("count", supportFragmentManager.backStackEntryCount)
            val fragmentID = Random.nextInt(0,1000).toString()
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, FirstFragment::class.java, bundle,
                    "firstFragment$firstFragmentsCount"
                )
                .addToBackStack(fragmentID)
                .commit()


        }

        //Add a new Fragment to the Back Stack and log the count
        binding.first.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", "First Fragment")
            bundle.putInt("count", supportFragmentManager.backStackEntryCount)
            val fragmentID = Random.nextInt(0,1000).toString()
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, FirstFragment::class.java, bundle,
                    "firstFragment$firstFragmentsCount"
                )
                .addToBackStack(fragmentID)
                .commit()


        }

        //Add a new Fragment to the Back Stack and log the count
        binding.second.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", "Second Fragment")
            bundle.putInt("count", supportFragmentManager.backStackEntryCount)
            val fragmentID = Random.nextInt(0,1000).toString()
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, FirstFragment::class.java, bundle,
                    "secondFragment$secondFragmentsCount"
                )
                .addToBackStack(fragmentID)
                .commit()


        }

        //Remove the topmost fragment from backstack when pop is clicked
        binding.pop.setOnClickListener {
            supportFragmentManager.popBackStack()
        }

        //Remove all the fragment from backstack when clear is clicked
        binding.clear.setOnClickListener {
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                supportFragmentManager.popBackStack()
            }
        }

    }


    // OnResume is called when the app recieve focus, Do all UI related work here
    override fun onResume() {
        super.onResume()

        blurHelper!!.setupImageBlurBackground(this, binding.background)
        binding.title.text = "MAIN ACTIVITY"

        //Reload a new background theme when use clicks the refresh button
        binding.reload.setOnClickListener {
            blurHelper!!.setupImageBlurBackground(
                this,
                binding.background,
                true
            )
        }



    }

    // Store any data in this bundle when app configuration change occurs
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }







}