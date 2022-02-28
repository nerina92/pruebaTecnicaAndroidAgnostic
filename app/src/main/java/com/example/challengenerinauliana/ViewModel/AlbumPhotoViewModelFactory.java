package com.example.challengenerinauliana.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumPhotoViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    RecyclerView recyclerView;
    Context context;

    /*Método de fábrica que nos permite crear un ViewModel con un constructor que toma
    un recyclerview y un contexto como parámetros*/
    public AlbumPhotoViewModelFactory(Context context, RecyclerView recyclerView) {
        this.recyclerView=recyclerView;
        this.context=context;
    }

//uso de genéricos
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new AlbumPhotoViewModel(context,recyclerView);
    }
}
