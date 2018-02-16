package com.rarescrap.sheduleb.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rarescrap.sheduleb.R;
import com.rarescrap.sheduleb.ViewHolder.AbstractHolder;
import com.rarescrap.sheduleb.ViewHolder.FolderPlateViewHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author RareScrap
 */
public class MainFragmentAdapter extends RecyclerView.Adapter<AbstractHolder> {
    private List<File> listFolders;

    public MainFragmentAdapter(File mainFolder) {
        this.listFolders = new ArrayList<>();
        listFolders.addAll(Arrays.asList(mainFolder.listFiles()));
    }

    @Override
    public AbstractHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Заполнение макета item'а списка
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_main, parent, false);

        // Создание ViewHolder для текущего элемента
        return (new FolderPlateViewHolder(view));
    }

    @Override
    public void onBindViewHolder(AbstractHolder holder, int position) {
        holder.bind(listFolders.get(position));
    }

    @Override
    public int getItemCount() {
        return listFolders.size();
    }

    /**
     * Добавляет папку к данным адаптера
     * @param folder Папка которую следует добавить
     **/
    public void add(File folder) {
        listFolders.add(folder);
    }
}
