package com.example.mashizhao.modulefirst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.frame.BaseActivity;
import com.example.mashizhao.modulesecond.MainActivity;

import static com.app.utils.Utils.testString;

/**
 * Created by MaShiZhao on 2016/12/6.
 */

public class ModuleFirstActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ModuleFirstActivity.this, MainActivity.class));
            }
        });

        testString(ModuleFirstActivity.class.getName());
    }

}
