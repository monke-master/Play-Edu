package ru.mirea.playedu.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.FragmentCommunityBinding;
import ru.mirea.playedu.databinding.FragmentProfileBinding;
import ru.mirea.playedu.viewmodel.CommunityViewModel;

public class CommunityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCommunityBinding binding = FragmentCommunityBinding.inflate(getLayoutInflater());
        CommunityViewModel viewModel = new ViewModelProvider(this).get(CommunityViewModel.class);
        binding.setViewModel(viewModel);

        binding.setKillsHdr(getString(R.string.kills_count));
        binding.setPlayEduCompletedHdr(getString(R.string.play_edu_compl_count));
        binding.setTasksCompletedHdr(getString(R.string.tasks_compl_count));
        return binding.getRoot();
    }
}