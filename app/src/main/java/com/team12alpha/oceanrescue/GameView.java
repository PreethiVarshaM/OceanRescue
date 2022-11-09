package com.team12alpha.oceanrescue;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import android.view.Display;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {

    Bitmap background, fish;
    Rect rectBackground;
    Context context;
    Handler handler;
    final long UPDATE_MILLIS =30;
    Runnable runnable;
    Paint textPaint = new Paint();
    Paint healthPaint = new Paint();
    float TEXT_SIZE = 120;
    int points = 0;
    int life =3;
    static int dWidth,dHeight;
    Random random;
    float fishX, fishY;
    float oldX;
    float oldfishX;
    ArrayList<Waste> wastes;

    public GameView(Context context) {
        super(context);
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(),R.drawable.background4);
        fish = BitmapFactory.decodeResource(getResources(),R.drawable.fish);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        rectBackground = new Rect(0,0,dWidth,dHeight);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run(){
                invalidate();
            }
        };
        textPaint.setColor(Color.rgb(255,165,0));
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTypeface(ResourcesCompat.getFont(context,R.font.pumpkinstory));
        healthPaint.setColor(Color.GREEN);
        random = new Random();
        fishX = dWidth / 2 - fish.getWidth() / 2;
        fishY = dHeight ;
        wastes = new ArrayList<>();

        for(int i=0;i<7;i++){
            Waste waste = new Waste(context);
            wastes.add(waste);
        }
    }

    @Override

}

