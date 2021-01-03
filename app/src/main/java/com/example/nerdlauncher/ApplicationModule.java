package com.example.nerdlauncher;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import dagger.Module;
import dagger.Provides;

import java.util.List;

@Module
public class ApplicationModule {

    PackageManager mPackageManager;

    public ApplicationModule(PackageManager packageManager) {
        mPackageManager = packageManager;
    }

    @Provides
    NerdLauncherViewModel provideViewModel(){
        return new NerdLauncherViewModel(mPackageManager);
    }
}
