package com.permoveo.giphysearch.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.permoveo.giphysearch.application.GiphySearchApplication;
import com.permoveo.giphysearch.model.Giph;
import com.permoveo.giphysearch.util.ConstantsUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by byfieldj on 6/19/17.
 *
 * This class initiates the API request to fetch a specified number of trending GIFs, stores them in a list and delivers
 * that list back to our Presenter layer that made the request.
 */

public class GiphySearchRequest {


    private static final String TAG = "GifSearchRequest";
    private static final String KEY_DATA = "data";
    private static final String KEY_IMAGES = "images";
    private ArrayList<Giph> mResults = new ArrayList();


    /*
    This callback will be responsible for delivering the results(or an error) back to the component that made the call
     */
    public interface GifSearchRequestListener {

        void onGifSearchCompleted(ArrayList<Giph> results);

        void onGifSearchError(VolleyError error);
    }


    public void searchGiphy(final int count, final GifSearchRequestListener listener) {

        // Make sure that we're clearing any previous results before adding new results to our list.
        if(!mResults.isEmpty()){
            mResults.clear();
        }

        String url = String.format(ConstantsUtil.GIPHY_TRENDING_ENDPOINT, count);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray array = response.getJSONArray(KEY_DATA);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        if (object.has(KEY_IMAGES)) {

                            JSONObject imageObject = object.getJSONObject(KEY_IMAGES);

                            Giph giph = new Giph();
                            giph.parseJSON(imageObject);
                            mResults.add(giph);
                        }
                    }
                    listener.onGifSearchCompleted(mResults);
                    Log.d("GifSearchRequest", "Giphy results size -> " + mResults.size());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onGifSearchError(error);

            }
        });

        GiphySearchApplication.getInstance().addToRequestQueue(request, url);
    }
}
