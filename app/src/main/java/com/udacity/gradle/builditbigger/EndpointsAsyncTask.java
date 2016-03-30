package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import pt.ruiadrmartins.displayjoke.JokeMainActivity;
import pt.ruimartins.gcebackend.myApi.MyApi;

/**
 * Task to communicate with GCE Server
 * Based on the tutorial from
 * https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
 *
 * GCE Server address and location is configured on the gradle.properties file
 */
class EndpointsAsyncTask extends AsyncTask<Pair<Context,ProgressBar>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private ProgressBar spinner;

    @Override
    protected String doInBackground(Pair<Context,ProgressBar>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder;

            builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(BuildConfig.GCE_ADDRESS);

            // if GCEServer is local
            if(BuildConfig.localAddr) {
                builder.setGoogleClientRequestInitializer(
                    new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                });
            }

            myApiService = builder.build();
        }

        context = params[0].first;
        spinner = params[0].second;

        try {
            return myApiService.getRandomJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        if(context!=null) {
            Intent intent = new Intent(context, JokeMainActivity.class);
            intent.putExtra(JokeMainActivity.JOKE_LABEL, joke);
            context.startActivity(intent);
            spinner.setVisibility(View.GONE);
        }
    }
}