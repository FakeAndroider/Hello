package org.work.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract public class BaseFragment<VM extends ViewModel, VB extends ViewDataBinding> extends Fragment {
    protected VM viewModel;
    protected VB binding;

    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
/*
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
*
* */
        viewModel = getViewModelInstance();
        binding = getViewBindingInstance(inflater, container);

        assert binding != null;
        return binding.getRoot();

    }

    private VB getViewBindingInstance(final LayoutInflater inflater, final ViewGroup container) {

        // 获取泛型参数的实际类型
        final Type[] typeArguments = getTypeParameterList();
        final int idx = 1;

        if (typeArguments.length > 0 && typeArguments[idx] instanceof Class) {
            final Class<VB> vbClass = (Class<VB>) typeArguments[idx];
            try {

                Method inflateMethod = vbClass.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);

//                final Method method = vbClass.getMethod("inflate", LayoutInflater.class);
                return (VB) inflateMethod.invoke(null,inflater, container, false);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }

    private VM getViewModelInstance() {

        // 获取泛型参数的实际类型
        final Type[] typeArguments = getTypeParameterList();
        final int idx = 0;

        if (typeArguments.length > 0 && typeArguments[idx] instanceof Class) {
            final Class<VM> vmClass = (Class<VM>) typeArguments[idx];
            return new ViewModelProvider(this).get(vmClass);
        }

        return null;

    }

    private Type[] getTypeParameterList() {

        final Type superClass = getClass().getGenericSuperclass();

        // 确保泛型超类是ParameterizedType
        if (superClass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superClass;
            return parameterizedType.getActualTypeArguments();
        }
        return new Type[0];


    }
}