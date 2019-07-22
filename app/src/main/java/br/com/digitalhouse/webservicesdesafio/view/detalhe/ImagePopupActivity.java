package br.com.digitalhouse.webservicesdesafio.view.detalhe;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import br.com.digitalhouse.webservicesdesafio.R;

public class ImagePopupActivity extends AppCompatActivity {

    private ImageView imageComic;
    private ImageView imageClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_image_popup);

        // Inicializa as views que serão utilizadas na activity
        imageComic = findViewById(R.id.imageComic);
        imageClose = findViewById(R.id.imageViewClose);

        // Pegamos o quadrinho que que foi clicado na imagem anterior
        String image = getIntent().getStringExtra("image");

        // Carregamos a imagem
        Picasso.get().load(image)
                .placeholder(R.drawable.ic_marvel_logo)
                .error(R.drawable.ic_marvel_logo)
                .into(imageComic);

        // Adidionamos o evento de click para fechar-mos a tela
        imageClose.setOnClickListener(v -> finish());
    }
}

