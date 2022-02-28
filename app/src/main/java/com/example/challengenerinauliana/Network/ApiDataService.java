package com.example.challengenerinauliana.Network;

import com.example.challengenerinauliana.Model.Album;
import com.example.challengenerinauliana.Model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/* Interfaz para definir todas las llamadas de API con:
 * solicitud,
 * tipo de respuesta y
 * parámetros de solicitud, para cada llamada*/

public interface ApiDataService {

    @GET("/albums")
    Call<List<Album>> getAllAlbums();

    @GET("/photos")
    Call<List<Photo>> getAllPhotos(@Query("albumId") Integer id);

}
