package com.example.nerdlauncher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class NerdLauncherFragment extends Fragment{

    RecyclerView mRecyclerView;

    public static Fragment newInstance() {

        return new NerdLauncherFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_nerd_launcher,container,false);



        mRecyclerView=view.findViewById(R.id.crime_recycler_view);

//        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getContext()));



        return view;
    }
}
