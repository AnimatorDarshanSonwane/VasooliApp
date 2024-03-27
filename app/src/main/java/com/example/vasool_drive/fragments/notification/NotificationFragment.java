package com.example.vasool_drive.fragments.notification;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasool_drive.R;
import com.example.vasool_drive.adpters.CollectionViewPagerAdapter;
import com.example.vasool_drive.databinding.FragmentNotificationBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class NotificationFragment extends Fragment {
    private String[] data = {"REMINDER","NOTES"};
    private Integer[]icon ={R.drawable.collection,R.drawable.customer};
    private AppBarConfiguration mAppBarConfiguration;
    private CollectionViewPagerAdapter viewPagerAdapter;
    private FragmentNotificationBinding binding;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater,container,false);


        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mAppBarConfiguration =
                new AppBarConfiguration.Builder(R.id.nav_host_fragment_content_main,R.id.collectionFragment).build();


        viewPagerAdapter = new CollectionViewPagerAdapter(getActivity());
        binding.viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(data[position]);

            }
        }
        ).attach();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.collectionFragment);
            }
        });

    }
}