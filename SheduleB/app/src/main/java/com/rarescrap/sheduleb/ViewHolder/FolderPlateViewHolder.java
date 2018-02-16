package com.rarescrap.sheduleb.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.rarescrap.sheduleb.R;

import java.io.File;

/**
 * @author RareScrap
 */
public class FolderPlateViewHolder extends AbstractHolder {
    private TextView name;
    private TextView description;

    public FolderPlateViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        description = (TextView) itemView.findViewById(R.id.description);
    }

    @Override
    public void bind(Object item) {
        name.setText(((File) item).getName());
    }
}
