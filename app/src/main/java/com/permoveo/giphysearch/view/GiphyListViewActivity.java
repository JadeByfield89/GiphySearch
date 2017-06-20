package com.permoveo.giphysearch.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.permoveo.giphysearch.R;
import com.permoveo.giphysearch.adapter.GiphyListViewAdapter;
import com.permoveo.giphysearch.model.Giph;
import com.permoveo.giphysearch.presenter.GiphyListViewPresenter;
import com.permoveo.giphysearch.util.GridItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GiphyListViewActivity extends AppCompatActivity implements GiphyListView{

    @Bind(R.id.bRefresh)
    Button mRefresh;

    @Bind(R.id.rvRecycler)
    RecyclerView mRecycler;

    @Bind(R.id.pbProgress)
    ProgressBar mProgressBar;

    GiphyListViewPresenter mPresenter = new GiphyListViewPresenter(this);
    private GiphyListViewAdapter mAdapter;

    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;


    private static final int GIPHY_MAX_RESULTS = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giphy_listview);

        mPresenter.onCreate();

        ButterKnife.bind(this);
        loadTrendingGifs(GIPHY_MAX_RESULTS);

        ArrayList<Giph> items = new ArrayList();
        mAdapter = new GiphyListViewAdapter(items);

        int spanCount = 3;
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        mStaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        mRecycler.setLayoutManager(mStaggeredGridLayoutManager);
        mRecycler.setHasFixedSize(true);


        mRecycler.addItemDecoration(new GridItemDecoration(4));
        mRecycler.setAdapter(mAdapter);


        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRefreshButton();
            }
        });


    }

    @Override
    public void loadTrendingGifs(int count) {
        mPresenter.fetchResults(count);
    }

    @Override
    public void updateRefreshButton() {
        mRefresh.setText("Refreshing...");
        mRecycler.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        mPresenter.onRefreshButtonSelected();
    }

    @Override
    public void reportError(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayResults(ArrayList<Giph> results) {

        mProgressBar.setVisibility(View.INVISIBLE);
        mRecycler.setVisibility(View.VISIBLE);
        mRefresh.setText("Refresh");
        Toast.makeText(this, "Loaded " + results.size() + " Gifs!", Toast.LENGTH_SHORT).show();

        mAdapter.appendResults(results);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
