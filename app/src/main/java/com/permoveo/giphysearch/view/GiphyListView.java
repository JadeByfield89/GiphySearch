package com.permoveo.giphysearch.view;

import com.permoveo.giphysearch.model.Giph;

import java.util.ArrayList;

/**
 * Created by byfieldj on 6/19/17.
 *
 * This is the interface that will act as the main communication layer between our Presenter and our View
 * This way each can be easily tested in isolation because they are not tightly coupled together.
 */

public interface GiphyListView {

    void loadTrendingGifs(int count);
    void reportError(String message);
    void displayResults(ArrayList<Giph> results);
    void updateRefreshButton();

}
