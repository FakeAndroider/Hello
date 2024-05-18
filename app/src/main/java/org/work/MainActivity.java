package org.work;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.work.boot.LoggerBoot;
import org.work.databinding.ActivityMainBinding;
import org.work.ui.ViewPagerAdapter;

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


        new TabLayoutMediator(tabLayout, viewPager, ((tab, i) -> {
            final View icon = LayoutInflater.from(this).inflate(R.menu.tab_item, null);
            final ImageView image = icon.findViewById(R.id.icon_image);
            final TextView title = icon.findViewById(R.id.icon_text);

            switch (i) {
                case 0:
                    title.setText(R.string.title_home);
                    image.setImageResource(R.drawable.ic_home_black_24dp);
                    break;
                case 1:
                    title.setText(R.string.title_dashboard);
                    image.setImageResource(R.drawable.ic_dashboard_black_24dp);
                    break;
                case 2:
                    title.setText(R.string.title_notifications);
                    image.setImageResource(R.drawable.ic_notifications_black_24dp);
                    break;
            }
//            image.setColorFilter(ContextCompat.getColorStateList(MainActivity.this, R.drawable.tab_color_selector));
            title.setTextColor(ContextCompat.getColorStateList(MainActivity.this, R.drawable.tab_color_selector));
            tab.setCustomView(icon);
        })).attach();
    }


}