package org.work;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import org.work.databinding.ActivityMainBinding;
import org.work.ui.ViewPagerAdapter;
import org.work.ui.ViewPagerBinder;

public class MainViewModel extends ViewModel {
    public void bind(@NonNull final ActivityMainBinding binding, final FragmentActivity activity) {
        // 添加选项卡
        final TabLayout tabLayout = binding.navTab;

        final ViewPager2 viewPager = binding.viewPager;

        // 创建 fragmentStateAdapter 实例
        final FragmentStateAdapter adapter = new ViewPagerAdapter(activity);
        viewPager.setAdapter(adapter);

        // 创建 ViewPager 和 tabLayout 建立关系
        ViewPagerBinder.builder(activity, tabLayout, viewPager);


    }
}
