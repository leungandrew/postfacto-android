package com.andrew.postfactoandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrew.postfactoandroid.R;
import com.andrew.postfactoandroid.model.Retro;
import com.andrew.postfactoandroid.model.RetroItem;

import java.util.ArrayList;
import java.util.List;

public class RetroColumnFragment extends Fragment {

    private static final String RETRO_COLUMN_CATEGORY_KEY = "RetroColumnCategoryKey";
    private static final String RETRO_COLUMN_RETRO_ITEMS_KEY = "RetroColumnRetroItemsKey";
    private Retro.Category category;
    private List<RetroItem> retroItems;
    private TextInputEditText inputRetroColumn;
    private RecyclerView list;
    private RetroColumnAdapter adapter;

    public static RetroColumnFragment newInstance(Retro.Category category, ArrayList<RetroItem> retroItems) {
        RetroColumnFragment fragment = new RetroColumnFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(RETRO_COLUMN_CATEGORY_KEY, category);
        bundle.putSerializable(RETRO_COLUMN_RETRO_ITEMS_KEY, retroItems);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_retro_column, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        this.category = (Retro.Category) bundle.getSerializable(RETRO_COLUMN_CATEGORY_KEY);
        this.retroItems = (ArrayList<RetroItem>) bundle.getSerializable(RETRO_COLUMN_RETRO_ITEMS_KEY);
        this.list = (RecyclerView) view.findViewById(R.id.list);
        adapter = new RetroColumnAdapter(this.retroItems);
        this.list.setAdapter(adapter);
        this.list.setLayoutManager(new LinearLayoutManager(view.getContext()));
        inputRetroColumn = (TextInputEditText) view.findViewById(R.id.input_retro_column);
        inputRetroColumn.setHint(getHint());

        inputRetroColumn.setOnKeyListener((v, keyCode, event) -> {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                if (!inputRetroColumn.getText().toString().isEmpty()) {
                    RetroItem item = new RetroItem();
                    item.description = inputRetroColumn.getText().toString();
                    this.retroItems.add(item);
                    inputRetroColumn.setText("");
                    adapter.notifyDataSetChanged();
                    return true;
                }
            }
            return false;
        });
    }

    private String getHint() {
        switch (category) {
            case HAPPY:
                return getString(R.string.retro_column_hint_happy);
            case MEH:
                return getString(R.string.retro_column_hint_meh);
            case SAD:
                return getString(R.string.retro_column_hint_sad);
        }
        return "";
    }

    private class RetroColumnAdapter extends RecyclerView.Adapter {

        private final List<RetroItem> items;

        RetroColumnAdapter(List<RetroItem> items) {
            this.items = items;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.view_retro_column_item, null);
            return new RetroColumnItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            RetroColumnItemViewHolder viewHolder = (RetroColumnItemViewHolder) holder;
            viewHolder.description.setText(items.get(position).description);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    private class RetroColumnItemViewHolder extends RecyclerView.ViewHolder {

        private TextView description;

        RetroColumnItemViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.retro_item_description);
        }
    }
}
