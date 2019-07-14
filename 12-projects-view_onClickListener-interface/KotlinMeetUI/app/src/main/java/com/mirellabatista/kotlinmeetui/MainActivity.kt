package com.mirellabatista.kotlinmeetui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var value:Int = 0
    override fun onClick(v: View) {

        val size: Float
        when (v.id){
            R.id.btnAdd -> {
                value++
                txtValue.text = "$value"
            }
            R.id.btnTake -> {
                value--
                txtValue.text = "$value"
            }
            R.id.btnReset -> {
                value = 0
                txtValue.text = "$value"
            }
            R.id.btnGrow -> {
                size = txtValue.textScaleX
                txtValue.textScaleX = size + 1
            }
            R.id.btnShrink -> {
                size = txtValue.textScaleX
                txtValue.textScaleX = size -1
            }
            R.id.btnHide -> {
                if (txtValue.visibility == View.VISIBLE) {
                    txtValue.visibility = View.INVISIBLE
                    btnHide.text = "SHOW"
                } else {
                    txtValue.visibility = View.VISIBLE
                    btnHide.text = "HIDE"
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener(this)
        btnTake.setOnClickListener(this)
        btnGrow.setOnClickListener(this)
        txtValue.setOnClickListener(this)
        btnShrink.setOnClickListener(this)
        btnReset.setOnClickListener(this)
        btnHide.setOnClickListener(this)
    }
}
