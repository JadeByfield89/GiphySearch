package com.permoveo.giphysearch;


import android.widget.Button;

import com.permoveo.giphysearch.presenter.GiphyListViewPresenter;
import com.permoveo.giphysearch.view.GiphyListViewActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by byfieldj on 6/20/17.
 */


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class GiphyListViewActivityTest {

    @Mock
    GiphyListViewPresenter mPresenter;


    private GiphyListViewActivity mActivity;


    @Before
    public void setup() throws Exception {

        mActivity = Robolectric.buildActivity(GiphyListViewActivity.class).create().resume().get();

    }


    @Test
    public void activityShouldNotBeNull(){

        assertNotNull(mActivity);
    }

    @Test
    public void refreshButtonShouldChangeTextWhenSelected(){

        Button refresh = (Button) mActivity.findViewById(R.id.bRefresh);
        refresh.performClick();
        assertTrue(refresh.getText().equals("Refreshing..."));
    }

}
