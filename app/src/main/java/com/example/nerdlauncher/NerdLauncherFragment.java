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

import java.util.Collections;
import java.util.List;

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
        setupAdapter();
        return binding.getRoot();
    }

    private void setupAdapter() {
        Intent startIntent=new Intent(Intent.ACTION_MAIN);
        startIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager=getActivity().getPackageManager();
        List<ResolveInfo> activities=packageManager.queryIntentActivities(startIntent,0);
        Collections.sort(activities, (ResolveInfo o1,ResolveInfo o2)-> {
//                PackageManager pm=getActivity().getPackageManager();
                return String.CASE_INSENSITIVE_ORDER.compare(o1.loadLabel(packageManager).toString(),o2.loadLabel(packageManager).toString());
            }
        );
        System.out.println("found "+activities.size());
    }

    class NerdLauncherHolder extends RecyclerView.ViewHolder{

        TextView mTextView;

        public NerdLauncherHolder(@NonNull View itemView) {
            super(itemView);
            mTextView=itemView.findViewById(R.id.list_item);
        }

        void setTextView(String stringInput){
            mTextView.setText(stringInput);
        }
    }

    class NerdLauncherAdapter extends RecyclerView.Adapter{

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}
