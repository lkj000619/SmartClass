package kr.hs.gimpo.smartclass.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.hs.gimpo.smartclass.R;

public class EventFragment extends Fragment {

    public EventFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.card_frag_event, container, false);
    }

    public void setText(String data) {

    }
}