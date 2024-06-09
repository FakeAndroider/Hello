package org.work.ui.home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import org.work.annotation.DataBing;
import org.work.base.BaseFragment;
import org.work.databinding.FragmentHomeBinding;
import org.work.log;

@DataBing
public class HomeFragment extends BaseFragment<HomeViewModel,FragmentHomeBinding> {

    final private String loggerPre = "HomeFragment";


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


    /*    public View onCreateView(@NonNull LayoutInflater inflater,
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

    }*/

    @Override
    public void onDestroyView() {

        log.d(loggerPre + "onDestroy");
        super.onDestroyView();

    }

    @Override
    protected void onBuildFinish(HomeViewModel viewModel, FragmentHomeBinding viewDataBing) {
        final TextView textView = dataBinding.textHome;
        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
    }
}