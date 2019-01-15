package libraries.renrui.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.renrui.libraries.interfaces.IHttpDownloadInterFace;
import com.renrui.libraries.util.CustomToast;
import com.renrui.libraries.util.LibUtility;
import com.renrui.libraries.util.UtilitySecurity;
import com.renrui.libraries.util.mHttpClient;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, TestActivity.class);

        float f = 1.2f;
        UtilitySecurity.putExtras(intent, "float", f);

        double d = 2.2d;
        UtilitySecurity.putExtras(intent, "double", d);

        int in = 1;
        UtilitySecurity.putExtras(intent, "int", in);

        String s = "string .";
        UtilitySecurity.putExtras(intent, "string", s);

        ArrayList<String> alString = new ArrayList<>();
        alString.add("aaa");

        ArrayList<Integer> alInteger = new ArrayList<>();
        alInteger.add(22);

        int sd = 90;
        sd++;
    }
}
