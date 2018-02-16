package com.rarescrap.sheduleb.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.rarescrap.sheduleb.MainActivity;
import com.rarescrap.sheduleb.R;

import java.io.File;

/**
 * @author RareScrap
 */
public class CreateFolderDialogFragment extends DialogFragment {
    public static final String TAG = "CreateFolderDialogFragment";

    public interface OnFolderCreateListener {
        void onFolderCreate(File createdFolder);
    }

    private TextInputEditText nameEditText;
    private TextInputEditText descriptionEditText;

    private OnFolderCreateListener mOnFolderCreateListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_create_folder, null);
        builder.setView(dialogView); // Добавление GUI в диалоговое окно

        // Назначение сообщения AlertDialog
        builder.setTitle(R.string.title_create_folder_dialog);

        nameEditText = (TextInputEditText) dialogView.findViewById(R.id.name_field);
        descriptionEditText = (TextInputEditText) dialogView.findViewById(R.id.description_field);

        // Добавление кнопки назначения цвета
        builder.setPositiveButton(R.string.create_folder,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        File listsFolder = MainActivity.getListsFolder(getContext());

                        File newFolder = new File(listsFolder.getAbsolutePath() +
                                File.separator + nameEditText.getText().toString());
                        newFolder.mkdir();

                        if (mOnFolderCreateListener != null) {
                            mOnFolderCreateListener.onFolderCreate(newFolder);
                        }
                    }
                }
        );

        return builder.create(); // Возвращение диалогового окна
    }

    public OnFolderCreateListener getOnFolderCreateListener() {
        return mOnFolderCreateListener;
    }

    public void setOnFolderCreateListener(OnFolderCreateListener onFolderCreateListener) {
        mOnFolderCreateListener = onFolderCreateListener;
    }
}
