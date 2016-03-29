package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import pt.ruiadrmartins.displayjoke.JokeMainActivity;
import pt.ruimartins.gcebackend.myApi.MyApi;

/**
 * Created by ruimartins on 17-03-2016.
 */
class EndpointsAsyncTask extends AsyncTask<Pair<Context,ProgressBar>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private ProgressBar spinner;

    @Override
    protected String doInBackground(Pair<Context,ProgressBar>... params) {
        if(myApiService == null) {  // Only do this once
            /*MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });*/

            // end options for devappserver
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(BuildConfig.GCE_ADDRESS);
            myApiService = builder.build();
        }

        context = params[0].first;
        spinner = params[0].second;

        try {
            //return myApiService.sayHi(name).execute().getData();
            return myApiService.getRandomJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        if(context!=null) {
            Intent intent = new Intent(context, JokeMainActivity.class);
            intent.putExtra(JokeMainActivity.JOKE_LABEL, joke);
            context.startActivity(intent);
            spinner.setVisibility(View.GONE);
        }
    }
}