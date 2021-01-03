package com.example.nerdlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nerdlauncher.databinding.ActivityNerdLauncherBinding;
import com.example.nerdlauncher.databinding.ListItemBinding;

import java.util.Collections;
import java.util.List;

public class NerdLauncherFragment extends Fragment{
    List<ResolveInfo> activities;

    public static Fragment newInstance() {
        return new NerdLauncherFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ActivityNerdLauncherBinding binding= DataBindingUtil.inflate(inflater,R.layout.activity_nerd_launcher,container,false);
        binding.nerdLauncherRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Intent startIntent=new Intent(Intent.ACTION_MAIN);
        startIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager=getActivity().getPackageManager();
        activities=packageManager.queryIntentActivities(startIntent,0);
        Collections.sort(activities, (ResolveInfo o1,ResolveInfo o2)-> String.CASE_INSENSITIVE_ORDER.compare(o1.loadLabel(packageManager).toString(),o2.loadLabel(packageManager).toString())
        );
        System.out.println("found "+activities.size());
//        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getContext()));
//        setupAdapter();
        binding.nerdLauncherRecyclerView.setAdapter(new NerdLauncherAdapter(activities,packageManager));
        return binding.getRoot();
    }

    private void setupAdapter() {
        Intent startIntent=new Intent(Intent.ACTION_MAIN);
        startIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager=getActivity().getPackageManager();
        activities=packageManager.queryIntentActivities(startIntent,0);
        Collections.sort(activities, (ResolveInfo o1,ResolveInfo o2)-> {
//                PackageManager pm=getActivity().getPackageManager();
                return String.CASE_INSENSITIVE_ORDER.compare(o1.loadLabel(packageManager).toString(),o2.loadLabel(packageManager).toString());
            }
        );
        System.out.println("found "+activities.size());
    }

    class NerdLauncherHolder extends RecyclerView.ViewHolder{

        ListItemBinding binding;

        public NerdLauncherHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
            binding.setViewModel(new NerdLauncherViewModel());;

        }

        public  void setTextView(String stringInput){
            binding.getViewModel().setActivityName(stringInput);
        }
    }

    class NerdLauncherAdapter extends RecyclerView.Adapter<NerdLauncherHolder>{

        List<ResolveInfo> mResolveInfoList;
        PackageManager mPackageManager;

        public NerdLauncherAdapter(List<ResolveInfo> resolveInfoList,PackageManager packageManager) {
            mResolveInfoList = resolveInfoList;
            mPackageManager=packageManager;
        }

        @NonNull
        @Override
        public NerdLauncherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemBinding binding=DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.list_item,parent,false);
            return new NerdLauncherHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull NerdLauncherHolder holder, int position) {
            holder.setTextView(mResolveInfoList.get(position).loadLabel(mPackageManager).toString());
        }

        @Override
        public int getItemCount() {
            return mResolveInfoList.size();
        }
    }


}
