package com.buzzbil.rxandroidsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.buzzbil.rxandroidsample.activity.RxViewActivity;
import com.buzzbil.rxandroidsample.activity.RxViewKtActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void startRxView(View view){
        startActivity(new Intent(this, RxViewActivity.class));
    }

    public void startRxViewKt(View view){
        startActivity(new Intent(this, RxViewKtActivity.class));
    }

}
