package com.example.gk3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_history);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        HistoryAdapter adapter = new HistoryAdapter(getHistoryItemsFromDatabase());
//        recyclerView.setAdapter(adapter);

    }
    private List<HistoryItem> getHistoryItemsFromDatabase() {
        List<HistoryItem> historyList = new ArrayList<>();
        AppDatabase appDatabase = AppDatabase.getInstance(this);

        // Sử dụng HistoryItemDao để truy xuất dữ liệu
        historyList.addAll(appDatabase.historyItemDao().getAllHistoryItems());

        return historyList;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_back) {
            // Xử lý sự kiện khi nút "Back" được nhấn
            // Ví dụ: Quay về trang chủ (hoặc hoạt động trước đó)
            onBackPressed();
            return true;
        } else if (id == R.id.action_delete_all) {
            // Xử lý sự kiện khi nút "Delete All" được nhấn
            // Ví dụ: Xóa toàn bộ dữ liệu
            Toast.makeText(this, "Delete All clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}