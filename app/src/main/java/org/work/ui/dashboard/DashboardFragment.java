package org.work.ui.dashboard;

import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {

/*    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
  /*      binding = null;*/
    }
}