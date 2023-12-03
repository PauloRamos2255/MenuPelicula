package com.example.myapplication.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Entidad.Movie;
import com.example.myapplication.Respositorio.MovieRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movies;
    private MovieRepository movieRepository;

    public HomeViewModel() {
        movies = new MutableLiveData<>();
        movieRepository = new MovieRepository();
        fetchPopularMovies("8300bb0075ff37f5c25ab05fdeb18503"); // Reemplaza "TU_CLAVE_API" con tu clave de API real
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    private void fetchPopularMovies(String apiKey) {
        movieRepository.getPopularMovies(apiKey, new MovieRepository.OnMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> moviesList) {
                movies.setValue(moviesList);
            }

            @Override
            public void onError(String errorMessage) {
                // Maneja el error según tus necesidades (puedes mostrar un mensaje en la interfaz de usuario, etc.)
            }
        });
    }
}