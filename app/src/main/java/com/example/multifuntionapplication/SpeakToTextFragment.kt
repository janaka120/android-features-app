package com.example.multifuntionapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import java.util.Locale


@Suppress("DEPRECATION")
class SpeakToTextFragment : Fragment() {
    lateinit var resultText: TextView
    lateinit var imageButton: ImageButton
    lateinit var activityResultsLauncher: ActivityResultLauncher<Intent>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_speak_to_text, container, false)
        // Inflate the layout for this fragment
        resultText = view.findViewById(R.id.textViewResult)
        imageButton = view.findViewById(R.id.imageButtonMic)

        activityResultsLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { result ->
                val resultCode = result.resultCode
                val data = result.data

                if (resultCode == Activity.RESULT_OK && data != null) {
                    val speakResult: ArrayList<String> = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
                    resultText.text = speakResult[0]
                }
            })

        imageButton.setOnClickListener {
            convertToText()
        }
        return view
    }

    private fun convertToText() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        startActivityForResult(intent, 5)
        activityResultsLauncher.launch(intent)
    }

}