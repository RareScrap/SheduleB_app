package com.rarescrap.sheduleb;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String LIST_FOLDER_NAME = "todo_lists";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.TAG);
                if (mainFragment != null && mainFragment.isVisible()) {
                    mainFragment.showAddFolderDialog();
                }
            }
        });

        MainFragment mainFragment = MainFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_main, mainFragment, MainFragment.TAG); // Не использую add, т.к. это ведет к дублированию вьюъ при изменении ориентации экрана
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Возвращает папку, где хранятся списки дел
     * @param context Контекст для доступа к файловой системе
     * @return Папка со списками дел
     */
    public static File getListsFolder(Context context) {
        // Проверяем, если ли в директории приложения папка со спискми дел
        for (File folder : context.getFilesDir().listFiles()) { // TODO: использовать FileFilter
            if (LIST_FOLDER_NAME.equals(folder.getName())) {
                return folder;
            }
        }

        // Если директория со списками дел не найдена - создадим ее
        File listsFolder = new File(context.getFilesDir().getAbsolutePath() + File.separator + LIST_FOLDER_NAME);
        listsFolder.mkdir();
        return listsFolder;
    }
}