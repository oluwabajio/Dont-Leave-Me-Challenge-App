package dont.leave.me.challenge.dontleavemechallenge.models

import dont.leave.me.challenge.dontleavemechallenge.R
import kotlinx.android.synthetic.main.activity_main.view.*

data class Question (var firstWord: Int, var secondWord: Int, var answer: String, var hint:String)

object Supplier {
    val questions = listOf<Question>(
        Question(
            R.drawable.corn,
            R.drawable.flakes,
            "Cornflakes",
            ""
        ),

        Question(
            R.drawable.chair,
            R.drawable.man,
            "Chairman",
            ""
        ),
        Question(
            R.drawable.hot,
            R.drawable.dog,
            "Hotdog",
            ""
        ),
        Question(
            R.drawable.ear,
            R.drawable.ring,
            "Earring",
            ""
        ),
        Question(
            R.drawable.lip,
            R.drawable.stick,
            "Lipstick",
            ""
        ),
        Question(
            R.drawable.school,
            R.drawable.fees,
            "Schoolfees",
            ""
        ),
        Question(
            R.drawable.bat,
            R.drawable.man,
            "batman",
            ""
        ),
        Question(
            R.drawable.cold,
            R.drawable.stone,
            "coldstone",
            ""
        ),
        Question(
            R.drawable.t,
            R.drawable.shirt,
            "tshirt",
            ""
        ),
        Question(
            R.drawable.school,
            R.drawable.bus,
            "Schoolbus",
            ""
        ),
        Question(
            R.drawable.face,
            R.drawable.book,
            "Facebook",
            ""
        ),
        Question(
            R.drawable.game,
            R.drawable.boy,
            "Gameboy",
            ""
        ),
        Question(
            R.drawable.x,
            R.drawable.box,
            "xbox",
            ""
        ),
        Question(
            R.drawable.tea,
            R.drawable.bag,
            "Teabag",
            ""
        ),
        Question(
            R.drawable.sam,
            R.drawable.song,
            "Samsung",
            ""
        ),
        Question(
            R.drawable.butter,
            R.drawable.fly,
            "Butterfly",
            ""
        ),
        Question(
            R.drawable.face,
            R.drawable.time,
            "Facetime",
            ""
        ),
        Question(
            R.drawable.watch,
            R.drawable.man,
            "Watchman",
            ""
        ),
        Question(
            R.drawable.pan,
            R.drawable.cake,
            "Pancake",
            ""
        ),
        Question(
            R.drawable.fanta,
            R.drawable.stick,
            "Fantastic",
            ""
        )


    )


}