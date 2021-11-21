package com.jhs.fourthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.jhs.fourthapp.api.APIClient;
import com.jhs.fourthapp.api.MemberAPI;
import com.jhs.fourthapp.data.Member;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkActivity extends AppCompatActivity {

    Retrofit retrofit;
    MemberAPI memberAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        retrofit = APIClient.getClient();
        memberAPI = retrofit.create(MemberAPI.class);
        
    }


    void regist(){

        Member member = new Member();
        member.setLoginId("jang4131");
        member.setPassword("abcd");
        member.setName("이름");               //EditText 직접 받아오게
        member.setType(0);

        memberAPI.regist(member).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.code() == 200){
                    Toast.makeText(NetworkActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }
}