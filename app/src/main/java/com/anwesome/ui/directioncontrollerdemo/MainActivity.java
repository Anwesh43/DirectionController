package com.anwesome.ui.directioncontrollerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.directioncontroller.DirectionController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DirectionController directionController = new DirectionController(this);
        directionController.show();
    }
}
