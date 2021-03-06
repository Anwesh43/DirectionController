package com.anwesome.ui.directioncontroller;

import android.graphics.*;

/**
 * Created by anweshmishra on 02/05/17.
 */
public class DirectionButton {
    private float x,y,deg = 0,size;
    private DirectionButtonOpaque directionButtonOpaque;
    public DirectionButton(float deg) {
        this.deg = deg;
    }
    public void setDimension(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
        directionButtonOpaque = new DirectionButtonOpaque();
    }
    public void update() {
        directionButtonOpaque.update();
    }
    public boolean stopped() {
        return directionButtonOpaque.stopped();
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        paint.setColor(Color.parseColor("#BDBDBD"));
        drawTriangle(canvas,paint);
        directionButtonOpaque.draw(canvas,paint);
        canvas.restore();
    }
    public boolean handleTap(float x,float y) {
        boolean condition =  x>=this.x -size && x<=this.x+size && y>=this.y-size && y<=this.y+size;
        if(directionButtonOpaque != null && condition) {
            directionButtonOpaque.startUpdating();
        }
        return condition;
    }
    public float getDeg() {
        return deg;
    }
    public int hashCode() {
        return (int)(deg+x+y);
    }
    private void drawTriangle(Canvas canvas,Paint paint) {
        Path path = new Path();
        path.moveTo(-size/2,size/2);
        path.lineTo(0,-size/2);
        path.lineTo(size/2,size/2);
        path.lineTo(-size/2,size/2);
        canvas.drawPath(path,paint);
    }
    private class DirectionButtonOpaque {
        private float dir = 0,scale = 0;
        public void draw(Canvas canvas,Paint paint) {
            paint.setColor(Color.parseColor("#7700ACC1"));
            canvas.save();
            canvas.scale(scale,scale);
            drawTriangle(canvas,paint);
            canvas.restore();
        }
        public boolean stopped() {
            return dir == 0;
        }
        public void update() {
            scale += dir * 0.2f;
            if(scale>1) {
                dir = -1;
                scale = 1;
            }
            if(scale<0) {
                dir = 0;
                scale = 0;
            }
        }
        public void startUpdating() {
            if(dir == 0 && scale == 0) {
                dir = 1;
            }
        }
    }
}
