package com.anwesome.ui.directioncontroller;

import android.graphics.*;

/**
 * Created by anweshmishra on 02/05/17.
 */
public class DirectionButton {
    private float x,y,deg = 0,size;
    public DirectionButton(float deg) {
        this.deg = deg;
    }
    public void setDimension(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        paint.setColor(Color.parseColor("#BDBDBD"));
        Path path = new Path();
        path.moveTo(0,0);
        path.lineTo(-size/2,0);
        path.lineTo(0,-size);
        path.lineTo(size/2,0);
        canvas.drawPath(path,paint);
        canvas.restore();
    }
}
