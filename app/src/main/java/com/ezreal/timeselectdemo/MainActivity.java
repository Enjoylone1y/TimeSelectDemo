package com.ezreal.timeselectdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ezreal.timeselectview.DateSelectDialog;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btn_show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateSelectDialog dialog = new DateSelectDialog(MainActivity.this);
                dialog.setDateSelectedListener(new DateSelectDialog.OnDateSelectedListener() {
                    @Override
                    public void onSelected(int startYear, int startMonth, int startDay, int endYear,
                                           int endMonth, int endDay) {
                        String s_m = String.valueOf(startMonth);
                        String s_d = String.valueOf(startDay);
                        String e_m = String.valueOf(endMonth);
                        String e_d = String.valueOf(endDay);
                        if (s_m.length() == 1){
                            s_m = "0" + s_m;
                        }
                        if (s_d.length() == 1){
                            s_d = "0" + s_d;
                        }
                        if (e_m.length() == 1){
                            e_m = "0" + e_m;
                        }
                        if (e_d.length() == 1){
                            e_d = "0" + e_d;
                        }
                        String s_time = startYear + "-" + s_m + "-" + s_d;
                        String e_time = endYear + "-" + e_m + "-" + e_d;
                        Toast.makeText(MainActivity.this,s_time + "--" + e_time,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSelectedAll() {
                        Log.e("MainActivity","onSelectedAll");
                    }
                });
                dialog.show();
            }
        });

    }

}
