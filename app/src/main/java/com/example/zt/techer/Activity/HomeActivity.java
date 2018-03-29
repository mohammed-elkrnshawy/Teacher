package com.example.zt.techer.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zt.techer.Classes.StudentAdapter;
import com.example.zt.techer.Classes.StudentClass;
import com.example.zt.techer.Classes.TeacherClass;
import com.example.zt.techer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    TeacherClass aClass;
    Dialog dialog;
    FloatingActionButton floatingActionButton;
    ListView listView;

    List<StudentClass> studentClasses=new ArrayList<>();
StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        Init();
        
        TeacherData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });


        ReturnStudent();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               StudentClass studentClass= (StudentClass) parent.getItemAtPosition(position);
                Intent intent=new Intent(HomeActivity.this,Main2Activity.class);
                intent.putExtra("StudentObject",studentClass);
                startActivity(intent);
            }
        });

    }

    private void ShowDialog()
    {
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.veiw_add);
        dialog.setTitle("Add Student ...");


        final EditText Name = dialog.findViewById(R.id.edittext);
        Button btn = dialog.findViewById(R.id.Btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDate(Name.getText()+"");
            }
        });
        dialog.show();
    }

    private void addDate(String Name) {
        if (Name.isEmpty()) {
            Toast.makeText(this, "Name Is Empty", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference id = mDatabase.child("Student").child(aClass.getUid()).push();
            id.setValue(new StudentClass(Name));
            dialog.dismiss();
        }
    }

    private void TeacherData() {

        Bundle extras = getIntent().getExtras();
        if ((!extras.isEmpty())) {
            aClass= (TeacherClass) extras.getSerializable("TeacherObject");
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
        Query query = reference.child("Student").child(aClass.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentClasses.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    studentClasses.add(new StudentClass(snapshot.child("name").getValue()+""
                            ,snapshot.getKey()+""));
                }


                studentAdapter=new StudentAdapter(HomeActivity.this,studentClasses);
                listView.setAdapter(studentAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
