package com.example.challengenerinauliana.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.challengenerinauliana.Model.Photo;
import com.example.challengenerinauliana.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

/*Clase para completar la vista que se decribe en el xml custom_photo_row con los datos de
imágenes de album(id, título, imágen)un album (id, título), a partir del procesamiento de la lista de photos*/

public class CustomAdapterPhoto extends RecyclerView.Adapter<CustomAdapterPhoto.CustomViewHolderPhoto>{

    private List<Photo> photoList;
    private Context context;

    public CustomAdapterPhoto(Context context, List<Photo> dataList){
        this.context = context;
        this.photoList = dataList;
    }

    class CustomViewHolderPhoto extends RecyclerView.ViewHolder {

        public final View mView;

        ImageView imgView;
        TextView txtId;
        TextView txtTitle;

        CustomViewHolderPhoto(View itemView) {
            super(itemView);
            mView = itemView;

            imgView=mView.findViewById(R.id.image_view);
            imgView.setMinimumHeight(imgView.getWidth());
            txtId = mView.findViewById(R.id.tv_id);
            txtTitle = mView.findViewById(R.id.tv_title);
        }
    }

    @Override
    public CustomViewHolderPhoto onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_photo_row, parent, false);
        return new CustomViewHolderPhoto(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapterPhoto.CustomViewHolderPhoto holder, int position) {
        holder.txtId.setText(""+ photoList.get(position).getId());
        holder.txtTitle.setText(photoList.get(position).getTitle().toUpperCase(Locale.ROOT));
        Picasso.get().load(photoList.get(position).getThumbnailUrl()).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
}
