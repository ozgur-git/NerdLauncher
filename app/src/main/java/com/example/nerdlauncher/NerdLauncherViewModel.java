package com.example.nerdlauncher;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class NerdLauncherViewModel {

    CharSequence activityName;
    ResolveInfo resolveInfo;
    PackageManager mPackageManager;

    public NerdLauncherViewModel(PackageManager packageManager) {
        mPackageManager = packageManager;
    }

    public CharSequence getActivityName() {
        return activityName;
    }

    public void setResolveInfo(ResolveInfo resolveInfo) {
        this.resolveInfo = resolveInfo;
        setActivityName(resolveInfo.loadLabel(mPackageManager));
    }

    public void setActivityName(CharSequence activityName) {
        this.activityName = activityName;
    }


    public void onClick(){

        System.out.println(getActivityName().toString());

//        Intent intent=new Intent(this,
    }
}
