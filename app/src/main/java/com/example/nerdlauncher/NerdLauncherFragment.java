package com.example.nerdlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
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
import com.example.nerdlauncher.databinding.ListItemBinding;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class NerdLauncherFragment extends Fragment{

    @Inject
    NerdLauncherViewModel mNerdLauncherViewModel;

    List<ResolveInfo> activities;
    PackageManager packageManager;
    ApplicationComponent applicationComponent;


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
        packageManager=getActivity().getPackageManager();
        activities=packageManager.queryIntentActivities(startIntent,0);
        Collections.sort(activities, (ResolveInfo o1,ResolveInfo o2)-> String.CASE_INSENSITIVE_ORDER.compare(o1.loadLabel(packageManager).toString(),o2.loadLabel(packageManager).toString())
        );
        System.out.println("found "+activities.size());
//        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getContext()));
//        setupAdapter();

        applicationComponent=DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(packageManager,getActivity())).build();
        applicationComponent.inject(this);

        binding.nerdLauncherRecyclerView.setAdapter(new NerdLauncherAdapter(activities,packageManager));

        return binding.getRoot();
    }

    private void setupAdapter() {
        Intent startIntent=new Intent(Intent.ACTION_MAIN);
        startIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager=getActivity().getPackageManager();
        activities=packageManager.queryIntentActivities(startIntent,0);
        Collections.sort(activities, (ResolveInfo o1,ResolveInfo o2)-> {
                return String.CASE_INSENSITIVE_ORDER.compare(o1.loadLabel(packageManager).toString(),o2.loadLabel(packageManager).toString());
            }
        );
        System.out.println("found "+activities.size());
    }

    class NerdLauncherHolder extends RecyclerView.ViewHolder{

        ListItemBinding binding;

        @Inject
        NerdLauncherViewModel nerdLauncherViewModel;

        public NerdLauncherHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
            applicationComponent.inject(this);
            binding.setViewModel(nerdLauncherViewModel);;
//            binding.setViewModel(new NerdLauncherViewModel(packageManager));;
        }

        public void setItemView(ResolveInfo resolveInfo){
            binding.getViewModel().setResolveInfo(resolveInfo);
            binding.getViewModel().setDrawable(binding.imageView);
            binding.executePendingBindings();
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
            holder.setItemView(mResolveInfoList.get(position));
        }

        @Override
        public int getItemCount() {
            return mResolveInfoList.size();
        }
    }


}
