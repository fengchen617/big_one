package com.bawei6.bigone.custom;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bawei6.bigone.R;

import java.io.ByteArrayOutputStream;

/**
 * @author fengchen
 * @date 2019/12/27.
 * @description：底部的按钮
 */
public class Custom_Bottom extends LinearLayout {
    private ImageView imageView_one,imageView_two,imageView_three,
                      imageView_four,imageView_five;
    public Custom_Bottom(Context context) {
        super(context);
    }

    public Custom_Bottom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initview(context,attrs);
    }


    public Custom_Bottom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initview(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_bottom_item, null);
        imageView_one= view.findViewById(R.id.c_b_img_one);
        imageView_two= view.findViewById(R.id.c_b_img_two);
        imageView_three= view.findViewById(R.id.c_b_img_three);
        imageView_four= view.findViewById(R.id.c_b_img_four);
        imageView_five= view.findViewById(R.id.c_b_img_five);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Custom_Bottom);

        int img_one = typedArray.getResourceId(R.styleable.Custom_Bottom_app_img_one, 0);
        int img_two = typedArray.getResourceId(R.styleable.Custom_Bottom_app_img_two,0);
        int img_three = typedArray.getResourceId(R.styleable.Custom_Bottom_app_img_three,0);
        int img_four = typedArray.getResourceId(R.styleable.Custom_Bottom_app_img_four,0);
        int img_five = typedArray.getResourceId(R.styleable.Custom_Bottom_app_img_five,0);

        if (img_one!=0){
            imageView_one.setImageResource(img_one);
            getimg_one();
        }

        if (img_two!=0){
            imageView_two.setImageResource(img_two);
            getimg_two();
        }

        if (img_three!=0){
            imageView_three.setImageResource(img_three);
            getimg_three();
        }

        if (img_four!=0){
            imageView_four.setImageResource(img_four);
            getimg_four();
        }

        if (img_five!=0){
            getimg_five();
            imageView_five.setImageResource(img_five);
        }
        this.addView(view);
        typedArray.recycle();
    }

    public ImageView getimg_one(){
        return  imageView_one;
    }
    public ImageView getimg_two(){
        return  imageView_two;
    }
    public ImageView getimg_three(){
        return  imageView_three;
    }
    public ImageView getimg_four(){
        return  imageView_four;
    }
    public ImageView getimg_five(){
        return  imageView_five;
    }

}
