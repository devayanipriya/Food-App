package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class UserLoginTab extends AppCompatActivity {
    private TabLayout layout;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_tab);

        layout = findViewById(R.id.utabLayout);
        pager = findViewById(R.id.uviewPager);

        layout.setupWithViewPager(pager);

        LoginAdapter loginAdapter = new LoginAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        loginAdapter.getFragment(new UserLoginFragment(), "LOGIN");
        loginAdapter.getFragment(new RegisterFragment(), "REGISTER");

        pager.setAdapter(loginAdapter);
    }
}