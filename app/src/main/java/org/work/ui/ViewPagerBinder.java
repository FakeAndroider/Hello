package org.work.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;



import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import org.work.R;

public class ViewPagerBinder {

    private static final int mTab = R.menu.tab_item;
    private static final int mTabImage = R.id.icon_image;
    private static final int mTabText = R.id.icon_text;
    // 加载 ColorStateList 资源

    static public void builder(final Context context, final TabLayout tabLayout,
                               final ViewPager2 viewPager) {


        new TabLayoutMediator(tabLayout, viewPager, (new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {

                final View icon = LayoutInflater.from(context).inflate(mTab, null);
                final ImageView image = icon.findViewById(mTabImage);
                final TextView title = icon.findViewById(mTabText);

                switch (i) {
                    case 0:
                        title.setText(R.string.title_home);
                        image.setImageResource(R.drawable.ic_home_black_24dp);
                        image.setSelected(true);
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

                @SuppressLint("ResourceType") final ColorStateList colorStateList =
                        ContextCompat.getColorStateList(context,
                                R.drawable.tab_color_selector);
                title.setTextColor(colorStateList);

                tab.setCustomView(icon);
            }
        })).attach();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View icon = tab.getCustomView();
                ImageView image = icon.findViewById(mTabImage);
                image.setSelected(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View icon = tab.getCustomView();
                if (icon != null) {
                    ImageView image = icon.findViewById(mTabImage);
                    image.setSelected(false);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }


}
