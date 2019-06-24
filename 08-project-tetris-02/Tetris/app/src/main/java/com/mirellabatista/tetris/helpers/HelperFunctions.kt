package com.mirellabatista.tetris.helpers

// arguments:
// sizeOuter is desired row number
// sizeInner is desired column number
// generates an array of bytearrays
fun array2dOfByte(sizeOuter: Int, sizeInner: Int): Array<ByteArray> = Array(sizeOuter) {
    ByteArray(sizeInner)
}