package com.bawei6.baseclass.basemvp;

/**
 *
 * @author fengchen
 * @date 2019.12.27
 * @param <V> Base---IView接口
 * @param <M>Base---IModel接口
 */
public abstract class BasePresenter<V extends IView,M extends IModel> {
    protected V mView;
    protected M mModel;

    //绑定View
    abstract public void AttachView(V view);
    //解除绑定View
    abstract public void DettachView();
}
