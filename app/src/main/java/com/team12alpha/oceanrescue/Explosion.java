package com.team12alpha.oceanrescue;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Explosion {
    Bitmap explosion[]=new Bitmap[5];
   int explosionFrame =0;
   int explosionX,explosionY;

   public Explosion(Context context){
       explosion[0]= BitmapFactory.decodeResource(context.getResources(),R.drawable.bottle);
       explosion[1]= BitmapFactory.decodeResource(context.getResources(),R.drawable.bottle);
       explosion[2]= BitmapFactory.decodeResource(context.getResources(),R.drawable.bottle);
       explosion[3]= BitmapFactory.decodeResource(context.getResources(),R.drawable.bottle);
   }

    public Bitmap getExplosion(int explosionFrame) {
        return explosion[explosionFrame];
    }

}
