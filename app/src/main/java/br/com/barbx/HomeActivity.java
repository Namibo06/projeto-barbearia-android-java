package br.com.barbx;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class HomeActivity extends AppCompatActivity {
    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Altera a cor da barra de navegação para transparente
        getWindow().setNavigationBarColor(android.graphics.Color.TRANSPARENT);
        // Se você também quiser que a barra de status fique transparente
        getWindow().setStatusBarColor(android.graphics.Color.TRANSPARENT);

        // Para permitir que o conteúdo se estenda sob a barra de navegação
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ImageView imageView = (ImageView) findViewById(R.id.image_barber_background);

        Glide.with(this)
                .load(R.drawable.barber_bg)
                .into(imageView);
    }
}
