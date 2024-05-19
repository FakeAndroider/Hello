package org.work;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import org.work.boot.LoggerBoot;
import org.work.databinding.ActivityMainBinding;
import org.work.ui.ViewPagerAdapter;
import org.work.ui.ViewPagerBinder;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onAppBoot();
        onBuildFramework();
    }


    private void onAppBoot() {
        new LoggerBoot().onStart();
    }

    private void onBuildFramework() {
        // 获取根视图设置为当前contentView
        org.work.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 添加选项卡
        final TabLayout tabLayout = binding.navTab;

        final ViewPager2 viewPager = binding.viewPager;

        // 创建 fragmentStateAdapter 实例
        final FragmentStateAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // 创建 ViewPager 和 tabLayout 建立关系
        ViewPagerBinder.builder(this, tabLayout, viewPager);

    }


}