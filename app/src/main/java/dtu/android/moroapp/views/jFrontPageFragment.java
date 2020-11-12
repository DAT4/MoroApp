package dtu.android.moroapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dtu.android.moroapp.R;
import dtu.android.moroapp.adapters.EventAdapter;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.viewModels.mainPageFragViewModel;


public class jFrontPageFragment extends Fragment {
    private View root;
    private RecyclerView mRecyclerview;
    private EventAdapter mAdapter;
    private mainPageFragViewModel xmainPageFragViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_j_front_page, container, false);

        xmainPageFragViewModel = new ViewModelProvider(this).get(mainPageFragViewModel.class);

        xmainPageFragViewModel.init();

        xmainPageFragViewModel.getEvents().observe(getViewLifecycleOwner(), new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                mAdapter.notifyDataSetChanged();
            }
        });

        initRecyclerview();

        return root;
    }

    private void initRecyclerview() {
        mAdapter = new EventAdapter(xmainPageFragViewModel.getEvents().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(linearLayoutManager);
        mRecyclerview.setAdapter(mAdapter);
    }
}