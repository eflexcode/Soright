package com.eflexsoft.soright;

import com.eflexsoft.soright.di.DaggerMainComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class App extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerMainComponent.builder().builder(this).MAIN_COMPONENT();
    }
}
