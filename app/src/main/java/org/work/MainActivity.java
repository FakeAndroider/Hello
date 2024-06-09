package org.work;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import org.work.annotation.ComponentLogger;
import org.work.annotation.MyAnnotation;

import org.work.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @MyAnnotation
    private String name;
    @MyAnnotation
    private int age;

    @Override
    @ComponentLogger
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildFramework();

        log.onInit();

    }

    private void buildFramework() {
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        final View root = binding.getRoot();
        setContentView(root);
        MainViewModel viewModel =
                new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.bind(binding,this);

    }

}
