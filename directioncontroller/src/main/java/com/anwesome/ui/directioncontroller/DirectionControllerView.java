package com.anwesome.ui.directioncontroller;

import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 02/05/17.
 */
public class DirectionControllerView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int render = 0;
    private OnDirectionChangeListener onDirectionChangeListener;
    private AnimationHandler animationHandler = new AnimationHandler();
    private List<DirectionButton> directionButtons = new ArrayList<>();
    public void setOnDirectionChangeListener(OnDirectionChangeListener onDirectionChangeListener) {
        this.onDirectionChangeListener = onDirectionChangeListener;
    }
    public DirectionControllerView(Context context) {
        super(context);
        initDirectionButtons();
    }
    public void initXY(float x,float y){
        setX(x);
        setY(y);
    }
    public void initDirectionButtons() {
        for(int i=0;i<4;i++) {
            directionButtons.add(new DirectionButton(90*i));
        }
    }
    public void onDraw(Canvas canvas) {
        int w = canvas.getWidth(),h = canvas.getHeight()/2;
        if(render == 0) {
            float x = w/2,y = w/2,size = w/4;
            for(DirectionButton directionButton:directionButtons ){
                float deg = directionButton.getDeg(),fx = (float)(Math.cos((deg-90)*Math.PI/180)),fy = (float)(Math.sin((deg-90)*Math.PI/180));
                Log.d("x,y",String.format("%f,%f",fx,fy));
                directionButton.setDimension(x+fx*3*size/2,y+fy*3*size/2,size);
            }
        }
        for(DirectionButton directionButton:directionButtons) {
            directionButton.draw(canvas,paint);
        }
        render++;
        animationHandler.animate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(),y = event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            animationHandler.handleTap(x,y);
        }
        return true;
    }
    private class AnimationHandler {
        private DirectionButton currButton;
        private boolean isAnimated = false;
        public void animate() {
            if(isAnimated && currButton != null) {
                currButton.update();
                if(currButton.stopped()) {
                    isAnimated = false;
                    float deg = currButton.getDeg()-90;
                    float dx = (float)(Math.cos(deg*Math.PI/180)),dy = (float)(Math.sin(deg*Math.PI/180));
                    if(onDirectionChangeListener != null) {
                        onDirectionChangeListener.onDirectionChange(dx,dy);
                    }
                    currButton = null;
                }
                try {
                    Thread.sleep(10);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public void  handleTap(float x,float y) {
            if(!isAnimated && currButton == null) {
                for(DirectionButton directionButton:directionButtons) {
                    if(directionButton.handleTap(x,y)) {
                        currButton = directionButton;
                        break;
                    }
                }
                if(currButton != null) {
                    isAnimated = true;
                    postInvalidate();
                }
            }
        }
    }
}
