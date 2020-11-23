package dtu.android.moroapp.views;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dtu.android.moroapp.R;
import dtu.android.moroapp.adapters.EventAdapter;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.viewModels.mainPageFragViewModel;


public class jFrontPageFragment extends Fragment {
    private View root;
    private RecyclerView mRecyclerview;
    private EventAdapter mAdapter;
    private mainPageFragViewModel xmainPageFragViewModel;

    Executor bgThread = Executors.newSingleThreadExecutor();
    Handler uithread = new Handler(Looper.getMainLooper());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_j_front_page, container, false);

        xmainPageFragViewModel = new ViewModelProvider(this).get(mainPageFragViewModel.class);

        mRecyclerview = root.findViewById(R.id.front_page_list);


        try {
            xmainPageFragViewModel.init();
        } catch (IOException e) {
            e.printStackTrace();
        }


        xmainPageFragViewModel.getEvents().observe(getViewLifecycleOwner(), new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                System.out.println(xmainPageFragViewModel.getEvents().getValue());

                System.out.println("observe test");
                mAdapter.notifyDataSetChanged();
            }
        });
        initRecyclerview();




        return root;
    }

    private void initRecyclerview() {
            RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerview.setLayoutManager(linearLayoutManager);
            mAdapter = new EventAdapter(xmainPageFragViewModel.getEvents().getValue());
            mRecyclerview.setAdapter(mAdapter);

    }
}