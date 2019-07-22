package br.com.digitalhouse.webservicesdesafio.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import br.com.digitalhouse.webservicesdesafio.R;
import br.com.digitalhouse.webservicesdesafio.view.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    //Declaracao de atributos
    private ImageView imageViewSplash;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Inicializa as views
        initViews();

        //Ação ao clicar na Imagem
        clickImage();

        //Tempo de execibição da imagem na tela
        tempoExibirImagem();
    }

    private void initViews() {
        imageViewSplash = findViewById(R.id.imageViewSplash);
    }

    private void clickImage() {
        imageViewSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Chama a tela de Home
                jump();
            }
        });
    }

    private void tempoExibirImagem() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                jump();
            }
        }, 3000);
    }

    private void jump() {

        timer.cancel();

        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);

        startActivity(intent);

        finish();
    }
}
