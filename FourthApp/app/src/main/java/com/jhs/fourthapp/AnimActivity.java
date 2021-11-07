package com.jhs.fourthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimActivity extends AppCompatActivity {

    ImageView imgStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);

        imgStar = findViewById(R.id.imgStar);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_sample);
        imgStar.startAnimation(animation);
        
        //Git 테스트 용 변경 사항



    }
}