package com.example.xingge.opensourcechina.config;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by xingge on 2017/4/28.
 */

public class Turing {

    private FragmentManager managerV4;
    private android.app.FragmentManager managerApp;
    private static Turing turing;

    private Turing(Activity activity){
        managerApp = activity.getFragmentManager();
    }

    private Turing(AppCompatActivity activity){
        managerV4 = activity.getSupportFragmentManager();
    }

    private Turing(Fragment fragment){
        managerApp = fragment.getFragmentManager();
    }

    private Turing(android.support.v4.app.Fragment fragment){
        managerV4 = fragment.getFragmentManager();
    }

    public static Turing create(Activity activity){
        if (turing == null){
            synchronized (Turing.class){
                if(turing == null)
                    turing = new Turing(activity);
            }
        }
        return turing;
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

    public static Turing create(android.support.v4.app.Fragment fragment){
        if (turing == null){
            synchronized (Turing.class){
                if(turing == null)
                    turing = new Turing(fragment);
            }
        }
        return turing;
    }

    public Turing start(){

        return this;
    }

}
