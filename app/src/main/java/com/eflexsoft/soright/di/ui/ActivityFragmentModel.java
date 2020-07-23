package com.eflexsoft.soright.di.ui;

import com.eflexsoft.soright.LoginActivity;
import com.eflexsoft.soright.MainActivity;
import com.eflexsoft.soright.PlaceOrderActivity;
import com.eflexsoft.soright.SignUpActivity;
import com.eflexsoft.soright.SplashActivity;
import com.eflexsoft.soright.fragments.BookMeFragment;
import com.eflexsoft.soright.fragments.DiamondFragment;
import com.eflexsoft.soright.fragments.GoldFragment;
import com.eflexsoft.soright.fragments.HomeFragment;
import com.eflexsoft.soright.fragments.MessageFragment;
import com.eflexsoft.soright.fragments.NotificationFragment;
import com.eflexsoft.soright.fragments.SilverFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityFragmentModel  {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    @ContributesAndroidInjector
    abstract BookMeFragment bookMeFragment();

    @ContributesAndroidInjector
    abstract DiamondFragment diamondFragment();

    @ContributesAndroidInjector
    abstract SignUpActivity signUpActivity();

    @ContributesAndroidInjector
    abstract LoginActivity loginActivity();

    @ContributesAndroidInjector
    abstract GoldFragment goldFragment();

    @ContributesAndroidInjector
    abstract SilverFragment silverFragment();

    @ContributesAndroidInjector
    abstract PlaceOrderActivity placeOrderActivity();

    @ContributesAndroidInjector
    abstract NotificationFragment notificationFragment();

    @ContributesAndroidInjector
    abstract MessageFragment messageFragment();

}
