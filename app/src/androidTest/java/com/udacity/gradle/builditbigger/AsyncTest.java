package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.v4.util.Pair;
import android.test.AndroidTestCase;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.concurrent.ExecutionException;

/**
 *  Unit Connected Tests
 */
public class AsyncTest extends AndroidTestCase {

    public void testVerifyAsyncTaskResponse() throws ExecutionException, InterruptedException {

        EndpointsAsyncTask task = new EndpointsAsyncTask();

        task.execute(new Pair<Context,ProgressBar>(null,null));
        String joke = task.get();

        assertNotNull(joke);
        Log.v("DEBUG","joke not null");
        assertFalse(joke.isEmpty());
        Log.v("DEBUG","joke is '" + joke + "'");
    }
}
