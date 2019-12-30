package com.bawei6.baseclass.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bawei6.baseclass.R;

/**
 * @author fengchen
 * @date 2019/12/27.
 * @description：
 */
public class TitleBar extends LinearLayout {
    private ImageView imageView_left,imageView_center,imageView_right;
    private TextView textView;
    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initview(context,attrs);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //初始化控件
    private void initview(Context context, AttributeSet attrs) {
        //找布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.titlebar_item, null);
        //找控件ID
        imageView_left = view.findViewById(R.id.base_title_bar_img_left);
        imageView_center = view.findViewById(R.id.base_title_bar_img_center);
        imageView_right = view.findViewById(R.id.base_title_bar_img_right);
        textView = view.findViewById(R.id.base_title_bar_text);

        //获取资源
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        int img_left = typedArray.getResourceId(R.styleable.TitleBar_attr_base_title_bar_img_left, 0);
        int img_center = typedArray.getResourceId(R.styleable.TitleBar_attr_base_title_bar_img_center, 0);
        int img_right = typedArray.getResourceId(R.styleable.TitleBar_attr_base_title_bar_img_right, 0);
        int text = typedArray.getResourceId(R.styleable.TitleBar_attr_base_title_bar_text, 0);

        //给左边的图片设置
        if(img_left!=0){
            imageView_left.setImageResource(img_left);
        }
        //给左边的图片设置
        if(img_left!=0){
            imageView_left.setImageResource(img_left);
        }
        //给中间的图片设置
        if(img_center!=0){
            imageView_center.setImageResource(img_center);
        }
        //给右边的图片设置
        if(img_right!=0){
            imageView_right.setImageResource(img_right);
        }
        //给text设置
        if(text!=0){
            textView.setText(img_left);
        }
        //添加到布局上
        this.addView(view);
        //回收
        typedArray.recycle();
    }
}
