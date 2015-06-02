package com.light.activitytransition;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by light on 15/6/2.
 */
public class ImageUtil {

    /**
     *
     * @Description TODO(Compress image by pixel )
     * @param @param imgPath
     * @param @param pixelW
     * @param @param pixelH
     * @param @return
     * @return Bitmap
     */
    public static Bitmap compressPixel(String imgPath, float pixelW, float pixelH) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath,newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = pixelH;
        float ww = pixelW;
        int inSampleSize = 1;
        if (h > hh || w > ww) {
            final int halfHeight = h / 2;
            final int halfWidth = w / 2;
            while ((halfHeight / inSampleSize) > hh
                    && (halfWidth / inSampleSize) > ww) {
                inSampleSize *= 2;
            }
        }
        newOpts.inSampleSize = inSampleSize;
        bitmap = BitmapFactory.decodeFile(imgPath, newOpts);

        Bitmap dst = Bitmap.createScaledBitmap(bitmap, (int)ww, (int)hh, false);
        if (bitmap != dst) {
            bitmap.recycle();
        }

        return dst;
    }

    public static Bitmap compressPixel(Context context,int drawable, float pixelW, float pixelH) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),drawable,newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = pixelH;
        float ww = pixelW;
        int inSampleSize = 1;
        if (h > hh || w > ww) {
            final int halfHeight = h / 2;
            final int halfWidth = w / 2;
            while ((halfHeight / inSampleSize) > hh
                    && (halfWidth / inSampleSize) > ww) {
                inSampleSize *= 2;
            }
        }
        newOpts.inSampleSize = inSampleSize;
        bitmap = BitmapFactory.decodeResource(context.getResources(),drawable,newOpts);

        Bitmap dst = Bitmap.createScaledBitmap(bitmap, (int)ww, (int)hh, false);
        if (bitmap != dst) {
            bitmap.recycle();
        }

        return dst;
    }

}
