package com.rarescrap.sheduleb.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author RareScrap
 */
public abstract class AbstractHolder extends RecyclerView.ViewHolder {
    public AbstractHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(Object item);
}
