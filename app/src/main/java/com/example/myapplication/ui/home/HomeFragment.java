package com.example.myapplication.ui.home;



import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Conexion.MovieAPI;
import com.example.myapplication.Entidad.Movie;
import com.example.myapplication.Entidad.MovieResponse;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import com.example.myapplication.Adapter.MovieAdapter;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewHorizontal);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL, false));

        movieAdapter = new MovieAdapter(new ArrayList<>());
        recyclerView.setAdapter(movieAdapter);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        observeViewModel();

        return view;
    }

    private void observeViewModel() {
        homeViewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
            if (movies != null) {
                movieAdapter.updateMovies(movies);
            }
        });
    }
}