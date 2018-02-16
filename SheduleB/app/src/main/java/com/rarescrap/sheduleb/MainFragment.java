package com.rarescrap.sheduleb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rarescrap.sheduleb.adapters.MainFragmentAdapter;
import com.rarescrap.sheduleb.dialogs.CreateFolderDialogFragment;

import java.io.File;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    public static final String TAG = "MainFragment";

    RecyclerView mRecyclerView;
    MainFragmentAdapter mAdapter;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MainFragmentAdapter(MainActivity.getListsFolder(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) inflatedView.findViewById(R.id.folder_list);

        // Возможно это не тут должно быть
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2); // TOOD: Magic number
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return inflatedView;
    }

    public void showAddFolderDialog() {
        CreateFolderDialogFragment dialog = new CreateFolderDialogFragment();
        dialog.setOnFolderCreateListener(new CreateFolderDialogFragment.OnFolderCreateListener() {
            @Override
            public void onFolderCreate(File createdFolder) {
                mAdapter.add(createdFolder);
                mAdapter.notifyDataSetChanged();
            }
        });
        dialog.show(getFragmentManager(), CreateFolderDialogFragment.TAG);
    }


}
