package org.work.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.orhanobut.logger.Logger;

import org.work.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    final private String loggerPre = "HomeFragment";
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(loggerPre + "onCreate");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.d(loggerPre + "onViewCreated");
    }

    @Override
    public void onStart() {
        Logger.d(loggerPre + "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Logger.d(loggerPre + "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Logger.d(loggerPre + "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Logger.d(loggerPre + "onStop");
        super.onStop();
    }


    @Override
    public void onDetach() {
        Logger.d(loggerPre + "onDetach");
        super.onDetach();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Logger.d(loggerPre + "onCreateView");
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

        Logger.d(loggerPre + "onDestroy");
    }
}