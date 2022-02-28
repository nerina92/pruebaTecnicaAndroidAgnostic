package com.example.challengenerinauliana.ViewModel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.challengenerinauliana.Model.Album;
import com.example.challengenerinauliana.Model.Photo;
import com.example.challengenerinauliana.Repository.Repository;

import java.util.List;

/*Usando Repository, se procesan los datos y se emiten al view.  View model no tiene una referencia
 directa de las vistas, mientras que las vistas tiene una referencia directa del modelo de vista.
 Desde esta clase se emiten los datos que luego se pueden obtener utilizando observadores creados
 en el momento de la referencia al modelo de vista. Las MutableLiveData que devuelven los métodos
 de esta clase serán observada por los diferentes activities que las usan*/


public class AlbumPhotoViewModel extends ViewModel {
    Repository repository;

    public AlbumPhotoViewModel(Context context, RecyclerView recyclerView) {
        this.repository = new Repository(context,recyclerView);
    }

    /*Los dos métodos siguientes (getAlbums y getPhotos) llaman al repositorio desde el ModelView*/
    public MutableLiveData<List<Album>> getAlbums() {
        return repository.getAlbums();
    }
    public MutableLiveData<List<Photo>> getPhotos(int idAlbum) {
        return repository.getPhotos(idAlbum);
    }
}
