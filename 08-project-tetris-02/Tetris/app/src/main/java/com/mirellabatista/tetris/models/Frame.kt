package com.mirellabatista.tetris.models

import com.mirellabatista.tetris.helpers.array2dOfByte

// properties
// width is integer prop, the number of cols in the frame byteArray (inner)
// data holds an array list of elements int eh Byte rray

class Frame(private val width: Int) {
    val data: ArrayList<ByteArray> = ArrayList()

    fun addRow(byteStr: String): Frame {
        val row = ByteArray(byteStr.length)

        for (index in byteStr.indices){
            row[index] = "${byteStr[index]}".toByte()
        }
        data.add(row)
        return this
    }

    fun as2dByteArray(): Array<ByteArray> {
        val bytes = array2dOfByte(data.size, width)
        return data.toArray(bytes)
    }
}