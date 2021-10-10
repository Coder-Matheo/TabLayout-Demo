package rob.myappcompany.tablayout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainFragment extends Fragment {


    //Init variable
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //Assing variable
        textView = view.findViewById(R.id.text_view);

        //Get title
        String sTitle = getArguments().getString("title");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(MainActivity.Tag, v.getTransitionName());
            }
        });

        textView.setText(sTitle);

        //Ret view
        return view;
    }
}