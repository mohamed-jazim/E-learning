package com.kazimasum.videostreaming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class cse_sem_three extends AppCompatActivity {

    LinearLayout mat_200,cst_201,cst_203,mnc_202,cst_205,est_200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cse_sem_three);

        mat_200 =(LinearLayout) findViewById(R.id.mat200);
        mat_200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cse_sem_three.this, s3_mat_200.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


        cst_201 =(LinearLayout) findViewById(R.id.cst201);
        cst_201.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cse_sem_three.this, s3_cst_201.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        cst_203 =(LinearLayout) findViewById(R.id.cst203);
        cst_203.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cse_sem_three.this, s3_cst_203.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });





    }
}