package com.example.gk3;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;



import java.util.List;

@Dao
public interface HistoryItemDao {
    @Query("SELECT * FROM history_items") // Truy vấn để lấy tất cả dữ liệu từ bảng
    List<HistoryItem> getAllHistoryItems();
    @Insert
    void insert(HistoryItem historyItem);

}
