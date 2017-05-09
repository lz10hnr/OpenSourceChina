package com.example.turing;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


public class Turing {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private static Turing turing;
    private int containerViewId;
    private Fragment lastFragment;
    private Fragment fragment;
    private boolean isDefault = true;

    private Turing(AppCompatActivity activity){
        manager = activity.getSupportFragmentManager();
    }

    private Turing(Fragment fragment){
        manager = fragment.getFragmentManager();
    }

    public static Turing create(AppCompatActivity activity){
        if (turing == null){
            synchronized (Turing.class){
                if(turing == null)
                    turing = new Turing(activity);
            }
        }
        return turing;
    }

    public static Turing create(Fragment fragment){
        if (turing == null){
            synchronized (Turing.class){
                if(turing == null)
                    turing = new Turing(fragment);
            }
        }
        return turing;
    }

    public Turing container(int containerViewId){
        this.containerViewId = containerViewId;
        return this;
    }

    public Turing start(Class<? extends Fragment> fragmentClass) throws NotFoundContainerException {
        if(containerViewId == 0)
            throw new NotFoundContainerException("Please add containerViewId before invoking start(Class<? extends Fragment> fragmentClass)");
        transaction = manager.beginTransaction();
        String simpleName = fragmentClass.getSimpleName();
        fragment = manager.findFragmentByTag(simpleName);
        if(fragment == null){
            try {
                //动态代理 动态创建对象
                fragment = fragmentClass.newInstance();
                transaction.add(containerViewId,fragment,simpleName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return this;
    }

    public Turing hide(){
        if (lastFragment != null){
            transaction.hide(lastFragment);
        }
        transaction.show(fragment);
        return this;
    }

    public Turing replace(){
        isDefault = false;
        transaction.replace(containerViewId,fragment,fragment.getClass().getSimpleName());
        return this;
    }

    public Turing isBack(boolean isBack){
        if(isBack)
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        return this;
    }

    public Fragment build(){
        if (isDefault) {
            hide();
            isBack(true);
        }
        transaction.commit();
        lastFragment = fragment;
        isDefault = true;
        return fragment;
    }

    public Fragment getLastFragment() {
        return lastFragment;
    }
}
