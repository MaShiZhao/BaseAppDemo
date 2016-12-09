package com.example.baseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.http.Bean.BaseBean;
import com.app.http.HttpGet;
import com.app.http.HttpPost;
import com.app.http.InterfaceHttpResult;
import com.example.baseapp.http.HttpUrl;
import com.example.mashizhao.modulefirst.ModuleFirstActivity;
import com.resources.utils.GlideUtils;

import static com.app.utils.Utils.testString;

public class MainActivity extends AppCompatActivity implements InterfaceHttpResult {

    private TextView title;
    private TextView title2;
    private ImageView imageViewWrap;
    private ImageView imageView40;
    private ImageView imageView60;


//    params.add(new BasicNameValuePair("account", phone));
//    params.add(new BasicNameValuePair("password", pwd));
//    params.add(new BasicNameValuePair("isCloudLogin", isCloudLogin));
//    String url = "http://192.168.3.6:88/Mobile/Index/loginAction?deviceId=ll&debug=1;account;password;isCloudLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        testString(MainActivity.class.getName());

        title = (TextView) findViewById(R.id.title);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new HttpGet(MainActivity.this, BaseBean.class, "http://192.168.3.6:88/Mobile/Index/getUserInfoAction&debug=1");
                HttpPost httpPost = new HttpPost(MainActivity.this, BaseBean.class, HttpUrl.LOGIN, "15312345678", "112233", "0");
                httpPost.excute(10);

            }
        });

        findViewById(R.id.title2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpGet httpGet = new HttpGet(MainActivity.this, BaseBean.class, "http://192.168.3.6:88/Mobile/Index/getUserInfoAction&debug=1");
                httpGet.excute();
            }
        });

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ModuleFirstActivity.class));
            }
        });


        findViewById(R.id.textView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.example.mashizhao.modulesecond.MainActivity.class));
            }
        });


        String url = "https://disqus-cloudfront.s3.amazonaws.com/docs/spreadsheet-example.png";
        imageViewWrap = (ImageView) findViewById(R.id.image_wrap);
        GlideUtils.loadCourse(MainActivity.this, url, imageViewWrap);
        imageView40 = (ImageView) findViewById(R.id.image_40dp);
        GlideUtils.loadRoundCourse(MainActivity.this, url, imageView40);
//        ImageUtils.loadRoundImage(MainActivity.this, url, R.drawable.check, 30,imageView40);
        imageView60 = (ImageView) findViewById(R.id.image_60dp);
        GlideUtils.loadRoundCourse(MainActivity.this, url, imageView60);


    }


    @Override
    public void getCallback(int resultCode, Object result) {

        BaseBean stringBean = (BaseBean) result;
        title.setText(resultCode + stringBean.getMsg() + stringBean.getCode());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
