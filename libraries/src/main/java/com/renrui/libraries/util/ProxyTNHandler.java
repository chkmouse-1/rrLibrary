package com.renrui.libraries.util;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.WindowManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyTNHandler extends Handler {

    private Object tnObject;
    private Method handleShowMethod;
    private Method handleHideMethod;

    public ProxyTNHandler(Object tnObject) {
        this.tnObject = tnObject;
        try {
            this.handleShowMethod = tnObject.getClass().getDeclaredMethod("handleShow", IBinder.class);
            this.handleShowMethod.setAccessible(true);
//            Timber.d("handleShow method is %s", handleShowMethod);
            this.handleHideMethod = tnObject.getClass().getDeclaredMethod("handleHide");
            this.handleHideMethod.setAccessible(true);
//            Timber.d("handleHide method is %s", handleHideMethod);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case 0: {
                //SHOW
                IBinder token = (IBinder) msg.obj;
//                Timber.d("handleMessage(): token is %s", token);
                if (handleShowMethod != null) {
                    try {
                        handleShowMethod.invoke(tnObject, token);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (WindowManager.BadTokenException e) {
                        //显示Toast时添加BadTokenException异常捕获
                        e.printStackTrace();
//                        Timber.e(e, "show toast error.");
                    }
                }
                break;
            }

            case 1: {
                //HIDE
//                Timber.d("handleMessage(): hide");
                if (handleHideMethod != null) {
                    try {
                        handleHideMethod.invoke(tnObject);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case 2: {
                //CANCEL
//                Timber.d("handleMessage(): cancel");
                if (handleHideMethod != null) {
                    try {
                        handleHideMethod.invoke(tnObject);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }

        }
        super.handleMessage(msg);
    }

}
