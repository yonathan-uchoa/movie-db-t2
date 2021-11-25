package com.example.moviedb.ui.filmes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviedb.R;
import com.example.moviedb.api.JsonPlaceHolderApi;
import com.example.moviedb.api.MovieData;
import com.example.moviedb.api.MoviesSearch;
import com.example.moviedb.databinding.FragmentFilmesBinding;
import com.example.moviedb.ui.GridAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmesFragment extends Fragment {

    private FilmesViewModel filmesViewModel;
    private FragmentFilmesBinding binding;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private EditText textSearch;
    private Button buttonSearch;
    private GridView grid_filmes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        filmesViewModel =
                new ViewModelProvider(this).get(FilmesViewModel.class);

        binding = FragmentFilmesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textSearch = getActivity().findViewById(R.id.filmeSearchTxt);
        buttonSearch = getActivity().findViewById(R.id.filmeSearchBtn);
        grid_filmes = getActivity().findViewById(R.id.grid_filmes);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        updateSearch("");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void updateSearch(View view){
        updateSearch(textSearch.getText().toString());
    }

    public void updateSearch(String query){
        Map<String, String> data = new HashMap<>();
        data.put("api_key", "bb0e5cab69c4b3502ab1a75cd9c7f371");
        data.put("language", "pt-BR");
        data.put("query", query);

        Call<MoviesSearch> call = jsonPlaceHolderApi.getPost(data);

        call.enqueue(new Callback<MoviesSearch>() {
            @Override
            public void onResponse(Call<MoviesSearch> call, Response<MoviesSearch> response) {
                GridAdapter adapter = new GridAdapter(getContext(), response.body());
                grid_filmes.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<MoviesSearch> call, Throwable t) {
                return;
            }
        });
    }
}