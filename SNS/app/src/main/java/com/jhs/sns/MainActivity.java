package com.jhs.sns;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jhs.sns.adapter.RecycleAdapter;
import com.jhs.sns.data.Post;
import com.jhs.sns.network.NetworkClient;
import com.jhs.sns.network.PostAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ImageButton btnAdd;
    RecyclerView rvPost;

    RecycleAdapter adapter;

    ArrayList<Post> listPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
        }

        listPost = new ArrayList<>();

        initUI();


    }

    @Override
    protected void onResume() {
        super.onResume();

        getPostList();
    }

    void initUI(){
        btnAdd = findViewById(R.id.btnAdd);
        rvPost = findViewById(R.id.rvPost);
        adapter = new RecycleAdapter(this);
        rvPost.setAdapter(adapter);
        LinearLayoutManager lManager = new LinearLayoutManager(this);
        lManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPost.setLayoutManager(lManager);
        adapter.setListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("post", listPost.get(position));
                startActivity(intent);

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("memberName", "JAMES");
                startActivity(intent);
            }
        });
    }

    void getPostList(){

        Retrofit retrofit = NetworkClient.getClient();
        PostAPI postAPI = retrofit.create(PostAPI.class);

        postAPI.getPostList(0).enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {

                if(response.code() == 200){
                    listPost = response.body();
                    adapter.replaceItem(listPost);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {

            }
        });
    }
}