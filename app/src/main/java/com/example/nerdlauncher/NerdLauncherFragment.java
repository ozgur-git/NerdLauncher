package com.example.nerdlauncher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nerdlauncher.databinding.ActivityNerdLauncherBinding;

public class NerdLauncherFragment extends Fragment{

    public static Fragment newInstance() {

        return new NerdLauncherFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ActivityNerdLauncherBinding binding= DataBindingUtil.inflate(inflater,R.layout.activity_nerd_launcher,container,false);
        binding.nerdLauncherRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getContext()));

        return binding.getRoot();
    }
}
