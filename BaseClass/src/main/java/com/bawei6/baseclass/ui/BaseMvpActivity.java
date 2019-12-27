package com.bawei6.baseclass.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei6.baseclass.basemvp.BasePresenter;
import com.bawei6.baseclass.basemvp.IModel;
import com.bawei6.baseclass.basemvp.IView;

/**
 * @author fengchen
 * @date 2019/12/27.
 * @description：
 */
public abstract class BaseMvpActivity<P extends BasePresenter<IView, IModel>>  extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    abstract Integer getLayoutId();
}
