package com.example.xingge.opensourcechina.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

import java.io.InputStream;

/**
 * Created by xingge on 2017/4/14.
 */

public class Utils {

    public static final void exit(){
        android.os.Process.killProcess(android.os.Process.myPid());//获取PID
        System.exit(0);
    }

    /**
     * 获取圆形图片
     * @param bitmap 方形的bitmap
     * @return
     */
    public static Bitmap getCircleImg(Bitmap bitmap){
        //第一创建一个空的Bitmap
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_4444);
        //第二步创建画布
        Canvas canvas = new Canvas(newBitmap);
        //第三步创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        //抗锯齿
        paint.setAntiAlias(true);
        //获取半径
        int radius = bitmap.getWidth() / 2 > bitmap.getHeight() / 2 ? bitmap.getHeight()/2 : bitmap.getWidth()/2;
        //绘制圆形
        canvas.drawCircle(bitmap.getWidth()/2,bitmap.getHeight()/2,radius,paint);
        //取交集
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        //绘制图片
        canvas.drawBitmap(bitmap,rect,rect,paint);
        return newBitmap;
    }

    public static Bitmap getCircleImg(InputStream inputStream){
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return getCircleImg(bitmap);
    }
}
