package libraries.renrui.com.myapplication;

import android.app.Activity;
import android.os.Bundle;

import com.renrui.libraries.util.CustomToast;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CustomToast.makeTextError("TestActivity ... start");
////        ToastCompat.makeText(this, "hello world!", Toast.LENGTH_SHORT).show();
//
//        try {
//
//            Thread.sleep(5000);
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//
//        CustomToast.makeTextSucess("TestActivity... end");
    }
}
