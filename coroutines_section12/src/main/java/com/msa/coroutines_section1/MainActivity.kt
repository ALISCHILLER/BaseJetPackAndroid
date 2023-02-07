package com.msa.coroutines_section1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.msa.coroutines_section1.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        CoroutineScope(Dispatchers.IO).launch {
            fakeApi("a")
           // fakeApi("b")
        }
        CoroutineScope(Dispatchers.IO).launch {
            //fakeApi("a")
            fakeApi("b")
        }


        CoroutineScope(Dispatchers.Main).launch {
            fakeAp2("a")
            fakeAp2("b")
        }


/*        CoroutineScope(Main).launch {


            val job2 = CoroutineScope(Main).launch() {
                repeat(10){
                    delay(1000)
                    logThread("fakeApi b$it")
                }
            }


            val job1 = CoroutineScope(Main).launch() {
                repeat(10){
                    delay(1000)
                    logThread("fakeApi a$it")
                    if (it == 5)
                        job2
                }
            }
        }*/



//        CoroutineScope(Main).launch {
//
//            val job1 = CoroutineScope(Main).launch() {
//                var a=10
//                while (a>0) {
//                    logThread("fakeApi a$a")
//                    a--
//                    if (a == 5)
//                        yield()
//                }
//            }
//
//            val job2 = CoroutineScope(Main).launch() {
//                var b=10
//                while (b>0) {
//                    logThread("fakeApi b$b")
//                    b--
//                }
//            }
//
//
//
//        }


//        CoroutineScope(Dispatchers.Main + Job()).launch {
//            val user = fetchUser() // A suspending function running in the I/O thread.
//              // TextcView.set=user // Updates UI in the main thread.
//         }
//
//        CoroutineScope(Dispatchers.IO).launch {
//        }
//
//        CoroutineScope(Dispatchers.Default).launch {
//        }
//        CoroutineScope(Dispatchers.Unconfined).launch {
//            // Writes code here running on Main thread.
//
//            delay(1_000)
//            // Writes code here running on `kotlinx.coroutines.DefaultExecutor`.
//
//            withContext(Dispatchers.IO) {  }
//            // Writes code running on I/O thread.
//
//            withContext(Dispatchers.Main) { }
//            // Writes code running on Main thread.
//        }






    }

    private fun setNewText(input: String){
        val newText = binding.textView.text.toString() + "\n$input"
        binding.textView.text = newText
    }
    private suspend fun setTextOnMainThread(input: String) {
        // Switch to Dispatchers.Main ui
        withContext (Dispatchers.Main) {
            setNewText(input)
        }
    }

    private  suspend fun fakeApi(name:String){


        logThread("fakeApi")
        delay(1000) // Does not block thread. Just suspends the coroutine inside the thread
        var a =7

        while(a>0){
           // logThread("Result ${name + a} ")
            setTextOnMainThread("Result ${name + a}")
            delay(1000)
            a--
        }



    }
    private  suspend fun fakeAp2(name:String){
        logThread("fakeApi")
       // delay(1000) // Does not block thread. Just suspends the coroutine inside the thread
        var a =7
        while(a>0){
            if (name == "a" && a==5)
                yield()
            logThread("fakeAp2 ${name + a} ")
          //  setTextOnMainThread("fakeAp2 ${name + a}")
            delay(1000)
            a--
        }
    }

    private fun logThread(methodName: String){
        Log.e("MainActivity","debug: ${methodName}")
    }
    private suspend fun fetchUser(): String = withContext(Dispatchers.IO) {
        // Fetches the data from server and returns user data.

        return@withContext "Result #1"
    }
}