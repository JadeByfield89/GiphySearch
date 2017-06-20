package com.permoveo.giphysearch.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by byfieldj on 6/19/17.
 * <p>
 * Simple model class to represent a single GIF that will be contained in the JSON returned from our API call
 */

public class Giph {

    private static final String TAG = "Gif";
    private static final String KEY_URL = "url";

    // Since there is no requirement to select a specific image from the list
    // of available options, let's just use the first one which is called "fixed_height"
    private static final String KEY_FIXED_HEIGHT = "fixed_height";

    private String mUrl;


    public Giph() {

    }

    public void parseJSON(JSONObject json) {
        Log.d(TAG, "JSON -> " + json.toString());

        if (json == null) {
            Log.d(TAG, "No JSON found at the requested location!");
        } else {

            try {
                JSONObject imageObject = json.optJSONObject(KEY_FIXED_HEIGHT);

                if (imageObject != null && imageObject.has(KEY_URL)) {
                    setUrl(imageObject.getString(KEY_URL));
                    Log.d(TAG, "Url -> " + imageObject.getString(KEY_URL));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    private void setUrl(final String url) {
        this.mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }
}
