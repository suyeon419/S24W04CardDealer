package kr.ac.kumoh.ce.s20220822.s24w04carddealer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import kr.ac.kumoh.ce.s20220822.s24w04carddealer.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        mainBinding.btn1Deal.setOnClickListener {
//            Log.i("Card!!!!!!!!!!!!!!!!!!!", getCardName(32))
//            Log.i("Card!!!!!!!!!!!!!!!!!!!", R.drawable.c_2_of_clubs.toString())
//            Log.i("Card!!!!!!!!!!!!!!!!!!!", R.drawable.c_2_of_diamonds.toString())

            val c = IntArray(5)
            val res = IntArray(5)

            //for (i in 0..4)
            //for (i in 0 until 5)
            //for (i in 0 until c.size)
            for (i in c.indices) {
                c[i] = Random.nextInt(52)

                Log.i("Test", "${c[i]} : " +
                        "${getCardName(c[i])}")

                res[i] = resources.getIdentifier(
                    getCardName(c[i]),
                    "drawable",
                    packageName
                )
            }

            mainBinding.imgCard1.setImageResource(res[0])
            mainBinding.imgCard2.setImageResource(res[1])
            mainBinding.imgCard3.setImageResource(res[2])
            mainBinding.imgCard4.setImageResource(res[3])
            mainBinding.imgCard5.setImageResource(res[4])
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun getCardName(c: Int): String{
        val shape = when(c/13){
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }

        val number = when(c%13){
            0 -> "ace"
            in 1..9 -> (c % 13 + 1).toString()
            10 -> "jack"
            11 -> "queen"
            12 -> "king"
            else -> "error"
        }

        return "c_${number}_of_${shape}"
    }

    override fun onStart() {
        super.onStart()

        Log.i("Lifecycle!!!!!!!!!!!!", "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.i("Lifecycle!!!!!!!!!!1", "onResume")
    }
    override fun onPause() {
        super.onPause()

        Log.i("Lifecycle!!!!!!!!!!1", "onPause")
    }
    override fun onStop() {
        super.onStop()

        Log.i("Lifecycle!!!!!!!!!!1", "onStop")
    }
}