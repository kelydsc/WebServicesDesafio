package br.com.digitalhouse.webservicesdesafio.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.digitalhouse.webservicesdesafio.R;
import br.com.digitalhouse.webservicesdesafio.model.Result;
import br.com.digitalhouse.webservicesdesafio.view.detalhe.DetalheComicsActivity;

public class RecyclerViewComicsAdapter extends RecyclerView.Adapter<RecyclerViewComicsAdapter.ViewHolder> {

    private List<Result> results;

    public RecyclerViewComicsAdapter(List<Result> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comics_recyclerview_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Result result = results.get(position);
        viewHolder.bind(result);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String transitionName = "image_" + position;
                Intent intent = new Intent(viewHolder.itemView.getContext(), DetalheComicsActivity.class);
                intent.putExtra("comic", result);
                intent.putExtra("transitionName", transitionName);

                viewHolder.imageViewHq.setTransitionName(transitionName);

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) viewHolder.itemView.getContext(),
                                viewHolder.imageViewHq, transitionName);

                viewHolder.itemView.getContext().startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewHq;
        private TextView textViewHqNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewHq = itemView.findViewById(R.id.imageViewComics);
            textViewHqNumber = itemView.findViewById(R.id.textViewComicsNumero);
        }

        private void bind(Result result) {
            Picasso.get().load(result.getThumbnail().getPath() + "/portrait_incredible." + result.getThumbnail().getExtension())
                    .placeholder(R.drawable.ic_marvel_logo)
                    .error(R.drawable.ic_marvel_logo)
                    .into(imageViewHq);

            textViewHqNumber.setText("# " + result.getIssueNumber());
        }
    }

    public void clear() {
        this.results.clear();
        notifyDataSetChanged();
    }

    public void update(List<Result> resultList) {
        this.results = resultList;
        notifyDataSetChanged();
    }
}
