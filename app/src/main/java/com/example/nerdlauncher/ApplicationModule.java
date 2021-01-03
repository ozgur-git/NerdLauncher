package com.example.nerdlauncher;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    NerdLauncherViewModel provideViewModel(){
        return new NerdLauncherViewModel();
    }
}
