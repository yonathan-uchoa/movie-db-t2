package com.example.moviedb.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviedb.R;
import com.example.moviedb.databinding.FragmentGalleryBinding;
import com.example.moviedb.model.DaoMaster;
import com.example.moviedb.model.DaoSession;
import com.example.moviedb.model.Movie;
import com.example.moviedb.model.MovieDao;
import com.example.moviedb.model.MovieUser;
import com.example.moviedb.model.MovieUserDao;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    private MovieUserDao movieUserDao;
    private MovieDao movieDao;
    private Bundle bundle;
    private ListView listMovies;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Conexao com banco de dados e sessoes.
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(root.getContext(), "notes-db");
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();

        movieDao = daoSession.getMovieDao();
        movieUserDao = daoSession.getMovieUserDao();

        listMovies = binding.listMovies;


//        //Pegando o Bundle;
//        bundle = getArguments();
//
//        List<MovieUser> movieUsers = movieUserDao.loadAll();
//        List<String> string = new ArrayList<String>();
//
//        for (MovieUser movieUser:
//             movieUsers) {
//            if(movieUser.getUserId() == bundle.getLong("userId")){
//                Movie movie = movieDao.load(movieUser.getMovieId());
//                String content = movie.getOriginalTitle();
//                content+= movieUser.getWatched();
//                content+= movieUser.getNote();
//
//
//            }
//
//        }

        List<String> string = new ArrayList<String>();
        List<Movie> movies = movieDao.loadAll();
        for (Movie movie:
             movies) {
            String content ="";
            content+= movie.getOriginalTitle();
            content+= "\n"+movie.getVoteAverage();
            string.add(content);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, string);
        listMovies.setAdapter(adapter);



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}