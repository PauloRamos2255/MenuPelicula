package com.example.myapplication.Respositorio;

import com.example.myapplication.Conexion.APIConexion;
import com.example.myapplication.Conexion.MovieAPI;
import com.example.myapplication.Entidad.Movie;
import com.example.myapplication.Entidad.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieRepository {
    private final MovieAPI movieApi;
    String language = "es-ES";

    public MovieRepository() {
        // Utiliza tu método getClient() de ApiConexion para obtener el cliente Retrofit
        Retrofit retrofit = APIConexion.getClient();

        // Crea una instancia de la interfaz de la API
        movieApi = retrofit.create(MovieAPI.class);
    }

    public void getPopularMovies(String apiKey, final OnMoviesCallback callback) {
        // Realiza la solicitud a la API (asumiendo que apiKey es tu clave de API)
        Call<MovieResponse> call = movieApi.getPopularMovies(apiKey,language);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();

                    if (movieResponse != null) {
                        List<Movie> movies = movieResponse.getResults();
                        callback.onSuccess(movies);
                    }
                } else {
                    callback.onError("Error al obtener la lista de películas");
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onError("Error de red al obtener la lista de películas");
            }
        });
    }

    public interface OnMoviesCallback {
        void onSuccess(List<Movie> movies);

        void onError(String errorMessage);
    }


    public void getCarteleraMovies(String apiKey, final MoviesCallback callback){

        Call<MovieResponse> call = movieApi.getCarteleraMovies(apiKey ,language );
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null) {
                        List<Movie> movies = movieResponse.getResults();
                        callback.onSuccess(movies);
                    }
                } else {
                    callback.onError("Error al obtener la lista de películas");
                }
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onError("Error de red al obtener la lista de películas");
            }
        });
    }

    public interface MoviesCallback {
        void onSuccess(List<Movie> moviess);

        void onError(String errorMessage);
    }


    public void getSearchMovies(String apiKey, String query,final SearchCallback callback){
        Call<MovieResponse> call = movieApi.getSearchMovies(apiKey ,language, query);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null) {
                        List<Movie> movies = movieResponse.getResults();

                        List<Movie> filteredMovies = new ArrayList<>();
                        String searchText = query.toLowerCase();

                        for (Movie movie : movies) {
                            if (movie.getTitle().toLowerCase().contains(searchText)) {
                                filteredMovies.add(movie);
                            }
                        }

                        callback.onSuccess(filteredMovies);
                    }
                } else {
                    callback.onError("Error al obtener la lista de películas");
                }
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onError("Error de red al obtener la lista de películas");
            }
        });
    }

    public interface SearchCallback {
        void onSuccess(List<Movie> moviess);

        void onError(String errorMessage);
    }

    public void getCarteleraMovies(String apiKey, final MoviesCallback callback){

        Call<MovieResponse> call = movieApi.getCarteleraMovies(apiKey ,language );
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null) {
                        List<Movie> movies = movieResponse.getResults();
                        callback.onSuccess(movies);
                    }
                } else {
                    callback.onError("Error al obtener la lista de películas");
                }
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onError("Error de red al obtener la lista de películas");
            }
        });
    }

    public interface MoviesCallback {
        void onSuccess(List<Movie> moviess);

        void onError(String errorMessage);
    }

}
