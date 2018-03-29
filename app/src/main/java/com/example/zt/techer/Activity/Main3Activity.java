package com.example.zt.techer.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zt.techer.Classes.StudentClass;
import com.example.zt.techer.Classes.TeacherClass;
import com.example.zt.techer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {

    StudentClass studentClass;
    EditText editText;
    Button button;

    boolean b=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        editText = findViewById(R.id.ET_Userr);
        button = findViewById(R.id.Bt_Login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    Login(editText.getText() + "");

                } catch (Exception e) {
                    Log.d("A", e.getMessage());
                }
            }
        });

    }


    void Login(final String Name) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("Student");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        if(Name.equals(snapshot1.child("name").getValue()))
                        {
                            studentClass=new StudentClass(snapshot1.child("name").getValue()+"",snapshot1.getKey());
                            b=false;

                        }
                    }
                }

                if(!b)
                {
                    Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                    intent.putExtra("StudentObject",studentClass);
                    intent.putExtra("Flag",true);
                    startActivity(intent);
                }
                else 
                {
                    Toast.makeText(Main3Activity.this, "Not", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
