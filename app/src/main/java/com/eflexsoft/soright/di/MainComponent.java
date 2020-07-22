package com.eflexsoft.soright.di;

import android.os.Build;

import com.eflexsoft.soright.App;
import com.eflexsoft.soright.di.firebase.FirebaseModel;
import com.eflexsoft.soright.di.ui.ActivityFragmentModel;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityFragmentModel.class,
        FirebaseModel.class
})
public interface MainComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder builder(App app);

        MainComponent MAIN_COMPONENT();

    }

}
