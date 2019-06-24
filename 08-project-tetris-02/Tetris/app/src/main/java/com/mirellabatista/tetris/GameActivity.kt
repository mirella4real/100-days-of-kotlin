package com.mirellabatista.tetris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.mirellabatista.tetris.models.AppModel
import com.mirellabatista.tetris.storage.AppPreferences
import com.mirellabatista.tetris.views.TetrisView
import org.w3c.dom.Text

class GameActivity : AppCompatActivity() {

    var tvHighScore: TextView? = null
    var tvCurrentScore: TextView? = null
    private lateinit var tetrisView: TetrisView
    var appPreferences: AppPreferences? = null
    private val appModel: AppModel = AppModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        appPreferences = AppPreferences(this)
        appModel.setPreferences(appPreferences)

        val btnRestart = findViewById<Button>(R.id.btn_restart)
        tvHighScore = findViewById<TextView>(R.id.tv_high_score)
        tvCurrentScore = findViewById<TextView>(R.id.tv_current_score)
        tetrisView = findViewById<TetrisView>(R.id.view_tetris)
        tetrisView.setActivity(this)
        tetrisView.setModel(appModel)
        tetrisView.setOnTouchListener(this::onTetrisViewTouch)
        btnRestart.setOnClickListener(this::btnRestarClick)

        updateHighScore()
        updateCurrentScore()
    }

    private fun btnRestarClick(view: View){
        appModel.restartGame()
    }

    private fun onTetrisViewTouch(view: View, event: MotionEvent) : Boolean {
        if(appModel.isGameOver() || appModel.isGameAwaitingStart()){
            appModel.startGame()
            tetrisView.setGameCommandWithDelay(AppModel.Motions.DOWN)
        } else if (appModel.isGameActive()){
            when (resolveTouchDirection(view, event)) {
                0 -> moveTetromino(AppModel.Motions.LEFT)
                1 -> moveTetromino(AppModel.Motions.ROTATE)
                2 -> moveTetromino(AppModel.Motions.DOWN)
                3 -> moveTetromino(AppModel.Motions.RIGHT)
            }
        }
        return true
    }

    private fun resolveTouchDirection(view: View, event: MotionEvent): Int{
        val x = event.x / view.width
        val y = event.y / view.height
        val direction: Int

        direction = if (y > x){
            if (x > 1 - y) 2 else 0
        }
        else {
            if(x > 1 - y ) 3 else 1
        }

        return direction
    }

    private fun moveTetromino(motion: AppModel.Motions){
        if(appModel.isGameActive()){
            tetrisView.setGameCommand(motion)
        }
    }

    private fun updateHighScore(){
        tvHighScore?.text = "${appPreferences?.getHighScore()}"
    }

    private fun updateCurrentScore(){
        tvCurrentScore?.text = "0"
    }

}
