package libraries.renrui.com.myapplication;

import android.app.Application;
import android.net.Uri;
import android.text.TextUtils;

import com.renrui.libraries.util.LibUtility;
import com.renrui.libraries.util.LibrariesCons;
import com.renrui.libraries.util.mHttpClient;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RRApplication extends Application {


    public static final String PUBLIC_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmQEjEdEXNZewgCZt40SAtYx2p/+91Cesx6Ns3sSg22NQxOHW1Mnt/OBaAEyvZu733PlMQQGkm6AkJtswRr61" +
                    "Z5pRk4ZKMIxj8sx7m1+DpnJr5ZJDZ3fs8+2zQLtpPM0PADnkUm7MY0NjRGvPVKWiVOHwXaY823RJAAfG1Ks3a4KL1E6RBXLjNaN85uthK05QLuCGSoXuyc2pQRuuOykt" +
                    "EjYiqRofwJBTkYTQ5nGPuDVzIzPmlmu10WMmY39dMXr1l00EVPobdnuuQu+dHsOqMrg0cRkMY/344SK7KKCI74YMKARDaXcRF2Kdulg8l4l46GG/29HBaUo/rvVlfFE2fwIDAQAB";


    @Override
    public void onCreate() {
        super.onCreate();

        LibrariesCons.setContext(this);
        // 登录加密key
        LibrariesCons.setAboutLoginPublicKey(PUBLIC_KEY);

        mHttpClient.setUserAgentKey(" tfyetan-app-api");
        mHttpClient.setChannelName("test");
        mHttpClient.setUserAgent("tfyetan/1.0.0 (vivo X7Plus; Android 5.1.1; zh_CN; 00000000-46dd-33d4-9ccd-1a2a0033c587; cs:74d5751a; ch:renrui)");
        mHttpClient.setHttpCookie(getHost(), getCookMap());

        mHttpClient.setProxy("192.168.100.188", 8888);
    }

    public static String getHost() {
        String domain = "";

        try {
            final Uri uri = Uri.parse("https://test.xiangcaozhaopin.com/tfapp/");
            domain = uri.getHost();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return domain;
    }

    public HashMap<String, String> getCookMap() {
        HashMap<String, String> hp = new HashMap<>();
        hp.put("xguid", "HJzPzkWmGUUxDw8PTk5OTk8");
        hp.put("xgtok", "KpZcmlRoZSdkXIC6x1681cUuWmUk");
        hp.put("avatar", "USER");

        return hp;
    }
}