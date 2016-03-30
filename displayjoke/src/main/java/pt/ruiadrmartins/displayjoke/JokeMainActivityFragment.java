package pt.ruiadrmartins.displayjoke;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment to display joke
 */
public class JokeMainActivityFragment extends Fragment {

    public JokeMainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke_main, container, false);

        Bundle args = getArguments();
        if(args!=null) {
            String joke = args.getString(JokeMainActivity.JOKE_LABEL);

            TextView text = (TextView) rootView.findViewById(R.id.joke_text);
            text.setText(joke);
        } else {
            TextView text = (TextView) rootView.findViewById(R.id.joke_text);
            text.setText(getString(R.string.no_joke));
        }

        return rootView;
    }
}
