package br.com.barbx;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

public class HomeActivity extends AppCompatActivity {
    private WebView iframeMaps;

    @SuppressLint({"ResourceType", "SetJavaScriptEnabled"})
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

        ImageView imageView = findViewById(R.id.image_barber_background);

        Glide.with(this)
                .load(R.drawable.barber_bg)
                .into(imageView);

        /*iframe google maps*/
        iframeMaps = findViewById(R.id.iframe_maps);
        iframeMaps.setBackgroundColor(Color.parseColor("#49403B"));
        WebSettings webSettings = iframeMaps.getSettings();
        webSettings.setJavaScriptEnabled(true);
        iframeMaps.getSettings().setLoadWithOverviewMode(true);
        iframeMaps.getSettings().setUseWideViewPort(true);
        iframeMaps.setWebViewClient(new WebViewClient());

        String htmlIframe = "<html><head>" +
                "<style>" +
                "body { margin: 0; padding: 0; } " +
                "iframe { width: 100%; height: 1200px; border-radius: 30px; border: 0; } " +
                "</style>" +
                "</head><body>" +
                "<iframe " +
                "loading=\"lazy\" " +
                "allowfullscreen " +
                "src=\"https://www.google.com/maps/embed/v1/place?key=AIzaSyAShdP6sUs0mL-nYUGA60B_9J5tRkgKvUI&q=Av. Artêmia Pires Freitas, s/n - Sim, Feira de Santana - BA, 44085-370\">" +
                "</iframe>" +
                "</body></html>";

        iframeMaps.loadData(htmlIframe,"text/html","UTF-8");
    }
}
