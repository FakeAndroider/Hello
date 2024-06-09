package org.work.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.work.databinding.FragmentHomeBinding;
import org.work.log;


public class HomeFragment extends Fragment {

    final private String loggerPre = "HomeFragment";
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.d(loggerPre + "onCreate");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        log.d(loggerPre + "onViewCreated");
    }

    @Override
    public void onStart() {
        log.d(loggerPre + "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        log.d(loggerPre + "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        log.d(loggerPre + "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        log.d(loggerPre + "onStop");
        super.onStop();
    }


    @Override
    public void onDetach() {
        log.d(loggerPre + "onDetach");
        super.onDetach();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        log.d(loggerPre + "onCreateView");
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        final View root = binding.getRoot();
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return root;

    }

    @Override
    public void onDestroyView() {
        binding = null;

        log.d(loggerPre + "onDestroy");
        super.onDestroyView();

    }
}