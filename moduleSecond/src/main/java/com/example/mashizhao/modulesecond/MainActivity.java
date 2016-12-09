package com.example.mashizhao.modulesecond;

import android.os.Bundle;
import android.view.View;

import com.app.frame.BaseActivity;

import static com.app.utils.Utils.testString;

/**
 * Created by MaShiZhao on 2016/12/6.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_second_main_layout);
        testString(MainActivity.class.getName());
        findViewById(R.id.text_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(MainActivity.this,Main);
                MainActivity.this.finish();
            }
        });
    }


}
