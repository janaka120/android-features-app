package com.example.multifuntionapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class MailSendFragment : Fragment() {

    lateinit var subject: EditText
    lateinit var address: EditText
    lateinit var body: EditText
    lateinit var submit: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_mail_send, container, false)

        subject = view.findViewById(R.id.editTextSubject)
        address = view.findViewById(R.id.editTextAddress)
        body = view.findViewById(R.id.editTextMail)
        submit = view.findViewById(R.id.buttonSendView)

        submit.setOnClickListener {
            val subText = subject.text.toString()
            val addText = address.text.toString()
            val bodyText = body.text.toString()

            sendMail(subText, addText, bodyText)
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun sendMail(subject: String, address: String, content: String) {
        val emailAddresses = arrayOf(address)

        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddresses)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, content)
        val packageManager = requireActivity().packageManager

        if (emailIntent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(emailIntent, "Choose a app"))
        }
    }
}