package com.anwesome.ui.directioncontrollerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.anwesome.ui.directioncontroller.DirectionController;
import com.anwesome.ui.directioncontroller.OnDirectionChangeListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView helloText = (TextView)findViewById(R.id.text_hello);
        DirectionController directionController = new DirectionController(this);
        directionController.setOnDirectionChangeListener(new OnDirectionChangeListener() {
            @Override
            public void onDirectionChange(float dx, float dy) {
                helloText.setX(helloText.getX()+dx*10);
                helloText.setY(helloText.getY()+dy*10);
            }
        });
        directionController.show();
    }
}
