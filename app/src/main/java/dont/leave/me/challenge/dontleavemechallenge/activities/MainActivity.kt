package dont.leave.me.challenge.dontleavemechallenge.activities

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import dont.leave.me.challenge.dontleavemechallenge.R
import dont.leave.me.challenge.dontleavemechallenge.databinding.ActivityMainBinding
import dont.leave.me.challenge.dontleavemechallenge.fragments.QuestionPageFragment
import dont.leave.me.challenge.dontleavemechallenge.interfaces.btnClicked


private const val NUM_PAGES = 20

class MainActivity : FragmentActivity(), btnClicked {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        viewPager = binding.pager
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
        initListeners();
    }

    private fun initListeners() {
//        binding.btnNext.setOnClickListener {
//            if (viewPager.currentItem == NUM_PAGES) {
//                Toast.makeText(this, "Thats all", Toast.LENGTH_LONG).show();
//
//            } else {
//                viewPager.currentItem = viewPager.currentItem + 1
//            }
//        }
//
//        binding.btnPrevious.setOnClickListener{
//            if (viewPager.currentItem == 0) {
//                Toast.makeText(this, "No More", Toast.LENGTH_LONG).show();
//            } else {
//                // Otherwise, select the previous step.
//                viewPager.currentItem = viewPager.currentItem - 1
//            }
//        }
    }


    override fun onBackPressed() {
//        if (viewPager.currentItem == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed()
//        } else {
//            // Otherwise, select the previous step.
//            viewPager.currentItem = viewPager.currentItem - 1
//        }

      //  TODO("Exit the app")
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = QuestionPageFragment(position)
    }

    override fun moveNext() {
        if (viewPager.currentItem == NUM_PAGES) {
                Toast.makeText(this, "Thats all", Toast.LENGTH_LONG).show();
            } else {
                viewPager.currentItem = viewPager.currentItem + 1
            playSound()
            }

    }

    fun playSound() {

        var resId = getResources().getIdentifier(
            "dontleaveme" , "raw", packageName)

        val mediaPlayer = MediaPlayer.create(this, resId)
        mediaPlayer.start()
    }
}