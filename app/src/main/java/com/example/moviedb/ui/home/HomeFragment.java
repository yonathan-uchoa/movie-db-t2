package com.example.moviedb.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviedb.api.JsonPlaceHolderApi;
import com.example.moviedb.api.Result;
import com.example.moviedb.api.Test;
import com.example.moviedb.databinding.FragmentHomeBinding;
import com.example.moviedb.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ListView listSearch;
    private EditText PTSearch;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private List<Movie> movies;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Pegar os dados da API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //Inicializando itens
        listSearch = binding.listSearch;
        PTSearch = binding.PTSearch;

        //Criando auxiliares
        movies = new ArrayList<Movie>();

        PTSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER){
                    fetchData(PTSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });

        listSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("title", movies.get(i).getOriginalTitle());
                bundle.putString("overview", movies.get(i).getOverview());
                bundle.putString("release", movies.get(i).getReleaseDate());
                bundle.putDouble("vote", movies.get(i).getVoteAverage());

                Intent it = new Intent(root.getContext(), HomeDetails.class);
                it.putExtras(bundle);
                startActivity(it);
            }
        });




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void fetchData(String search){
        Map<String, String> data = new HashMap<>();

        data.put("api_key", "bb0e5cab69c4b3502ab1a75cd9c7f371");
        data.put("language", "pt-BR");


        //E aqui que deve ser alterado para pegar do campo de pesquisa. "aranha" -> TextField.getText.
        data.put("query", search);

        Call<Test> call = jsonPlaceHolderApi.getPost(data);
        call.enqueue(new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call, Response<Test> response) {
                if(!response.isSuccessful()){

                    return;
                }else{
                    List<Result> test = response.body().getResults();
                    List<String> string = new ArrayList<String>();


                    for(Result result : test){
                        if(result.getTitle() != null) {
                            Movie movie = new Movie();
                            movie.setOriginalTitle(result.getTitle());
                            movie.setOverview(result.getOverview());
                            movie.setVoteAverage(result.getVoteAverage());
                            movie.setReleaseDate(result.getReleaseDate());

                            String content = "";
                            content+= result.getTitle();
                            content+= "\nNota " + result.getVoteAverage();
                            string.add(content);

                            movies.add(movie);
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, string);
                    listSearch.setAdapter(adapter);

/*
    Nao esta pegando todas as paginas.
    Tentei com a solucao abaixo, mas sem sucesso.
 */

//                    List<String> string = new ArrayList<String>();
//
//                    for(int i = 1; i < response.body().getTotalPages() +1; i++ ){
//
//                        Map<String, String> redata = new HashMap<>();
//                        redata.put("api_key", "bb0e5cab69c4b3502ab1a75cd9c7f371");
//                        redata.put("language", "pt-BR");
//                        redata.put("query", search);
//                        redata.put("page", String.valueOf(i));
//                        Call<Test> recall = jsonPlaceHolderApi.getPost(redata);
//                        recall.enqueue(new Callback<Test>() {
//                            @Override
//                            public void onResponse(Call<Test> call, Response<Test> reresponse) {
//                                if(!reresponse.isSuccessful()){
//                                    Toast.makeText(binding.getRoot().getContext(), "Failure "+reresponse.message(),Toast.LENGTH_SHORT).show();
//                                    return;
//                                }else{
//                                    Toast.makeText(binding.getRoot().getContext(), "sucess "+search,Toast.LENGTH_SHORT).show();
//                                    List<Result> retest = reresponse.body().getResults();
//                                    for(Result result : retest){
//                                        if(result.getTitle() != null) {
//                                            String content = "";
//                                            content+= result.getTitle();
//                                            content+= "\nNota " + result.getVoteAverage();
//                                            string.add(content);
//                                        }
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<Test> call, Throwable t) {
//                                Toast.makeText(binding.getRoot().getContext(), "Failure "+t.getMessage(),Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, string);
//                    listSearch.setAdapter(adapter);


                }
            }
            @Override
            public void onFailure(Call<Test> call, Throwable t) {

            }
        });


    }
}