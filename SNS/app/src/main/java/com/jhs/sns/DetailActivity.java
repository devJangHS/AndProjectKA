package com.jhs.sns;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jhs.sns.data.Post;
import com.jhs.sns.network.NetworkClient;
import com.jhs.sns.network.PostAPI;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etContent;
    TextView tvMemberName;
    ImageView imgPhoto;
    Button btnAdd;

    File mFile;

    ActivityResultLauncher<Intent> resultLauncher;

    Retrofit retrofit;
    PostAPI postAPI;

    String memberName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        retrofit = NetworkClient.getClient();
        postAPI = retrofit.create(PostAPI.class);

        initUI();

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Uri uri = result.getData().getData();
                            imgPhoto.setImageURI(uri);

                            mFile = convertUriToFile(uri);

                        }
                    }
                });

    }

    void initUI(){

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        tvMemberName = findViewById(R.id.tvMemberName);
        imgPhoto = findViewById(R.id.imgPhoto);
        btnAdd = findViewById(R.id.btnAdd);


        Post post = (Post)getIntent().getSerializableExtra("post");
        memberName = getIntent().getStringExtra("memberName");

        if(post != null) {

            memberName = post.getMemberName();

            etTitle.setText(post.getTitle());
            etTitle.setEnabled(false);
            etContent.setText(post.getContent());
            etContent.setEnabled(false);

            if(post.getFile() != null)
                Glide.with(this).load(post.getFile()).into(imgPhoto);

            btnAdd.setVisibility(View.GONE);
        }else{

            imgPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    resultLauncher.launch(intent);
                }
            });

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Post post = new Post();
                    post.setTitle(etTitle.getText().toString());
                    post.setContent(etContent.getText().toString());
                    post.setMemberName(memberName);
                    post.setType(0);

                    String fileName = "0_"+System.currentTimeMillis()+".jpg";
                    post.setFile(fileName);

                    postAPI.createPost(post).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            if(response.code() == 200 && mFile != null)
                                uploadFile(fileName);

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });

                }
            });

        }

        tvMemberName.setText(memberName);
    }

    void uploadFile(String fileName){

        RequestBody fileBody =RequestBody.create(MediaType.parse("multipart/form-data"), mFile);
        MultipartBody.Part bodyPart = MultipartBody.Part.createFormData("file", fileName, fileBody);


        postAPI.uploadFile(bodyPart).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200){
                    Toast.makeText(DetailActivity.this, "POST 등록 성공", Toast.LENGTH_SHORT).show();
                    DetailActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


    File convertUriToFile(Uri uri) {

        if(uri == null ) {
            return null;
        }

        Bitmap bitmap;
        File file = null;

        try {
            bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            file = new File(Environment.getExternalStorageDirectory() + File.separator + "123" + System.currentTimeMillis());
            file.createNewFile();

            if(bitmap != null){

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20 , bos); // YOU can also save it in JPEG
                byte[] bitmapdata = bos.toByteArray();

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bitmapdata);
                fos.flush();
                fos.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return file;
    }
}
