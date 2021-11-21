package com.jhs.fourthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jhs.fourthapp.api.APIClient;
import com.jhs.fourthapp.api.MemberAPI;
import com.jhs.fourthapp.data.Member;

import java.util.List;

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

        getMemberList();

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
                t.printStackTrace();
            }
        });

    }

    void login(){


        Member member = new Member();
        member.setLoginId("admin");
        member.setPassword("1234");
        member.setType(0);
        
        memberAPI.login(member).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200)
                    Toast.makeText(NetworkActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(NetworkActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(NetworkActivity.this, "통신 오류", Toast.LENGTH_SHORT).show();

            }
        });

    }

    void getMemberList(){

        memberAPI.getMemberList(0).enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {

                if(response.code() == 200){

                    List<Member> list = response.body();

                    for(int i = 0; i < list.size(); i ++){

//                        adapter.addItem(new Member(list.get(i).getLoginId(), list.get(i).getName()));


                    }

                }

            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {

            }
        });











    }
}