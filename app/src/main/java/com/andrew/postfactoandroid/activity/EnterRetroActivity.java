package com.andrew.postfactoandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andrew.postfactoandroid.R;
import com.andrew.postfactoandroid.model.Retro;
import com.andrew.postfactoandroid.model.RetroItem;

import java.util.ArrayList;
import java.util.List;

public class EnterRetroActivity extends AppCompatActivity {

    private EditText inputRetroId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, R.layout.activity_enter_retro, null);
        setContentView(view);
        inputRetroId = (EditText) view.findViewById(R.id.input_enter_retro);
        Button buttonEnterRetro = (Button) view.findViewById(R.id.button_enter_retro);

        buttonEnterRetro.setOnClickListener(v -> {
            ShowRetroActivity.start(EnterRetroActivity.this, getFakeRetro());
            Toast.makeText(EnterRetroActivity.this, "Retro Id:" + inputRetroId.getText().toString(), Toast.LENGTH_SHORT).show();
        });
    }

    private Retro getFakeRetro() {
        Retro retro = new Retro();
        retro.id = "1";
        retro.name = "My first Retro!";
        List<RetroItem> items = new ArrayList<>();

        RetroItem item = new RetroItem();
        item.id = "1";
        item.description = "This is something happy!";
        item.category = Retro.Category.HAPPY;

        items.add(item);

        item = new RetroItem();
        item.id = "2";
        item.description = "This is something meh!";
        item.category = Retro.Category.MEH;

        items.add(item);

        item = new RetroItem();
        item.id = "3";
        item.description = "This is something sad!";
        item.category = Retro.Category.SAD;

        items.add(item);
        retro.items = items;
        return retro;
    }
}
