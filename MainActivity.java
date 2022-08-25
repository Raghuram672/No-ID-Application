package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,usn,time;
    Button save,view;
    TextView output;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        usn=findViewById(R.id.usn);
        time=findViewById(R.id.time);
        save=findViewById(R.id.btn_save);
        view=findViewById(R.id.btn_view);
        output=findViewById(R.id.display);
        db=new DatabaseHelper(MainActivity.this,"Students",null,1);
        //saving the data
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentname=name.getText().toString();
                String rollnum=usn.getText().toString();
                String date=time.getText().toString();
                long recordid=db.saveNewUserData(studentname,rollnum,date);// to insert data
              if(recordid>0)
                  Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_LONG).show();
              else
                  Toast.makeText(getApplicationContext(),"Not saved",Toast.LENGTH_LONG).show();
            }
        });
        //to view all
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String student_data=db.getAllRecords();
                    output.setText(student_data);
            }
        });
    }
}