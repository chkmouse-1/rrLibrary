package libraries.renrui.com.myapplication;

import android.app.Application;

import com.renrui.libraries.util.LibrariesCons;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LibrariesCons.setContext(this);
    }
}
