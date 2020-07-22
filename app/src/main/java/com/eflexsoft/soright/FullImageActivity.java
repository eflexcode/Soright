package com.eflexsoft.soright;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class FullImageActivity extends AppCompatActivity {

    PhotoView fullImage;
    TextView fullName;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        fullImage = findViewById(R.id.image_full);
        fullName = findViewById(R.id.full_text);

        intent = getIntent();

        String text = intent.getStringExtra("text");
        String image = intent.getStringExtra("imageUri");

        fullName.setText(text);
        Glide.with(this).load(image).into(fullImage);

        Toast.makeText(this, "you can zoom image", Toast.LENGTH_SHORT).show();
    }
}
