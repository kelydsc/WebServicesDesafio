package br.com.digitalhouse.webservicesdesafio.view.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.webservicesdesafio.R;
import br.com.digitalhouse.webservicesdesafio.adapters.RecyclerViewComicsAdapter;
import br.com.digitalhouse.webservicesdesafio.model.Result;
import br.com.digitalhouse.webservicesdesafio.viewmodel.ComicsViewModel;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewComics;
    private RecyclerViewComicsAdapter adapterComics;
    private ComicsViewModel viewModelComics;

    //private List<Result> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        initViews();

        viewModelComics.getComics();
        // Atualiza a lista com os quadriho buscados na API
        viewModelComics.getResults().observe(this, (List<Result> results) -> {
            adapterComics.update(results);
        });
    }

    private void initViews() {
        viewModelComics = ViewModelProviders.of(this).get(ComicsViewModel.class);
        recyclerViewComics = findViewById(R.id.recyclerViewComics);
        adapterComics = new RecyclerViewComicsAdapter(new ArrayList<>());
        recyclerViewComics.setAdapter(adapterComics);
        recyclerViewComics.setLayoutManager(new GridLayoutManager(this, 3));
    }
}
