package com.example.moviedb.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviedb.R;
import com.example.moviedb.model.DaoMaster;
import com.example.moviedb.model.DaoSession;
import com.example.moviedb.model.MovieDao;
import com.example.moviedb.model.Movie;

import org.greenrobot.greendao.database.Database;

public class HomeDetails extends AppCompatActivity {

    private Bundle bundle;
    private TextView TVTitle;
    private TextView TVNote;
    private TextView TVOverview;
    private TextView TVRelease;
    private Button BtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);

        bundle = getIntent().getExtras();

        TVTitle = findViewById(R.id.TVTitle);
        TVNote = findViewById(R.id.TVNote);
        TVOverview = findViewById(R.id.TVOverview);
        TVRelease = findViewById(R.id.TVRelease);

        BtnSave = findViewById(R.id.BtnSalvar);

        TVTitle.setText(bundle.getString("title"));
        TVNote.setText(String.valueOf(bundle.getDouble("vote")));
        TVOverview.setText(bundle.getString("overview"));
        TVRelease.setText(bundle.getString("release"));
    }

    public void save(View view){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();

        MovieDao movieDao = daoSession.getMovieDao();

        Movie movie = new Movie();
        movie.setReleaseDate(bundle.getString("release"));
        movie.setVoteAverage(bundle.getDouble("vote"));
        movie.setOriginalTitle(bundle.getString("title"));
        movie.setOverview(bundle.getString("overview"));


        if(movieDao.queryBuilder().where(MovieDao.Properties.OriginalTitle.eq(movie.getOriginalTitle())).list().size()== 0)
            movieDao.save(movie);
    }
}