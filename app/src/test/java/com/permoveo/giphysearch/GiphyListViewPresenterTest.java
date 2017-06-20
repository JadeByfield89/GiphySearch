package com.permoveo.giphysearch;

import com.permoveo.giphysearch.presenter.GiphyListViewPresenter;
import com.permoveo.giphysearch.view.GiphyListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by byfieldj on 6/20/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class GiphyListViewPresenterTest {


    private GiphyListViewPresenter mPresenter;

    private GiphyListView mListView;


    @Before
    public void setup() throws Exception{

        mListView = mock(GiphyListView.class);
        mPresenter = new GiphyListViewPresenter(mListView);



    }

    @Test
    public void searchRequestShouldNotBeNullAfterOnCreate() throws Exception{

        mPresenter.onCreate();

        assertNotNull(mPresenter.getSearchRequest());
    }

    @Test
    public void viewCallsUpdateRefreshButtonAfterPresenterIsNotified() throws Exception{

        mPresenter.onCreate();
        mListView.updateRefreshButton();

        verify(mListView, atLeastOnce()).updateRefreshButton();


    }


}
