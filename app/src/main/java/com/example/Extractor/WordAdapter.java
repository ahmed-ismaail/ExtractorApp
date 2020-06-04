package com.example.Extractor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Words> {

    public WordAdapter(Activity context, ArrayList<Words> Words) {
        super(context, 0, Words);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Words currentWord = getItem(position);

        assert currentWord != null;
        ((TextView) listItemView.findViewById(R.id.word_text_view)).setText((currentWord).getWord());
        ((TextView) listItemView.findViewById(R.id.number_text_view)).setText(String.valueOf((currentWord.getWordCounter())));

        return listItemView;
    }
}
