package libraries.renrui.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.renrui.libraries.interfaces.ITextviewClickable;
import com.renrui.libraries.util.LibUtility;
import com.renrui.libraries.util.UtilityControl;
import com.renrui.libraries.util.UtilitySecurity;


public class MainActivity extends Activity {

    TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tvTest = findViewById(R.id.tvTest);

        String text = "asdfasdfasdfsajdfl啊啊啊啊啊askdfjl88";
        String hotword = "啊啊啊啊啊";
        UtilityControl.setHotWordsText(tvTest, text, hotword, R.color.red_4d52, new ITextviewClickable() {
            @Override
            public void onSpanClick() {
                int sd = 90;
                sd++;
            }
        });
    }
}
