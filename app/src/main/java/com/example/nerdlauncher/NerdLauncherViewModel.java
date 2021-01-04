package com.example.nerdlauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

public class NerdLauncherViewModel extends BaseObservable{

    CharSequence activityName;
    ResolveInfo resolveInfo;
    PackageManager mPackageManager;
    Context context;
    Drawable mDrawable;

    public NerdLauncherViewModel(PackageManager packageManager, Context context) {
        System.out.println("constructor is called");
        mPackageManager = packageManager;
        this.context=context;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(ImageView imageView) {
        imageView.setImageDrawable(mDrawable);
    }

    @Bindable
    public CharSequence getActivityName() {
        return resolveInfo.loadLabel(mPackageManager);
    }

    public void setResolveInfo(ResolveInfo resolveInfo) {
        this.resolveInfo = resolveInfo;
        mDrawable=resolveInfo.loadIcon(mPackageManager);
        notifyPropertyChanged(BR.activityName);
//        setActivityName(resolveInfo.loadLabel(mPackageManager));
    }

    public void setActivityName(CharSequence activityName) {
        this.activityName = activityName;
    }

    public void onClick(){
        System.out.println(getActivityName().toString());
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.setClassName(resolveInfo.activityInfo.packageName,resolveInfo.activityInfo.name);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
