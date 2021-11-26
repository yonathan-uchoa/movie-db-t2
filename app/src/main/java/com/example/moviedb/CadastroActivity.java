package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviedb.model.DaoMaster;
import com.example.moviedb.model.DaoSession;
import com.example.moviedb.model.User;
import com.example.moviedb.model.UserDao;

import org.greenrobot.greendao.database.Database;

public class CadastroActivity extends AppCompatActivity {
    private EditText textEmail;
    private EditText textPassword;
    private EditText textPasswordConfirm;
    private EditText textApelido;
    private EditText textDescricao;
    private Button cadastrarBtn;

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        textEmail = findViewById(R.id.textEmailCadastro);
        textPassword = findViewById(R.id.textSenhaCadastro);
        textPasswordConfirm = findViewById(R.id.textSenhaConfirm);
        textApelido = findViewById(R.id.textApelido);
        textDescricao = findViewById(R.id.textDescricao);
        cadastrarBtn = findViewById(R.id.cadastrarBtn);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();

        userDao = daoSession.getUserDao();
    }

    private void cadastrar(View view){
        String password = textPassword.getText().toString();
        String username = textEmail.getText().toString();
        String nickName = textApelido.getText().toString();
        String description = textDescricao.getText().toString();

        if (password != textPasswordConfirm.getText().toString()) {
            Toast.makeText(this, "Erro ao confirmar a senha",Toast.LENGTH_LONG).show();
            return;
        }

        User user = new User();
        user.setUserName(username);
        user.setDescription(description);
        user.setPassword(password);
        user.setNickName(nickName);

        userDao.insert(user);
        Toast.makeText(this, "Novo usuario"+String.valueOf(user.getId()),Toast.LENGTH_SHORT).show();
    }
}