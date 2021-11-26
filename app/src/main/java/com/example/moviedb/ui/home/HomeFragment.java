package com.example.moviedb.ui.home;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviedb.R;
import com.example.moviedb.api.JsonPlaceHolderApi;
import com.example.moviedb.api.Result;
import com.example.moviedb.api.Test;
import com.example.moviedb.databinding.FragmentHomeBinding;

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

        listSearch = binding.listSearch;
        PTSearch = binding.PTSearch;

        PTSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER){
                    Toast.makeText(root.getContext(), ""+PTSearch.getText().toString(),Toast.LENGTH_SHORT).show();
                    fetchData(PTSearch.getText().toString());

                    return true;
                }
                return false;
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {


            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void fetch(View view, String search){
        fetchData(search);
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
//                    List<Result> test = response.body().getResults();
//                    List<String> string = new ArrayList<String>();
//
//
//                    for(Result result : test){
//                        if(result.getTitle() != null) {
//                            String content = "";
//                            content+= result.getTitle();
//                            content+= "\nNota " + result.getVoteAverage();
//                            string.add(content);
//                        }
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, string);
//                    listSearch.setAdapter(adapter);

                    data.put("")
                }
            }
            @Override
            public void onFailure(Call<Test> call, Throwable t) {

            }
        });

        for(int j = 1; j < response.body().getTotalPages(); j++){
            data.put("page", String.valueOf(j));
            Call<Test> recall = jsonPlaceHolderApi.getPost(data);
            recall.enqueue(new Callback<Test>() {
                @Override
                public void onResponse(Call<Test> call, Response<Test> response) {
                    if(!response.isSuccessful()){

                        return;
                    }else{
                        List<Result> retest = response.body().getResults();
                        for(Result result : retest){
                            if(result.getTitle() != null) {
                                String content = "";
                                content+= result.getTitle();
                                content+= "\nNota " + result.getVoteAverage();
                                string.add(content);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Test> call, Throwable t) {

                }
            });
        }
    }
}