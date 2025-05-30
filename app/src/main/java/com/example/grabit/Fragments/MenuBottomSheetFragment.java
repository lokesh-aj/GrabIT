package com.example.grabit.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.grabit.Adapter.MenuAdapter;
import com.example.grabit.R;
import com.example.grabit.databinding.FragmentMenuBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.Arrays;
import java.util.List;

public class MenuBottomSheetFragment extends BottomSheetDialogFragment {

    private FragmentMenuBottomSheetBinding binding;
    private BottomSheetBehavior<View> bottomSheetBehavior;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false);
        setupBottomSheet();
        setupRecyclerView();
        binding.btnBack.setOnClickListener(v -> dismissWithAnimation());
        return binding.getRoot();
    }

    private void setupBottomSheet() {
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        if (dialog != null) {
            dialog.setOnShowListener(dialogInterface -> {
                View bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                if (bottomSheet != null) {
                    bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    bottomSheetBehavior.setPeekHeight(0);
                    bottomSheetBehavior.setHideable(true);
                    bottomSheetBehavior.setSkipCollapsed(true);
                    animateSheetOpen(bottomSheet);
                }
            });
        }
    }

    private void setupRecyclerView() {
        List<String> menuFoodName = Arrays.asList("Burger", "Sandwich", "Momo", "Pizza", "Sandwich", "Momo");
        List<String> menuItemPrice = Arrays.asList("₹50", "₹50", "₹50", "₹50", "₹50", "₹50");
        List<Integer> menuImage = Arrays.asList(
                R.drawable.menu1,
                R.drawable.menu2,
                R.drawable.menu3,
                R.drawable.menu4,
                R.drawable.menu5,
                R.drawable.menu6
        );

        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        // Now using the updated MenuAdapter constructor with Context parameter
        binding.menuRecyclerView.setAdapter(new MenuAdapter(requireContext(), menuFoodName, menuItemPrice, menuImage));
    }

    private void dismissWithAnimation() {
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        } else {
            dismiss();
        }
    }

    private void animateSheetOpen(View view) {
        TranslateAnimation slideUp = new TranslateAnimation(0, 0, view.getHeight(), 0);
        slideUp.setDuration(500);
        slideUp.setInterpolator(new AccelerateDecelerateInterpolator());
        view.startAnimation(slideUp);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
