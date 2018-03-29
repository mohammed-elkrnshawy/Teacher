package com.example.zt.techer.Activity;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zt.techer.Classes.StudentAdapter;
import com.example.zt.techer.Classes.StudentClass;
import com.example.zt.techer.Classes.TeacherClass;
import com.example.zt.techer.Classes.TestAdapter;
import com.example.zt.techer.Classes.TestClass;
import com.example.zt.techer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    StudentClass aClass;
    Dialog dialog;
    FloatingActionButton floatingActionButton;
    ListView listView;

    boolean b;

    List<TestClass>testClassList=new ArrayList<>();
    TestAdapter testAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        Init();

        StudentData();


        if(b)
        {
            floatingActionButton.setVisibility(View.INVISIBLE);
        }


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });

        try {
            ReturnStudent();
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }




    private void ShowDialog()
    {
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.view_add_test);
        dialog.setTitle("Add Student ...");


        final EditText Name = dialog.findViewById(R.id.edittext);
        final EditText Degree = dialog.findViewById(R.id.edittext2);
        Button btn = dialog.findViewById(R.id.Btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTest(Name.getText()+"",Degree.getText()+"");
            }
        });
        dialog.show();
    }

    private void addTest(String Name,String Degree) {
        if (Name.isEmpty()) {
            Toast.makeText(this, "Name Is Empty", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference id = mDatabase.child("Test").child(aClass.getUid()).push();
            id.setValue(new TestClass(Name,Double.parseDouble(Degree)));
            dialog.dismiss();
        }
    }

    private void StudentData() {

        Bundle extras = getIntent().getExtras();
        if ((!extras.isEmpty())) {
            aClass= (StudentClass) extras.getSerializable("StudentObject");
            b=extras.getBoolean("Flag",false);
        }
        else {
            Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show();
        }
    }

    private void Init() {
        floatingActionButton=findViewById(R.id.F);
        listView=findViewById(R.id.LV);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    private void ReturnStudent()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("Test").child(aClass.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                testClassList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    testClassList.add(new TestClass(snapshot.child("testName").getValue()+""
                            ,Double.parseDouble(snapshot.child("testDegree").getValue()+"")));
                }


                testAdapter=new TestAdapter(Main2Activity.this,testClassList);
                listView.setAdapter(testAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
