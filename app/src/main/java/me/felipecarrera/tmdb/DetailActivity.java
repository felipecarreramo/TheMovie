package me.felipecarrera.tmdb;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.felipecarrera.tmdb.model.DAOTMDB;
import me.felipecarrera.tmdb.model.TVSerie;
import me.felipecarrera.tmdb.tools.DAOAsyncResponse;


public class DetailActivity extends ActionBarActivity implements DAOAsyncResponse{


    private ImageView posterDetail;
    private TextView titleDetail;
    private TextView seasonsDetail;
    private TextView overviewText;
    private int selectedId;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            selectedId = extras.getInt("idSerie");
            posterDetail = (ImageView) findViewById(R.id.posterDetail);
            titleDetail = (TextView) findViewById(R.id.titleDetail);
            seasonsDetail = (TextView) findViewById(R.id.seasonsText);
            overviewText = (TextView) findViewById(R.id.overviewText);
            DAOTMDB.INSTANCE.delegate = this;
            DAOTMDB.INSTANCE.fetchMoreInfoOfSerie(id);


        }
    }

    @Override
    public void onAsyncResponse(String module)
    {
        if (module.equals("fetchMoreInfoOfSerie"))
        {
            TVSerie selectedSerie = DAOTMDB.INSTANCE.findSerieById(selectedId);
            Picasso.with(this).load(selectedSerie.getPoster()).into(posterDetail);
            titleDetail.setText(selectedSerie.getName());
            seasonsDetail.setText(selectedSerie.getNumberSeasons()+" temporadas");
            overviewText.setText(selectedSerie.getOverview());
        }
    }
}
