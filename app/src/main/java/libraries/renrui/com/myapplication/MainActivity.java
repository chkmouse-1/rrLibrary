package libraries.renrui.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.renrui.libraries.interfaces.ITextviewClickable;
import com.renrui.libraries.model.SpanModel;
import com.renrui.libraries.util.CustomToast;
import com.renrui.libraries.util.UtilityControl;
import com.renrui.libraries.util.UtilitySecurity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {

    private TextView tvTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest1 = findViewById(R.id.tvTest1);

        String text = "点击登录表示您同意《用户协议》《企业注册协议》《隐私权协议》mafeifgdsgfdgceshi";

        SpanModel model1 = new SpanModel();
        model1.text = "《用户协议》";
        model1.color = getResources().getColor(R.color.blue_a1ff);
        model1.isUnderline = true;
        model1.textSize = 50;

        SpanModel model2 = new SpanModel();
        model2.text = "《企业注册协议》";
        model2.color = getResources().getColor(R.color.blue_a1ff);
        model2.isUnderline = false;

        SpanModel model3 = new SpanModel();
        model3.text = "《隐私权协议》";
        model3.color = getResources().getColor(R.color.blue_a1ff);
        model3.isUnderline = false;

        SpanModel model4 = new SpanModel();
        model4.text = "mafei";
        model4.color = getResources().getColor(R.color.blue_a1ff);
        model4.isUnderline = false;
        model4.isBold = true;

        SpanModel model5 = new SpanModel();
        model5.text = "ceshi";
        model5.color = getResources().getColor(R.color.blue_a1ff);
        model5.isUnderline = false;
        model5.isBold = true;

        List<SpanModel> list = new ArrayList<>();
        list.add(model1);
        list.add(model2);
        list.add(model3);
        list.add(model4);
        list.add(model5);

        UtilityControl.setSpanText(tvTest1, text, list, new ITextviewClickable() {
            @Override
            public void onSpanClick(int position) {
                CustomToast.makeTextWarn("position=" + position);
            }
        });

//        String text = "点击登录表示您同意《用户协议》《企业注册协议》《隐私权协议》";
//        String[] strings = {"《用户协议》", "《企业注册协议》", "《隐私权协议》"};
//        UtilityControl.setHotWordsText(tvTest1, text, strings, R.color.blue_a1ff, new ITextviewClickable() {
//            @Override
//            public void onSpanClick(int position) {
//                CustomToast.makeTextWarn("position=" + position);
//            }
//        });

        // 测试Map判空方法
//        Map<String,String> map = new HashMap<>();
//        UtilitySecurity.isEmpty(map);
//        HashMap<String,Object> maps = new HashMap<>();
//        UtilitySecurity.isEmpty(maps);
    }
}