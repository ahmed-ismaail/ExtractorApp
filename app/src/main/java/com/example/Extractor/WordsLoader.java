package com.example.Extractor;

import android.content.Context;
import androidx.annotation.NonNull;
import android.content.AsyncTaskLoader;
import java.util.List;

public class WordsLoader extends AsyncTaskLoader<List<Words>> {

    private String mUrl;

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    public WordsLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<Words> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        return QueryUtils.fetchWords(mUrl);
    }
}
