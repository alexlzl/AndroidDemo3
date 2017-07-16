package com.ispring.canvasdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements Button.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        MyView.DrawMode drawMode;
        switch (v.getId()){
            case R.id.btnDrawAxis:
                drawMode = MyView.DrawMode.AXIS;
                break;
            case R.id.btnDrawARGB:
                drawMode = MyView.DrawMode.ARGB;
                break;
            case R.id.btnDrawText:
                drawMode = MyView.DrawMode.TEXT;
                break;
            case R.id.btnDrawPoint:
                drawMode = MyView.DrawMode.POINT;
                break;
            case R.id.btnDrawLine:
                drawMode = MyView.DrawMode.LINE;
                break;
            case R.id.btnDrawRect:
                drawMode = MyView.DrawMode.RECT;
                break;
            case R.id.btnDrawCircle:
                drawMode = MyView.DrawMode.CIRCLE;
                break;
            case R.id.btnDrawOval:
                drawMode = MyView.DrawMode.OVAL;
                break;
            case R.id.btnDrawArc:
                drawMode = MyView.DrawMode.ARC;
                break;
            case R.id.btnDrawPath:
                drawMode = MyView.DrawMode.PATH;
                break;
            case R.id.btnDrawBitmap:
                drawMode = MyView.DrawMode.BITMAP;
                break;
            default:
                drawMode = MyView.DrawMode.UNKNOWN;
                break;
        }
        Intent intent = new Intent(this, CanvasActivity.class);
        intent.putExtra("drawMode", drawMode.value());
        startActivity(intent);
    }
}