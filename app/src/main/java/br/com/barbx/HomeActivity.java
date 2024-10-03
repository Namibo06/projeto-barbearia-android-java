package br.com.barbx;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private WebView iframeMaps;
    private Map<String,String> envVariables = new HashMap<>();

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

        /*env file*/
        loadEnvFromAssets();
        String apiMapsKey = envVariables.get("API_MAPS_KEY");

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
                "src=\"https://www.google.com/maps/embed/v1/place?key="+apiMapsKey+"&q=Av. Artêmia Pires Freitas, s/n - Sim, Feira de Santana - BA, 44085-370\">" +
                "</iframe>" +
                "</body></html>";

        iframeMaps.loadData(htmlIframe,"text/html","UTF-8");
    }

    private void loadEnvFromAssets(){
        AssetManager assetManager = getAssets();
        try{
            InputStream inputStream = assetManager.open("env.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while((line = reader.readLine()) != null){
                String[] keyValue = line.split("=");
                if(keyValue.length == 2){
                    envVariables.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }
            reader.close();
        }catch (IOException ex){
            Log.e("env api","Erro ao carregar o arquivo env.txt:"+ex.getMessage());
        }
    }
}
