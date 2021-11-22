package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviedb.model.DaoMaster;
import com.example.moviedb.model.User;
import com.example.moviedb.model.UserDao;
import com.example.moviedb.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class MainActivity extends AppCompatActivity {

    private UserDao userDao;

    private EditText PTPassword;
    private EditText PTUsername;
    private EditText PTNickName;
    private EditText PTDescription;

    private Button ButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();

        userDao = daoSession.getUserDao();

        PTPassword = findViewById(R.id.PTextPassword);
        PTUsername = findViewById(R.id.PTextUsername);
        PTNickName = findViewById(R.id.PTextNickName);
        PTDescription = findViewById(R.id.PTextDescription);

        ButtonAdd = findViewById(R.id.ButtonAdd);
    }



    public void addButton(View view){
        addUser();
    }

    private void addUser(){
        String password = PTPassword.getText().toString();
        String username = PTUsername.getText().toString();
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