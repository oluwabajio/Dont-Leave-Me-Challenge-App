package dont.leave.me.challenge.dontleavemechallenge.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class AppUtils {

   object utils {
       fun Context.hideKeyboard(view: View) {
           val inputMethodManager =
               getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
           inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
       }
   }
}