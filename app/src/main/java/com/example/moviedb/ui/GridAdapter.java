package com.example.moviedb.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.moviedb.api.MoviesSearch;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GridAdapter extends BaseAdapter {
    private Context ctx;
    private MoviesSearch movies;

    public GridAdapter(Context ctx, MoviesSearch movies) {
        this.ctx = ctx;
        this.movies = movies;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return movies.getTotalResults();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return movies.getResults().get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return movies.getResults().get(position).getId();
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView poster = new ImageButton(ctx);
        InputStream input;
        Bitmap img = null;
        String imgUrl = "https://image.tmdb.org/t/p/original/"
                + movies.getResults().get(position).getPosterPath();

        try {
            input = new URL(imgUrl).openStream();
            img = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        poster.setImageBitmap(img);
        poster.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
        poster.setScaleType(ImageView.ScaleType.CENTER_CROP);
        poster.setPadding(5,5,5,5);
        return poster;
    }
}
