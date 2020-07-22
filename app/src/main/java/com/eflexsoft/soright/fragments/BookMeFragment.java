package com.eflexsoft.soright.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.eflexsoft.soright.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class BookMeFragment extends DaggerFragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    List<SlideModel> slideModels = new ArrayList<>();

    @Inject
    FirebaseFirestore firebaseFirestore;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        slideModels.add(new SlideModel(R.drawable.endless1, "Everything we make is amazing", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.endless2, "Everything we make is amazing", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.endless3, "Everything we make is amazing", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.endless4, "Everything we make is amazing", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.endless6, "Everything we make is amazing", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.endless7, "Everything we make is amazing", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.endless8, "Everything we make is amazing", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.endless9, "Everything we make is amazing", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.endless10, "Everything we make is amazing", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.endless11, "Everything we make is amazing", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.endless12, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless13, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless14, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless15, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless16, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless17, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless18, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless19, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless20, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless21, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless23, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless24, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless25, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless26, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.endless27, "Everything we make is amazing", ScaleTypes.CENTER_INSIDE));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_me, container, false);

        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.paggerV);

        List<String> fragmentName = new ArrayList<>();
        fragmentName.add("₦850,000");
        fragmentName.add("₦650,000");
        fragmentName.add("₦250,000");

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new DiamondFragment());
        fragmentList.add(new GoldFragment());
        fragmentList.add(new SilverFragment());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragmentList, fragmentName);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_INSIDE);

        return view;
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

        List<String> pageName = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList, List<String> pageName) {
            super(fm);
            this.fragmentList = fragmentList;
            this.pageName = pageName;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void setFragmentList(List<Fragment> fragmentList, List<String> pageName) {
            this.fragmentList = fragmentList;
            this.pageName = pageName;
            notifyDataSetChanged();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return pageName.get(position);
        }
    }

}
