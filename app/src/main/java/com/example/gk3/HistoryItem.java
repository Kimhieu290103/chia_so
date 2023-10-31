package com.example.gk3;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//public class HistoryItem {
//    private String selectedAction;
//    private String input;
//    private String result;
//
//    public HistoryItem(String selectedAction, String input, String result) {
//        this.selectedAction = selectedAction;
//        this.input = input;
//        this.result = result;
//    }
//
//    public String getAction() {
//        return selectedAction;
//    }
//
//    public String getInput() {
//        return input;
//    }
//
//    public String getResult() {
//        return result;
//    }
//}
@Entity(tableName = "history_items")
public class HistoryItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String selectedAction;
    private String input;
    private String result;

    public HistoryItem(String selectedAction, String input, String result) {
        this.selectedAction = selectedAction;
        this.input = input;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSelectedAction() {
        return selectedAction;
    }

    public void setSelectedAction(String selectedAction) {
        this.selectedAction = selectedAction;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
