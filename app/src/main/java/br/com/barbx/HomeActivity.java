package br.com.barbx;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class HomeActivity extends AppCompatActivity {
    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView imageView = (ImageView) findViewById(R.id.image_barber_background);

        Glide.with(this)
                .load(R.drawable.barber_bg)
                .into(imageView);
    }
}
