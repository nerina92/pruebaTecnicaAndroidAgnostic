package com.example.challengenerinauliana.View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.RecyclerView;

import com.example.challengenerinauliana.Model.Album;
import com.example.challengenerinauliana.R;

/*Clase para completar la vista que se decribe en el xml custom_album_row con los datos de
un album (id, título), a partir del procesamiento de la lista de albums*/
public class CustomAdapterAlbum extends RecyclerView.Adapter<CustomAdapterAlbum.CustomViewHolder> {

    private List<Album> albumList;
    private Context context;

    public CustomAdapterAlbum(Context context, List<Album> dataList){
        this.context = context;
        this.albumList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtId;
        TextView txtTitle;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtId = mView.findViewById(R.id.tv_id);
            txtTitle = mView.findViewById(R.id.tv_title);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_album_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtId.setText(""+ albumList.get(position).getId());
        holder.txtTitle.setText(albumList.get(position).getTitle().toUpperCase(Locale.ROOT));

        holder.itemView.setOnClickListener(v->{
            Intent miIntent = new Intent(context, AlbumDetailActivity.class);
            miIntent.putExtra("selectAlbum", albumList.get(position).getId());
            context.startActivity(miIntent);
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
    public void setFilter(List<Album>filterAlbums){
        albumList =filterAlbums;
        notifyDataSetChanged();
    }

    public List<Album> getAlbumList() {
        return albumList;
    }
}