package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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
    private EditText textEmail;
    private EditText textPassword;
    private Button loginBtn;
    private Button cadastroBtn;
    private Button semLoginBtn;

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEmail = findViewById(R.id.textEmailLogin);
        textPassword = findViewById(R.id.textSenhaLogin);
        loginBtn = findViewById(R.id.loginBtn);
        cadastroBtn = findViewById(R.id.cadastroBtn);
        semLoginBtn = findViewById(R.id.semLoginBtn);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();

        userDao = daoSession.getUserDao();
    }



    public void login(View view){
        List<User> queryResult = userDao.queryRaw(
                "userName=? and password=?", textEmail.getText().toString());

        if (queryResult.isEmpty()) {
            Toast.makeText(this, "Usuário ou senha incorretos",Toast.LENGTH_LONG).show();
            return;
        }
        // TODO
    }

    public void cadastro(View view){
        startActivity(new Intent(MainActivity.this, CadastroActivity.class));
    }

    public void semLogin(View view){
        // TODO
        Intent it = new Intent(this, MainActivity2.class);
        startActivity(it);
    }


}