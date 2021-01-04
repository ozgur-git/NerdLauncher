package com.example.nerdlauncher;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import dagger.Module;
import dagger.Provides;

import java.util.List;

@Module
public class ApplicationModule {

    PackageManager mPackageManager;
    Context mContext;

    public ApplicationModule(PackageManager packageManager, Context context) {
        mPackageManager = packageManager;
        mContext=context;
    }

    @Provides
    NerdLauncherViewModel provideViewModel(){
        return new NerdLauncherViewModel(mPackageManager,mContext);
    }
}
