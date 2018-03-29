package com.example.zt.techer.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zt.techer.R;
import com.example.zt.techer.Classes.TeacherClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText Username,Password;
    Button Sign;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Init();



        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });


    }

    void Init()
    {
        Username=findViewById(R.id.ET_User);
        Password=findViewById(R.id.ET_Pass);
        Sign=findViewById(R.id.Bt_Sign);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    void Register()
    {
        DatabaseReference id=mDatabase.child("Teacher").push();
        id.setValue(new TeacherClass(""+Username.getText(),""+Password.getText()));
    }

}
