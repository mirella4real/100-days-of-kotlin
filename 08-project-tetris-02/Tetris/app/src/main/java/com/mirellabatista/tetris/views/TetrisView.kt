package com.mirellabatista.tetris.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.annotation.Dimension
import com.mirellabatista.tetris.constants.CellConstants
import com.mirellabatista.tetris.GameActivity
import com.mirellabatista.tetris.constants.FieldConstants
import com.mirellabatista.tetris.models.AppModel
import com.mirellabatista.tetris.models.Block

class TetrisView : View {

    private val paint = Paint()
    private var lastMove: Long = 0
    private var model: AppModel? = null
    private var activity: GameActivity? = null
    private val viewHandler = ViewHandler(this)
    private var cellSize: Dimension  = Dimension(0,0)
    private var frameOffset: Dimension = Dimension(0,0)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
    companion object {
        private val DELAY = 500
        private val BLOCK_OFFSET = 2
        private val FRAME_OFFSET_BASE = 10
    }

    fun setModel(model: AppModel){
        this.model = model
    }

    fun setActivity(gameActivity: GameActivity){
        this.activity = gameActivity
    }

    fun setGameCommand(move: AppModel.Motions){
        if(null != model && (model?.currentState == AppModel.Statuses.ACTIVE.name)) {
            if(AppModel.Motions.DOWN == move){
                model?.generateField(move.name)
                invalidate()
                return
            }
            setGameCommandWithDelay(move)
        }
    }

    fun setGameCommandWithDelay(move: AppModel.Motions){
        val now = System.currentTimeMillis()
        if (now - lastMove > DELAY){
            model?.generateField(move.name)
            invalidate()
            lastMove = now
        }
        updateScores()
        viewHandler.sleep(DELAY.toLong())

    }

    private fun updateScores() {
        activity?.tvCurrentScore?.text = "${model?.score}"
        activity?.tvHighScore?.text = "${activity?.appPreferences?.getHighScore()}"
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        drawFrame(canvas)

        if(model != null) {
            for( i in 0 until FieldConstants.ROW_COUNT.value) {
                for( j in 0 until FieldConstants.COLUMN_COUNT.value){
                    drawCell(canvas, i, j)
                }
            }
        }
    }

    private fun drawFrame(canvas: Canvas){
        paint.color = Color.LTGRAY

        canvas.drawRect(frameOffset.width.toFloat(), frameOffset.height.toFloat(), width - frameOffset.width.toFloat(),  height - frameOffset.height.toFloat(), paint)
    }

    private fun drawCell(canvas: Canvas, row: Int, col: Int){
        val cellStatus = model?.getCellStatus(row, col)
        if(CellConstants.EMPTY.value != cellStatus) {
            val color = if(CellConstants.EPHEMERAL.value == cellStatus){
                model?.currentBlock?.color
            } else {
                Block.getColor(cellStatus as Byte)
            }
            drawCell(canvas, col, row, color as Int)
        }
    }

    private fun drawCell(canvas: Canvas, x: Int, y: Int, rgbColor: Int){
        paint.color = rgbColor

        val top: Float = (frameOffset.height + y * cellSize.height + BLOCK_OFFSET).toFloat()
        val left: Float = (frameOffset.width + x * cellSize.width + BLOCK_OFFSET).toFloat()
        val bottom: Float = (frameOffset.height + (y + 1) * cellSize.height - BLOCK_OFFSET).toFloat()
        val right: Float = (frameOffset.width + (x + 1) * cellSize.width - BLOCK_OFFSET).toFloat()
        val rectangle = RectF(left, top, right, bottom)

        canvas.drawRoundRect(rectangle, 4f, 4f, paint)
    }

    override fun onSizeChanged(width: Int, height: Int, previousWidth: Int, previousHeight: Int){
        super.onSizeChanged(width, height, previousWidth, previousHeight)

        val cellWidth = (width - 2 * FRAME_OFFSET_BASE) / FieldConstants.COLUMN_COUNT.value
        val cellHeight = (height - 2 * FRAME_OFFSET_BASE) / FieldConstants.ROW_COUNT.value
        val n = Math.min(cellWidth, cellHeight)
        this.cellSize = Dimension(n, n)
        val offsetX = (width - FieldConstants.COLUMN_COUNT.value * n) / 2
        val offsetY = (height - FieldConstants.ROW_COUNT.value * n) / 2
        this.frameOffset = Dimension(offsetX, offsetY)

    }

    private class ViewHandler(private val owner: TetrisView) : Handler() {
        override fun handleMessage(message: Message) {
            if(message.what == 0){
                if(owner.model != null){
                    if(owner.model!!.isGameOver()){
                        owner.model?.endGame()
                        Toast.makeText(owner.activity, "Game Over", Toast.LENGTH_LONG).show();
                    }
                    if(owner.model!!.isGameActive()){
                        owner.setGameCommandWithDelay(AppModel.Motions.DOWN)
                    }
                }
            }
        }

        fun sleep(delay: Long){
            this.removeMessages(0)
            sendMessageDelayed(obtainMessage(0), delay)
        }
    }

    private data class Dimension(val width: Int, val height: Int)

}

