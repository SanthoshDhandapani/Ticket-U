package com.ticketu.ui.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.TypedValue;

import com.github.johnkil.print.PrintDrawable;

/**
 * Created by santhosh on 1/12/15.
 */
public class PrintFontIconDrawable {


    private static PrintFontIconDrawable instance = null;
    private Context mContext;

    public PrintFontIconDrawable(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static PrintFontIconDrawable getInstance(Context context) {
        if(instance == null) instance = new PrintFontIconDrawable(context);
        return instance;
    }

    public PrintDrawable getDrawableFontIcon(int textResId, int fontColorResId,int iconSizeResId) {

        return new PrintDrawable.Builder(mContext)
                .iconText(textResId)
                .iconColor(fontColorResId)
                .iconSize(iconSizeResId)
                .build();
    }

    public PrintDrawable getDrawableFontIcon(String iconText, int fontColorResId, int iconSizeResId) {

        return new PrintDrawable.Builder(mContext)
                .iconText(iconText)
                .iconColor(fontColorResId)
                .iconSize(iconSizeResId)
                .build();
    }

    public PrintDrawable getDrawableFontIcon(String iconText, int fontColorResId, String fontAssetName, int iconSizeResId) {

        return new PrintDrawable.Builder(mContext)
                .iconText(iconText)
                .iconColor(fontColorResId)
                .iconFont(fontAssetName)
                .iconSize(iconSizeResId)
                .build();
    }

    public PrintDrawable getDrawableFontIcon(String iconText, ColorStateList fontColorResId, String fontAssetName, int iconSizeResId) {

        return new PrintDrawable.Builder(mContext)
                .iconText(iconText)
                .iconColor(fontColorResId)
                .iconFont(fontAssetName)
                .iconSize(TypedValue.COMPLEX_UNIT_PX, iconSizeResId)
                .build();
    }

    public PrintDrawable getDrawableFontIcon(int textResId, int fontColorResId,int iconSizeResId, String fontFace) {

        return new PrintDrawable.Builder(mContext)
                .iconText(textResId)
                .iconColor(fontColorResId)
                .iconSize(iconSizeResId)
                .iconFont(fontFace)
                .build();
    }
}
