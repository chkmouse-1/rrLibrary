package libraries.renrui.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.renrui.libraries.interfaces.ITextviewClickable;
import com.renrui.libraries.model.SpanModel;
import com.renrui.libraries.util.CustomToast;
import com.renrui.libraries.util.UtilityControl;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private TextView tvTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest1 = findViewById(R.id.tvTest1);

        String text = "asdfas123456dfasdfas4567dfasdf";

        SpanModel model1 = new SpanModel();
        model1.text = "123456";
        model1.color = getResources().getColor(R.color.red_3f42);
        model1.isUnderline = true;

        SpanModel model2 = new SpanModel();
        model2.text = "4567";
        model2.color = getResources().getColor(R.color.red_3f42);
        model2.isUnderline = false;

        List<SpanModel> list = new ArrayList<>();
        list.add(model1);
        list.add(model2);

        UtilityControl.setSpanText(tvTest1, text, list, new ITextviewClickable() {
            @Override
            public void onSpanClick(int position) {
                CustomToast.makeTextWarn("position=" + position);
            }
        });
    }
}