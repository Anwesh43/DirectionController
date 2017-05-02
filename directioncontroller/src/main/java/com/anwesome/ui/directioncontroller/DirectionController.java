package com.anwesome.ui.directioncontroller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 02/05/17.
 */
public class DirectionController {
    private DirectionControllerView directionControllerView;
    private Activity activity;
    private OnDirectionChangeListener onDirectionChangeListener;
    private int w,h;
    public void setOnDirectionChangeListener(OnDirectionChangeListener onDirectionChangeListener) {
        if(directionControllerView!=null) {
            directionControllerView.setOnDirectionChangeListener(onDirectionChangeListener);
        }
        else {
            this.onDirectionChangeListener = onDirectionChangeListener;
        }
    }
    public DirectionController(Activity activity) {
        this.activity = activity;
        initDimension();
    }
    public void initDimension() {
        DisplayManager displayManager = (DisplayManager)activity.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display != null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
    }
    public void show() {
        if(directionControllerView == null) {
            directionControllerView = new DirectionControllerView(activity);
            int size = Math.min(w,h)/2;
            activity.addContentView(directionControllerView,new ViewGroup.LayoutParams(size,size));
            directionControllerView.initXY(w-size,17*h/20-size);
            if(onDirectionChangeListener != null) {
                directionControllerView.setOnDirectionChangeListener(onDirectionChangeListener);
            }
        }
    }
}
