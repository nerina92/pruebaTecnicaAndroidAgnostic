package com.example.challengenerinauliana.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.challengenerinauliana.Model.Album;
import com.example.challengenerinauliana.R;
import com.example.challengenerinauliana.ViewModel.AlbumPhotoViewModel;
import com.example.challengenerinauliana.ViewModel.AlbumPhotoViewModelFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*Pantalla principal de la aplicación que muestra una lista de álbums que obtiene de una API web*/
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private CustomAdapterAlbum adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    List<Album> filterString, allAlbumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Cargando....");
        progressDoalog.show();

        recyclerView = findViewById(R.id.customRecyclerView);
        setupViewModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
        //Log.d("SEARCH VIEW",searchView.toString());
        searchView.setOnQueryTextListener(this);
        return true;
    }


    private void setupViewModel() {
        /*Con este método se pueden observar los datos en vivo para actualizar la pantalla de la
        interfaz de usuario cada vez que haya un cambio en los datos de caché.*/
        AlbumPhotoViewModelFactory factory = new AlbumPhotoViewModelFactory(this,recyclerView);
        AlbumPhotoViewModel viewModel = ViewModelProviders.of(this,factory).get(AlbumPhotoViewModel.class);

        viewModel.getAlbums().observe(this, albums -> {
            if(albums.isEmpty()){
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Algo saió mal, seguro puedes volver a intentarlo!", Toast.LENGTH_SHORT).show();
            }
            else{
                progressDoalog.dismiss();
                generateDataList( albums );
            }
        });
    }

    private void generateDataList( List<Album> albumList) {
        allAlbumList=albumList;
        adapter = new CustomAdapterAlbum(this,albumList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        filterString=filter(allAlbumList,s);
        adapter.setFilter(filterString);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filterString=filter(allAlbumList,s);
        adapter.setFilter(filterString);
        return true;
    }
    private List<Album>filter(List<Album>albums,String text){
        System.out.println("TEXTO A BUSCAR: "+text);
        filterString=new ArrayList<>();

        for (Album album:albums){
            if (album.getTitle().toUpperCase(Locale.ROOT).contains(text.toUpperCase(Locale.ROOT))) {
                filterString.add(album);
            }
        }
        return filterString;
    }

}