package com.example.Extractor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import android.content.Loader;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Words>> {

    ArrayList<Words> wordsArrayList = new ArrayList<>();
    WordAdapter wordAdapter;
    TextView emptyState;
    ProgressBar progressBar;
    ListView wordsListView;

    private String URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        emptyState = findViewById(R.id.empty_view);

        URL = getIntent().getStringExtra("url");

        //network and connectivity objects
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        //check that the device is connected to the internet
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(2, null, this);
        } else {//in case the device is not connected to the internet
            progressBar.setVisibility(View.GONE);
            emptyState.setText(R.string.no_internet_connection);
        }

        wordsListView = findViewById(R.id.wordsList);
        wordsListView.setEmptyView(emptyState);

        wordAdapter = new WordAdapter(this, wordsArrayList);

        // Set the adapter on the ListView
        // so the list can be populated in the user interface
        wordsListView.setAdapter(wordAdapter);
    }

    @Override
    public Loader<List<Words>> onCreateLoader(int id, Bundle args) {
        return new WordsLoader(this, URL);
    }

    //this method calls WordsLoader.loadInBackground() method which calls fetchWords() method
    @Override
    public void onLoadFinished(Loader<List<Words>> loader, List<Words> data) {
        wordAdapter.clear();
        if (data == null) {
            emptyState.setText(R.string.no_earthquake_found);
            progressBar.setVisibility(View.GONE);
            return;
        }
        wordAdapter.addAll(data);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<Words>> loader) {
        wordAdapter.clear();
    }
}
