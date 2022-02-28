package com.example.challengenerinauliana.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.challengenerinauliana.Model.Photo;
import com.example.challengenerinauliana.R;
import com.example.challengenerinauliana.ViewModel.AlbumPhotoViewModel;
import com.example.challengenerinauliana.ViewModel.AlbumPhotoViewModelFactory;

import java.util.List;

/*Pantalla que muestra la lista de imágenes del álbum seleccionado.
 *Esta pantalla podría simplificarse, y usar el mismo main con dos fragments diferentes por ejemplo,
 que se carguen y refresquen según corresponda. Para el caso de un proyecto de mayor envergadura, es
  muy útil el uso de fragments, para reducir el tamaño del proyecto, mayor mantenibiliad, posibilidad
  de reutilización, entre otras ventajas. En este caso decidí realizarlo con un nuevo activity ya que
  se trataba solo de dos pantallas, pero no es una buena práctica aplicarlo a proyectos grandes.*/

public class AlbumDetailActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    int idAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        progressDialog = new ProgressDialog(AlbumDetailActivity.this);
        progressDialog.setMessage("Cargando....");
        progressDialog.show();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                idAlbum= -1;
            } else {
                idAlbum= extras.getInt("selectAlbum");
            }
        } else {
            idAlbum= (int) savedInstanceState.getSerializable("selectAlbum");
        }
        System.out.println("El album seleccionado es el de id "+idAlbum);
        recyclerView = findViewById(R.id.customRecyclerView2);
        setupViewModel();
    }
    private void setupViewModel() {
        AlbumPhotoViewModelFactory factory = new AlbumPhotoViewModelFactory(this,recyclerView);
        AlbumPhotoViewModel viewModel = ViewModelProviders.of(this,factory).get(AlbumPhotoViewModel.class);

        viewModel.getPhotos(idAlbum).observe(this, photos -> {
            if(photos.isEmpty()){
                progressDialog.dismiss();
                Toast.makeText(AlbumDetailActivity.this, "Algo saió mal, seguro puedes volver a intentarlo!", Toast.LENGTH_SHORT).show();
            }
            else{
                progressDialog.dismiss();
                generateDataList( photos );
            }
        });
    }

    private void generateDataList( List<Photo> photoList) {
        CustomAdapterPhoto adapter = new CustomAdapterPhoto(this, photoList);
        //System.out.println("Cantidad de fotos del album: "+ adapter.getItemCount());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }
}