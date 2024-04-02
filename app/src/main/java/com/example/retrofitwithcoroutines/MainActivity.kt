package com.example.retrofitwithcoroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val quotesAPI = RetrofitHelper.getInstance().create(QuotesAPI::class.java)

        GlobalScope.launch {
            val result = quotesAPI.getQuotes(1)
            if (result != null) {

                val quoteList = result.body()

                if (quoteList != null) {
                    quoteList.results.forEach {
                        Log.d("XOKSIS", it.content)
                    }
                }

            }
        }

    }
}