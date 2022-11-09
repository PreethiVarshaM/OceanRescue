package com.team12alpha.oceanrescue;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Waste {
    Bitmap waste[]=new Bitmap[7];
    int wasteFrame =0;
    int wasteX, wasteY, spikeVelocity;
    Random random;

    public Waste(Context context){
        waste[0]= BitmapFactory.decodeResource(context.getResources(),R.drawable.bottle);
        waste[1]= BitmapFactory.decodeResource(context.getResources(),R.drawable.chappal);
        waste[2]= BitmapFactory.decodeResource(context.getResources(),R.drawable.fireextinguisher);
        waste[3]= BitmapFactory.decodeResource(context.getResources(),R.drawable.tire);
        waste[4]= BitmapFactory.decodeResource(context.getResources(),R.drawable.plasticcover);
        waste[5]= BitmapFactory.decodeResource(context.getResources(),R.drawable.fishnet);
        waste[6]= BitmapFactory.decodeResource(context.getResources(),R.drawable.phone);
        random=new Random();
        resetPosition();
    }

    public Bitmap getWaste(int spikeFrame){
        return waste[wasteFrame];
    }

    public int getWasteWidth(){
        return waste[0].getWidth();
    }

    public int getWasteHeight(){
        return waste[0].getHeight();
    }
    public void resetPosition(){
        wasteX=random.nextInt(GameView.dWidth-getWasteWidth());
        wasteY=-200+random.nextInt(600)*-1;
        wasteVelocity=35+random.nextInt(16);

    }
}
