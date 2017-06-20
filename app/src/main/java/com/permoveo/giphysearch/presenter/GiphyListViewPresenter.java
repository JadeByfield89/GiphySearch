package com.permoveo.giphysearch.presenter;

import com.android.volley.VolleyError;
import com.permoveo.giphysearch.model.Giph;
import com.permoveo.giphysearch.network.GiphySearchRequest;
import com.permoveo.giphysearch.view.GiphyListView;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by byfieldj on 6/19/17.
 * <p>
 * This class will be responsible for a couple things
 * <p>
 * 1) Providing our GiphyListViewActivity(View) with data from our model
 * 2) Instructing the View how to respond to user input events, in this case tapping the "Refresh" button.
 */

public class GiphyListViewPresenter implements Presenter {

    private GiphyListView mView;
    private GiphySearchRequest mSearchRequest;


    public GiphyListViewPresenter(GiphyListView view) {
        this.mView = view;
    }

    public void fetchResults(int count) {
        mSearchRequest.searchGiphy(count, new GiphySearchRequest.GifSearchRequestListener() {
            @Override
            public void onGifSearchCompleted(ArrayList<Giph> results) {

                mView.displayResults(results);
            }


            @Override
            public void onGifSearchError(VolleyError error) {

                mView.reportError(error.getMessage());
            }
        });

    }


    public void onRefreshButtonSelected() {

        int nextCount = ThreadLocalRandom.current().nextInt(10, 50 + 1);
        fetchResults(nextCount);

    }

    public GiphySearchRequest getSearchRequest() {
        return mSearchRequest;
    }


    @Override
    public void onCreate() {
        mSearchRequest = new GiphySearchRequest();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }
}
