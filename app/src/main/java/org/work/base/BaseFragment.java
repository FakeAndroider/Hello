package org.work.base;


import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import org.work.log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

abstract public class BaseFragment<VM extends ViewModel, VB extends ViewDataBinding> extends Fragment {
    protected VM viewModel;
    protected VB dataBinding;

    protected abstract void onBuildFinish(final VM viewModel, final VB viewDataBing);

    @Override
    public void onDestroy() {
        dataBinding = null;
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewModel = getViewModelInstance();
        dataBinding = getViewBindingInstance(inflater, container);

        onBuildFinish(viewModel, dataBinding);
        assert dataBinding != null;
        return dataBinding.getRoot();

    }

    private VB getViewBindingInstance(final LayoutInflater inflater, final ViewGroup container) {

     /*   // 获取泛型参数的实际类型
        final Type[] typeArguments = getTypeParameterList();
        final int idx = 1;

        if (typeArguments.length > 0 && typeArguments[idx] instanceof Class) {
            final Class<VB> vbClass = (Class<VB>) typeArguments[idx];
            try {

                Method inflateMethod = vbClass.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);

                return (VB) inflateMethod.invoke(null, inflater, container, false);

            } catch (Exception e) {
                log.e(e.toString());
                return null;
            }

        }
        return null;*/
        try {

            final String currentPackageName = Objects.requireNonNull(this.getClass().getPackage()).getName();
            final String className = currentPackageName + "." + this.getClass().getSimpleName() + "ViewModelInit";

            final Class<?> generatedClass = Class.forName(className);
            final Constructor<?> constructor = generatedClass.getDeclaredConstructor();
            final Object generatedClassInstance = constructor.newInstance();

            final Method method = generatedClass.getDeclaredMethod("provideViewBinding", LayoutInflater.class, ViewGroup.class);
            return (VB) method.invoke(generatedClassInstance, inflater, container);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

    }

    private VM getViewModelInstance() {

        try {
            final String currentPackageName = Objects.requireNonNull(this.getClass().getPackage()).getName();
            final String className = currentPackageName + "." + this.getClass().getSimpleName() + "ViewModelInit";

            final Class<?> generatedClass = Class.forName(className);
            final Constructor<?> constructor = generatedClass.getDeclaredConstructor();
            final Object generatedClassInstance = constructor.newInstance();

            // 获取方法对象
            final Method method = generatedClass.getDeclaredMethod("provideViewModel", ViewModelStoreOwner.class);

            // 调用方法并返回结果
            return (VM) method.invoke(generatedClassInstance, this);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

      /*  // 获取泛型参数的实际类型
        final Type[] typeArguments = getTypeParameterList();
        final int idx = 0;

        if (typeArguments.length > 0 && typeArguments[idx] instanceof Class) {
            final Class<VM> vmClass = (Class<VM>) typeArguments[idx];
            return new ViewModelProvider(this).get(vmClass);
        }

        return null;*/

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