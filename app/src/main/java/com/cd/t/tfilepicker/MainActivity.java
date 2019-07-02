package com.cd.t.tfilepicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ess.filepicker.FilePicker;
import com.ess.filepicker.model.EssFile;
import com.ess.filepicker.util.Const;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int FILES_REQUEST_CODE = 10001;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv);
        textView.setMovementMethod(new ScrollingMovementMethod());
        FilePicker.from(this)
                .chooseForBrowser()
                .setMaxCount(9)
                .setFileTypes("doc", "docx", "pdf", "ppt", "pptx", "xlsx", "xls")
                .requestCode(FILES_REQUEST_CODE)
                .start();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == FILES_REQUEST_CODE)
            if (data != null && null != data.getParcelableArrayListExtra(Const.EXTRA_RESULT_SELECTION)) {
                try {
                    ArrayList<EssFile> essFileList = data.getParcelableArrayListExtra(Const.EXTRA_RESULT_SELECTION);
                    for (EssFile file : essFileList) {
                        Log.e("essFileList", file.toString());
                        textView.setText(file.toString()+"\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
