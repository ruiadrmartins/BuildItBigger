package pt.ruiadrmartins.displayjoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class JokeMainActivity extends AppCompatActivity {

    public static final String JOKE_LABEL = "jokeLabel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_main);

        if(savedInstanceState==null) {
            Bundle bundle = new Bundle();
            Intent intent = getIntent();

            String joke = intent.getStringExtra(JOKE_LABEL);
            bundle.putString(JOKE_LABEL, joke);

            JokeMainActivityFragment fragment = new JokeMainActivityFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, fragment)
                    .commit();
        }
    }

}
