/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package pt.ruimartins.gcebackend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.gradle.jokes.Jokes;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "gcebackend.ruimartins.pt",
    ownerName = "gcebackend.ruimartins.pt",
    packagePath=""
  )
)
public class MyEndpoint {

    /**
     * Get random joke
     * @return
     */
    @ApiMethod(name = "getRandomJoke")
    public MyBean getRandomJoke() {
        String joke = Jokes.getRandomJoke();

        MyBean response = new MyBean();
        response.setData(joke);

        return response;
    }

    /**
     * Get Specific joke
     * @param index
     * @return
     */
    @ApiMethod(name = "getJoke")
    public MyBean getJoke(@Named("index") int index) {
        String joke = Jokes.getJoke(index);

        MyBean response = new MyBean();
        response.setData(joke);

        return response;
    }

}
