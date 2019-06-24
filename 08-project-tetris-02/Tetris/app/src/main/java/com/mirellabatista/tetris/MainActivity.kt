package com.mirellabatista.tetris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.mirellabatista.tetris.storage.AppPreferences

class MainActivity : AppCompatActivity() {

    var tvHighScore: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val btnNewGame = findViewById<Button>(R.id.btn_new_game)
        val btnResetScore = findViewById<Button>(R.id.btn_reset_score)
        val btnExit = findViewById<Button>(R.id.btn_exit)
        tvHighScore = findViewById<TextView>(R.id.tv_high_score)

        btnNewGame.setOnClickListener(this::onBtnNewGameClick)
        btnResetScore.setOnClickListener(this::onBtnResetScoreClick)
        btnExit.setOnClickListener(this::onBtnExitClick)
    }

    private fun onBtnNewGameClick(view: View){
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun onBtnResetScoreClick(view: View){
        //call to SharedPreferences
        val preferences = AppPreferences(this)
        preferences.clearHighScore()

        Snackbar.make(view, "Score successfully reset", Snackbar.LENGTH_SHORT).show()
        tvHighScore?.text = "High Score: ${preferences.getHighScore()}"
    }

    private fun onBtnExitClick(view: View){
        System.exit(0)
    }
}
