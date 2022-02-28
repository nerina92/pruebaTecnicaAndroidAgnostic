package com.example.challengenerinauliana.Repository;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.example.challengenerinauliana.Model.Album;
import com.example.challengenerinauliana.Model.Photo;
import com.example.challengenerinauliana.Network.ApiDataService;
import com.example.challengenerinauliana.Network.RetrofitClient;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*Clase que llama al servicio web y obtiene los datos, luego devuelve los datos al ViewModel */
public class Repository {

    Context context;
    RecyclerView reciclerView;

    public Repository(Context context, RecyclerView reciclerView) {
        this.context = context;
        this.reciclerView=reciclerView;
    }

    public MutableLiveData<List<Album>>getAlbums(){
        ApiDataService apiDataService = RetrofitClient.getRetrofitInstance().create(ApiDataService.class);
        Call<List<Album>> call= apiDataService.getAllAlbums();
        final MutableLiveData<List<Album>> api_response = new MutableLiveData<>();
        //call.enqueque hace que la llamada no se realice en el proceso o hilo principal, sino en uno secundario.
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                //Llamada exitosa
                api_response.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                //Resultado erroneo
                api_response.setValue(null);
            }
        });
        return api_response;
    }
    public MutableLiveData<List<Photo>>getPhotos(int idAlbum){
        System.out.println("Album seleccionado desde Repository: "+idAlbum);
        ApiDataService apiDataService =RetrofitClient.getRetrofitInstance().create(ApiDataService.class);
        Call<List<Photo>> call= apiDataService.getAllPhotos(idAlbum);
        final MutableLiveData<List<Photo>> api_response = new MutableLiveData<>();
        //call.enqueque hace que la llamada no se realice en el proceso o hilo principal, sino en uno secundario.
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                //Llamada exitosa
                api_response.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                //Resultado erroneo
                api_response.setValue(null);
            }
        });
        return api_response;
    }
}
