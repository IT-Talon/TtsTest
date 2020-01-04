package com.deyi.ttstest

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.QUEUE_FLUSH
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    lateinit var engine: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        engine = TextToSpeech(this, this)
        val btn = findViewById<TextView>(R.id.btn)
        val edit = findViewById<EditText>(R.id.edit_text)
        btn.setOnClickListener {
            val text = edit.text.toString()
            Log.e("Talon", text)
            engine.speak(text, QUEUE_FLUSH, null, null)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result: Int = engine.setLanguage(Locale.CHINA)
            if (result == TextToSpeech.LANG_MISSING_DATA
                || result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                Log.e("Talon", "不支持朗读功能")
                Toast.makeText(this, "不支持朗读功能", Toast.LENGTH_SHORT).show()
            } else {
                Log.e("Talon", "支持朗读功能")
            }
        } else {
            Log.e("Talon", "tts初始化失败")
        }
    }

    override fun onDestroy() {
        engine.stop()
        engine.shutdown()
        super.onDestroy()
    }
}
