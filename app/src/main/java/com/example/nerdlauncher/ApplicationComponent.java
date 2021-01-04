package com.example.nerdlauncher;

import dagger.Component;

@Component(modules=ApplicationModule.class)
public interface ApplicationComponent {

    void inject (NerdLauncherFragment nerdLauncherFragment);
    void inject (NerdLauncherFragment.NerdLauncherHolder nerdLauncherHolder);

}
