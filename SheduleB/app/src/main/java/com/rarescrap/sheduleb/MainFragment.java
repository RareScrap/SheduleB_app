package com.rarescrap.sheduleb;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rarescrap.sheduleb.adapters.MainFragmentAdapter;

import java.io.File;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    private static final String LIST_FOLDER_NAME = "todo_lists";

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

        File appDir = getActivity().getFilesDir();
        File mainDir = null;

        // Проверяем, если ли в директории приложения папка со спискми дел
        for (File file : appDir.listFiles()) { // TODO: использовать FileFilter
            if (LIST_FOLDER_NAME.equals(file.getName())) {
                mainDir = file;
                break;
            }
        }

        // Если директория со списками дел не найдена - создадим ее
        if (mainDir == null) {
            mainDir = new File(appDir.getAbsolutePath() + File.separator + LIST_FOLDER_NAME);
            mainDir.mkdir();
        }

        for (int i = 0; i < 50; i++) {
            File debugDir;
            debugDir = new File(mainDir.getAbsolutePath() + File.separator + "debug" + i);
            debugDir.mkdir();
        }

        mAdapter = new MainFragmentAdapter(mainDir.listFiles());
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
}
