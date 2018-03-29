package com.example.zt.techer.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zt.techer.R;

public class RoleActivity extends AppCompatActivity {

    Button Teacher,Student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        Teacher=findViewById(R.id.Teacher);
        Student=findViewById(R.id.Student);


        Teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoleActivity.this, MainActivity.class));
                finish();
            }
        });

        Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoleActivity.this, Main3Activity.class));
                finish();
            }
        });

    }
}
