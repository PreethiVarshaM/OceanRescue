package com.team12alpha.oceanrescue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.MotionEvent;
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
    int points = 0;          // No of points scored
    int life =3;             // Total No of  lives
    static int dWidth,dHeight;
    Random random;
    float fishX, fishY;         // fish coordinates
    float oldX;
    float oldfishX;
    ArrayList<Waste> wastes;   // list of all thrown waste items
    ArrayList<Explosion> explosions;

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
        fishY = dHeight -fish.getHeight() ;
        wastes = new ArrayList<>();

        for(int i=0;i<7;i++){
            Waste waste = new Waste(context);
            wastes.add(waste);
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(background,null,rectBackground,null);
        canvas.drawBitmap(fish,fishX,fishY,null);
        for(int i=0;i<wastes.size();i++){
            canvas.drawBitmap(wastes.get(i).getWaste(wastes.get(i).wasteFrame),wastes.get(i).wasteX,wastes.get(i).wasteY,null);
            wastes.get(i).wasteFrame++;
            if(wastes.get(i).wasteFrame>2){
                wastes.get(i).wasteFrame=0;
            }
            wastes.get(i).wasteY+=wastes.get(i).wasteVelocity;
            if(wastes.get(i).wasteY+wastes.get(i).getWasteHeight()>=dHeight){
                points+=10;
                ///explosion object
                Explosion explosion=new Explosion(context);
                explosion.explosionX=wastes.get(i).wasteX;
                explosion.explosionY=wastes.get(i).wasteY;
                explosions.add(explosion);
                wastes.get(i).resetPosition();
            }
        }
        for(int i=0;i<wastes.size();i++){
            if(wastes.get(i).wasteX+wastes.get(i).getWasteWidth()>=fishX
            && wastes.get(i).wasteX<=fishX+fish.getWidth()
            && wastes.get(i).wasteY+wastes.get(i).getWasteWidth()>=fishY
            && wastes.get(i).wasteY+wastes.get(i).getWasteWidth()<=fishY+fish.getHeight()){
                life--;
                wastes.get(i).resetPosition();
                if(life==0){
                    Intent intent=new Intent(context,GameOver.class);
                    intent.putExtra("points",points);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
            }
        }
        for(int i=0;i<explosions.size();i++){
            canvas.drawBitmap(explosions.get(i).getExplosion(explosions.get(i).explosionFrame),explosions.get(i).explosionX,explosions.get(i).explosionY,null);
            explosions.get(i).explosionFrame++;
            if(explosions.get(i).explosionFrame>3){
                explosions.remove(i);
            }
        }
        if(life==2){
            healthPaint.setColor(Color.YELLOW);
        }else if(life==1){
            healthPaint.setColor(Color.RED);
        }
        canvas.drawRect(dWidth-200,30,dWidth-200+60*life,80,healthPaint);
        canvas.drawText(""+points,20,TEXT_SIZE,textPaint);
        handler.postDelayed(runnable,UPDATE_MILLIS);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float touchX=event.getX();
        float touchY=event.getY();
        if(touchY>=fishY){
            int action =event.getAction();
            if(action==MotionEvent.ACTION_DOWN){
                oldX=event.getX();
                oldfishX=fishX;
            }
            if(action==MotionEvent.ACTION_MOVE){
                float shift=oldX-touchX;
                float newFishX=oldfishX-shift;
            }
        }
        return true;
    }
}

