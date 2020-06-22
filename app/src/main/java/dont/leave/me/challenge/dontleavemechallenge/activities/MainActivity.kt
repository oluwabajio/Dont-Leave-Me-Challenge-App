package dont.leave.me.challenge.dontleavemechallenge.activities


import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import dont.leave.me.challenge.dontleavemechallenge.R
import dont.leave.me.challenge.dontleavemechallenge.databinding.ActivityMainBinding
import dont.leave.me.challenge.dontleavemechallenge.fragments.QuestionPageFragment
import dont.leave.me.challenge.dontleavemechallenge.interfaces.btnClicked
import dont.leave.me.challenge.dontleavemechallenge.utils.AppUtils.utils.hideKeyboard


private const val NUM_PAGES = 20
private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f

class MainActivity : FragmentActivity(), btnClicked {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var  mediaPlayer: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




        viewPager = binding.pager
        viewPager.setPageTransformer(ZoomOutPageTransformer())
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter



    }



    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {

            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }

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

        playSound()
        showSuccessDialog()

    }

    fun goToNextPage() {
        if (viewPager.currentItem == NUM_PAGES) {
            Toast.makeText(this, "Thats all", Toast.LENGTH_LONG).show();
        } else {
            viewPager.currentItem = viewPager.currentItem + 1

        }
    }

    fun playSound() {
        mediaPlayer  = MediaPlayer.create(this, R.raw.success)
            mediaPlayer.start()




    }

    private fun showSuccessDialog() {

        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)

        //then we will inflate the custom alert dialog xml that we created

        val dialogView: View =
            LayoutInflater.from(this).inflate(R.layout.popup_view_success, viewGroup, false)

        //Now we need an AlertDialog.Builder object
        val builder =  AlertDialog.Builder(this)

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView)

     //finally creating the alert dialog and displaying it
        val alertDialog = builder.create()
        alertDialog.window?.attributes?.windowAnimations = R.style.animationdialog

        val btnContinue = dialogView.findViewById<Button>(R.id.btn_next)
        btnContinue.setOnClickListener {
            goToNextPage()
            alertDialog.dismiss()
        }
        alertDialog.show()
    }







    class ZoomOutPageTransformer : ViewPager2.PageTransformer {

        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }

}