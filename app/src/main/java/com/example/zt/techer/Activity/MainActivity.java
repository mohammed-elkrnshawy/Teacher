package com.example.zt.techer.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zt.techer.R;
import com.example.zt.techer.Classes.TeacherClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText Username,Password;
    Button Login,Sign;
    TeacherClass teacherClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();



        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(Username.getText()+"",Password.getText()+"");
            }
        });

    }

    void Init()
    {
        Username=findViewById(R.id.ET_User);
        Password=findViewById(R.id.ET_Pass);
        Login=findViewById(R.id.Bt_Login);
        Sign=findViewById(R.id.Bt_Sign);
    }
    void Login(String Name, final String Pass)
    {
        final boolean[] b = new boolean[1];
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("Teacher").orderByChild("name").equalTo(Name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    if (snapshot.child("password").getValue().equals(Pass))
                    {
                        teacherClass=new TeacherClass(snapshot.child("name").getValue()+"",
                                snapshot.child("password").getValue()+"",
                                snapshot.getKey()+"");
                        b[0] =false;
                        break;
                    }
                }


                if(!b[0])
                {
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                    intent.putExtra("TeacherObject", teacherClass);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    void Register()
    {
        startActivity(new Intent(this,RegisterActivity.class));
    }
}
