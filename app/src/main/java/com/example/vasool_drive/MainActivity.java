package com.example.vasool_drive;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.vasool_drive.adpters.ViewPagerAdapter;
import com.example.vasool_drive.databinding.ActivityMainBinding;
import com.example.vasool_drive.fragments.CollectionFragment;
import com.example.vasool_drive.fragments.CustomerFragment;
import com.example.vasool_drive.fragments.ExpenseFragment;
import com.example.vasool_drive.fragments.LineDialogFragment;
import com.example.vasool_drive.fragments.ReportFragment;
import com.example.vasool_drive.fragments.SettingFragment;
import com.example.vasool_drive.fragments.settingfragment.MySettingsFragment;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity  {

    private ActivityMainBinding binding;
    private long backpressed;
    private Toast backtoast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;

        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_content_main);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        bottomNavigationView.setVisibility(View.GONE);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.collectionFragment:
                        Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment_content_main).popBackStack();
                        Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment_content_main).navigate(R.id.collectionFragment);

                        Log.d("TAG  ", " collection Fragment" + item.getItemId());
                        break;
                    case R.id.expenseFragment:

                        Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment_content_main).popBackStack();
                        Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment_content_main).navigate(R.id.expenseFragment);

                        Log.d("TAG  ", " collection Fragment" + item.getItemId());
                        break;
                    case R.id.customerFragment:

                        Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment_content_main).popBackStack();
                      Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment_content_main).navigate(R.id.customerFragment);
                        Log.d("TAG  ", " collection Fragment" + item.getItemId());
                        break;
                    case R.id.reportFragment:

                       Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment_content_main).popBackStack();
                       Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment_content_main).navigate(R.id.reportFragment);
                        Log.d("TAG  ", " collection Fragment" + item.getItemId());
                        break;
                    case R.id.settingFragment:

                       Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment_content_main).popBackStack();
                       Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment_content_main).navigate(R.id.settingFragment);
                        Log.d("TAG  ", " collection Fragment" + item.getItemId());
                        break;
                }
                return true;
            }
        });




    }

    @Override
    public void onBackPressed() {

        if (backpressed + 3000 > System.currentTimeMillis()){
        super.onBackPressed();
        backtoast.cancel();



        return;

        }else {

            backtoast = Toast.makeText(getBaseContext(),"press back again to exit", Toast.LENGTH_SHORT);

            backtoast.show();
        }

        backpressed =System.currentTimeMillis();



    }

    private void setupNav() {
        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_content_main);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                while (navDestination.equals(true)) {
                    binding.bottomNavigationView.setVisibility(View.GONE);
                }
            }
        });
    }




}