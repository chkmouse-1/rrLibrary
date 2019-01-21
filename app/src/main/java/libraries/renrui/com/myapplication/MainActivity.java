package libraries.renrui.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class MainActivity extends Activity {

    private TextView tvTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tvTest1 = findViewById(R.id.tvTest1);
//
//        String text = "asdfas123456dfasdfas4567dfasdf";
//        String[] arrHotWord = new String[]{"12345", "4567"};
//        UtilityControl.setHotWordsText(tvTest1, text, arrHotWord, R.color.red_3f42, new ITextviewClickable() {
//            @Override
//            public void onSpanClick(int position) {
//                int sd = 90;
//                sd++;
//
//                if (position == 0)
//                    CustomToast.makeTextWarn("aaaa");
//                else
//                    CustomToast.makeTextWarn("bbbb");
//
////                LibUtility.getAppOnlySign(MainActivity.this);
//            }
//        });
    }
}