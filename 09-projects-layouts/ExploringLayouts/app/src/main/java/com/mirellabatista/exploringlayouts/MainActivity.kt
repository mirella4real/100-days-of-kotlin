package com.mirellabatista.exploringlayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        val btnView1 = findViewById<Button>(R.id.btn_load_cl)
            btnView1.setOnClickListener(this::loadConstraintLayout)
        val btnView2 = findViewById<Button>(R.id.btn_load_tl)
            btnView2.setOnClickListener(this::loadTableLayout)
    }

    fun loadConstraintLayout(v: View){
        setContentView(R.layout.activity_main)
    }

    fun loadTableLayout(v: View){
        setContentView(R.layout.my_table_layout)
    }

    fun loadMenuLayout(v: View){
        setContentView(R.layout.main_menu)
        val btnView1 = findViewById<Button>(R.id.btn_load_cl)
        btnView1.setOnClickListener(this::loadConstraintLayout)
        val btnView2 = findViewById<Button>(R.id.btn_load_tl)
        btnView2.setOnClickListener(this::loadTableLayout)
    }
}
