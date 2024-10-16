package kr.ac.kumoh.ce.s20220822.s24w04carddealer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.ce.s20220822.s24w04carddealer.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val model = ViewModelProvider(this)[CardViewModel::class.java]
        model.cards.observe(this, Observer {
            val res = IntArray(5)

            model.cards.value!!.forEachIndexed { index, num ->
                res[index] = resources.getIdentifier(
                    getCardName(num),
                    "drawable",
                    packageName
                )
            }

            mainBinding.imgCard1.setImageResource(res[0])
            mainBinding.imgCard2.setImageResource(res[1])
            mainBinding.imgCard3.setImageResource(res[2])
            mainBinding.imgCard4.setImageResource(res[3])
            mainBinding.imgCard5.setImageResource(res[4])
        })


        mainBinding.btn1Deal.setOnClickListener {
            model.shuffle()

            /*model.cards.value!!.forEachIndexed { index, num ->
                res[index] = resources.getIdentifier(
                    getCardName(num),
                    "drawable",
                    packageName
                )
            }

            mainBinding.imgCard1.setImageResource(res[0])
            mainBinding.imgCard2.setImageResource(res[1])
            mainBinding.imgCard3.setImageResource(res[2])
            mainBinding.imgCard4.setImageResource(res[3])
            mainBinding.imgCard5.setImageResource(res[4])*/
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getCardName(c: Int) : String {
        val shape = when (c / 13) {
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }

        val number = when (c % 13) {
            0 -> "ace"
            in 1..9 -> (c % 13 + 1).toString()
            10 -> "jack"
            11 -> "queen"
            12 -> "king"
            else -> "error"
        }
        return if (c % 13 in 10..12)
            "c_${number}_of_${shape}2"
        else
            "c_${number}_of_${shape}"
    }
}