package com.waiway.clientapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFragment extends Fragment {


    public SeriesFragment() {
        // Required empty public constructor
    }

    public static RecyclerView rc_all_series;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series, container, false);
        rc_all_series = view.findViewById(R.id.ic_all_series);

        SeriesRecyclerAdapter.activity = getActivity();
        FirebaseConnect firebaseConnect = new FirebaseConnect(getContext());
        firebaseConnect.getAllSeries();
        return view;
    }

}
