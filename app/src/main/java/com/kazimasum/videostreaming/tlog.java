package com.kazimasum.videostreaming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class tlog extends AppCompatActivity {

    EditText t1,t2;
    Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tlog);
        t1 = (EditText) findViewById(R.id.ede);
        t2 = (EditText) findViewById(R.id.p11);

        bt = (Button) findViewById(R.id.buttonj);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.getText().toString().equals("admin")&&t2.getText().toString().equals("admin")){

                    Intent intent = new Intent(tlog.this, uploadfile.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    Toast.makeText(tlog.this,"Lognin sucessfull",Toast.LENGTH_SHORT);

                } else if (t1.getText().toString().equals("mat")&&t2.getText().toString().equals("mat")) {

                    Intent intent = new Intent(tlog.this, ucse2.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    Toast.makeText(tlog.this,"Lognin sucessfull",Toast.LENGTH_SHORT);



                }

                else if (t1.getText().toString().equals("est")&&t2.getText().toString().equals("est")) {

                    Intent intent = new Intent(tlog.this, uest_100.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    Toast.makeText(tlog.this,"Lognin sucessfull",Toast.LENGTH_SHORT);



                }

                else if (t1.getText().toString().equals("cyt")&&t2.getText().toString().equals("cyt")) {

                    Intent intent = new Intent(tlog.this, ucyt_100.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    Toast.makeText(tlog.this,"Lognin sucessfull",Toast.LENGTH_SHORT);



                }

                else if (t1.getText().toString().equals("est110")&&t2.getText().toString().equals("est110")) {

                    Intent intent = new Intent(tlog.this, uest_110.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    Toast.makeText(tlog.this,"Lognin sucessfull",Toast.LENGTH_SHORT);



                }

                else if (t1.getText().toString().equals("est102")&&t2.getText().toString().equals("est102")) {

                    Intent intent = new Intent(tlog.this, uest_102.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    Toast.makeText(tlog.this,"Lognin sucessfull",Toast.LENGTH_SHORT);



                }

                else if (t1.getText().toString().equals("hut102")&&t2.getText().toString().equals("hut102")) {

                    Intent intent = new Intent(tlog.this, uhut_102.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    Toast.makeText(tlog.this,"Lognin sucessfull",Toast.LENGTH_SHORT);



                }

                else if (t1.getText().toString().equals("mat102")&&t2.getText().toString().equals("mat102")) {

                    Intent intent = new Intent(tlog.this, umat_102.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    Toast.makeText(tlog.this,"Lognin sucessfull",Toast.LENGTH_SHORT);



                }

                else if (t1.getText().toString().equals("mat200")&&t2.getText().toString().equals("mat200")) {

                    Intent intent = new Intent(tlog.this, us3_mat_200.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    Toast.makeText(tlog.this,"Lognin sucessfull",Toast.LENGTH_SHORT);



                }

                else if (t1.getText().toString().equals("cst201")&&t2.getText().toString().equals("cst201")) {

                    Intent intent = new Intent(tlog.this, us3_sct_201.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    Toast.makeText(tlog.this,"Lognin sucessfull",Toast.LENGTH_SHORT);



                }

                else if (t1.getText().toString().equals("cst201")&&t2.getText().toString().equals("cst201")) {

                    Intent intent = new Intent(tlog.this, us3_sct_201.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    Toast.makeText(tlog.this,"Lognin sucessfull",Toast.LENGTH_SHORT);



                }

                else if (t1.getText().toString().equals("cst203")&&t2.getText().toString().equals("cst203")) {

                    Intent intent = new Intent(tlog.this, us3_cst_203.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    Toast.makeText(tlog.this,"Lognin sucessfull",Toast.LENGTH_SHORT);



                }








                else {
                    Toast.makeText(tlog.this,"Lognin unsucessfull",Toast.LENGTH_SHORT);
                }



            }
        });





    }
}