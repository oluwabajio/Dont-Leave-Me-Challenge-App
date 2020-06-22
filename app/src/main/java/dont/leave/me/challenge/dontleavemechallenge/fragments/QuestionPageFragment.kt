package dont.leave.me.challenge.dontleavemechallenge.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.snackbar.Snackbar
import dont.leave.me.challenge.dontleavemechallenge.R
import dont.leave.me.challenge.dontleavemechallenge.activities.MainActivity
import dont.leave.me.challenge.dontleavemechallenge.models.Question
import dont.leave.me.challenge.dontleavemechallenge.models.Supplier
import dont.leave.me.challenge.dontleavemechallenge.databinding.FragmentQuestionPageBinding
import dont.leave.me.challenge.dontleavemechallenge.interfaces.btnClicked
import dont.leave.me.challenge.dontleavemechallenge.utils.AppUtils.utils.hideKeyboard

class QuestionPageFragment(val position: Int) : Fragment() {
    lateinit var binding: FragmentQuestionPageBinding
    lateinit var question: Question

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionPageBinding.inflate(inflater,container,false)
        val view = binding.root

        //Toast.makeText(activity, "position is "+ position, Toast.LENGTH_LONG).show()
        question = Supplier.questions[position]

        initViews(question);
        initAds()

        return view

    }

    override fun onResume() {
        super.onResume()
        initAnimations()
    }
    private fun initAnimations() {
        YoYo.with(Techniques.Tada).duration(700).playOn(binding.ly1);
        YoYo.with(Techniques.Tada).duration(700).playOn(binding.ly2);
        YoYo.with(Techniques.RubberBand).duration(700).delay(800).playOn(binding.tvYourScore);
    }

    private fun initAds() {
        MobileAds.initialize(activity,"ca-app-pub-9562015878942760~3516654827")

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun initViews(question: Question) {

        binding.img1.setImageResource(question.firstWord)
        binding.img2.setImageResource(question.secondWord)

        binding.tvWordLength.text = "Word Length: "+ question.answer.length
        binding.tvYourScore.text = "Your Score: "+position*100

        binding.btnNext.setOnClickListener{

            var inputText: String = binding.edInput.text.toString()
            inputText = inputText.replace(" ", "")
            inputText = inputText.replace("-", "")



            if (inputText.equals(question.answer, ignoreCase = true)) {
                view?.let { activity?.hideKeyboard(it) }
                var mainActivity: MainActivity = activity as MainActivity
                mainActivity.moveNext()

            } else {
                YoYo.with(Techniques.Tada).duration(700).repeat(1).playOn(binding.btnNext);
                showSnackBar("Not Correct!! Try Again")

            }
        }

    }

//    fun Context.hideKeyboard(view: View) {
//        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//    }

    fun showSnackBar(text: String) {
        Snackbar.make(binding.edInput, text, Snackbar.LENGTH_LONG)
            .show()
    }
}