package ar.com.damianfrutos.conversordedivisas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonConvert: Button = findViewById(R.id.buttonConvert)
        buttonConvert.setOnClickListener {
            val amount = findViewById<EditText>(R.id.editTextAmount).text.toString().toDouble()
            val currencyFrom = findViewById<EditText>(R.id.editTextCurrencyFrom).text.toString().toUpperCase()
            val currencyTo = findViewById<EditText>(R.id.editTextCurrencyTo).text.toString().toUpperCase()

            convertCurrency(amount, currencyFrom, currencyTo)
        }


    }
    private fun convertCurrency(amount: Double, currencyFrom: String, currencyTo: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.api.getConversion(currencyFrom, currencyTo, amount)
            if (response.isSuccessful) {
                response.body()?.let {
                    val result = it.conversion_result
                    withContext(Dispatchers.Main) {
                        findViewById<TextView>(R.id.textViewResult).text = result.toString()
                    }
                }
            }
        }
    }
}
