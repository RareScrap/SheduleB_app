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
import java.util.Collections;
import java.util.List;

/**
 * @author RareScrap
 */
public class MainFragmentAdapter extends RecyclerView.Adapter<AbstractHolder> {
    private List listFolders;

    public MainFragmentAdapter(File[] listFolders) {
        this.listFolders = new ArrayList();
        Collections.addAll(this.listFolders, listFolders); // TODO: Сделать бы это в одну строку
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

}
