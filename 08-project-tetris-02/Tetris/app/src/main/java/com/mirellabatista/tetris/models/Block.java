package com.mirellabatista.tetris.models;

import android.graphics.Color;
import android.graphics.Point;
import androidx.annotation.NonNull;
import com.mirellabatista.tetris.constants.FieldConstants;

import java.util.Random;

public class Block {

    private int shapeIndex;     // holds index of shape  of the block
    private int frameNumber;    // keep track of the number of frames the block's shape has
    private BlockColor color;   // hold color characteristics of the block
    private Point position;     // track the block's current spatial position in game field

    private Block(int shapeIndex, BlockColor blockColor){
        this.frameNumber = 0;
        this.shapeIndex = shapeIndex;
        this.color = blockColor;
        this.position = new Point(FieldConstants.COLUMN_COUNT.getValue() /2, 0);
    }

    public static Block createBlock() {
        Random random = new Random();
        int shapeIndex = random.nextInt(Shape.values().length);
        BlockColor blockColor = BlockColor.values()[random.nextInt(BlockColor.values().length)];

        Block block = new Block(shapeIndex, blockColor);
        block.position.x = block.position.x - Shape.values()[shapeIndex].getStartPosition();
        return block;
    }

    public static int getColor(byte value){
        for(BlockColor colour : BlockColor.values()){
            if(value == colour.byteValue){
                return colour.rgbValue;
            }
        }
        return -1;
    }

    public final void setState(int frame, Point position){
        this.frameNumber = frame;
        this.position = position;
    }

    @NonNull
    public final byte[][] getShape(int frameNumber){
        return Shape.values()[shapeIndex].getFrame(frameNumber).as2dByteArray();
    }

    public Point getPosition(){
        return this.position;
    }

    public final int getFrameCount() {
        return Shape.values()[shapeIndex].getFrameCount();
    }

    public int getFrameNumber(){
        return frameNumber;
    }

    public int getColor(){
        return color.rgbValue;
    }

    public byte getStaticValue(){
        return color.byteValue;
    }

    public enum BlockColor {
        PINK(Color.rgb(355, 105, 180), (byte) 2),
        GREEN(Color.rgb(0, 128, 0), (byte) 3),
        ORANGE(Color.rgb(255, 140, 0), (byte) 4),
        YELLOW(Color.rgb(255, 255, 0), (byte) 5),
        CYAN(Color.rgb(0, 255, 255), (byte) 6);

        BlockColor(int rgbValue, byte value){
            this.rgbValue = rgbValue;
            this.byteValue = value;
        }

        private final int rgbValue;
        private final byte byteValue;
    }

}
