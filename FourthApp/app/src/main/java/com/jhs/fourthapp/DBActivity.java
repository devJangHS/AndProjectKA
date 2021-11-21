package com.jhs.fourthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jhs.fourthapp.adapter.MemberRecycleAdapter;
import com.jhs.fourthapp.data.Member;
import com.jhs.fourthapp.helper.MyDBHelper;


public class DBActivity extends AppCompatActivity {

    MyDBHelper dbHelper;
    SQLiteDatabase sqlDB;

    EditText etName, etId;
    Button btnAdd;

    RecyclerView rvMember;
    MemberRecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        dbHelper = new MyDBHelper(this);

        etName = findViewById(R.id.etName);
        etId = findViewById(R.id.etId);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString();
                String id = etId.getText().toString();

                adapter.addItem(new Member(id, name));
                insertDB(id, name);

            }
        });

        rvMember = findViewById(R.id.rvMember);
        adapter = new MemberRecycleAdapter(this);
        rvMember.setAdapter(adapter);
        LinearLayoutManager lManager = new LinearLayoutManager(this);
        rvMember.setLayoutManager(lManager);

        selectDB();

    }

    void insertDB(String id, String name){

        sqlDB = dbHelper.getWritableDatabase();
        sqlDB.execSQL("INSERT INTO Member(id, name) VALUES('" + id + "','" + name + "')");
        sqlDB.close();

    }

    void selectDB(){

        sqlDB = dbHelper.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM Member",null);

        while(cursor.moveToNext()) {

            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("name");

            String id = cursor.getString(idIndex);
            String name = cursor.getString(nameIndex);

            adapter.addItem(new Member(id, name));
        }

        cursor.close();
        sqlDB.close();

    }


}