package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviedb.api.JsonPlaceHolderApi;
import com.example.moviedb.api.Result;
import com.example.moviedb.api.Test;
import com.example.moviedb.model.DaoMaster;
import com.example.moviedb.model.User;
import com.example.moviedb.model.UserDao;
import com.example.moviedb.model.DaoSession;

import org.greenrobot.greendao.database.Database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private UserDao userDao;

    private EditText PTPassword;
    private EditText PTEmail;
    private EditText PTNickName;
    private EditText PTDescription;

    private TextView test;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private Button ButtonAdd;
    private Button API;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();

        userDao = daoSession.getUserDao();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


    }



    public void addButton(View view){
        addUser();
    }

    public void fetch(View view){
        fetchData();
    }

    private void fetchData(){
        Map<String, String> data = new HashMap<>();
        data.put("api_key", "bb0e5cab69c4b3502ab1a75cd9c7f371");
        data.put("language", "pt-BR");


        //E aqui que deve ser alterado para pegar do campo de pesquisa. "aranha" -> TextField.getText.
        data.put("query", "aranha");

        Call<Test> call = jsonPlaceHolderApi.getPost(data);
        call.enqueue(new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call, Response<Test> response) {
                if(!response.isSuccessful()){
                    test.setText("Code: " + response.code());
                    return;
                }else{
                 List<Result> teste = response.body().getResults();

                    for(Result result : teste){
                        String content = "";
                        content+= "title" + result.getOriginalTitle();
                        content+= "\n vote average " + result.getVoteAverage();
                        content+= "x\n\n";
                        test.append(content);
                    }
                }
            }
            @Override
            public void onFailure(Call<Test> call, Throwable t) {
                test.setText("ERROR!!"+t.getMessage());

            }
        });
    }

    private void addUser(){
        String password = PTPassword.getText().toString();
        String username = PTEmail.getText().toString();
        String nickName = PTNickName.getText().toString();
        String description = PTDescription.getText().toString();

        User user = new User();
        user.setUserName(username);
        user.setDescription(description);
        user.setPassword(password);
        user.setNickName(nickName);

        userDao.insert(user);
        Toast.makeText(this, "Novo usuario"+String.valueOf(user.getId()),Toast.LENGTH_SHORT).show();
    }
}