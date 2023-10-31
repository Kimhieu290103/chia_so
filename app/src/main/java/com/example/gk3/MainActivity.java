package com.example.gk3;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText inputEditText;
    private Spinner spinner;
    private Button submitButton;
    private Button history;
    private TextView resultTextView;
    private List<HistoryItem> historyList = new ArrayList<>();
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEditText = findViewById(R.id.editTextText);
        spinner = findViewById(R.id.spinner);
        submitButton = findViewById(R.id.button5);
        resultTextView = findViewById(R.id.textView7);
        history=findViewById(R.id.button2);

        appDatabase = AppDatabase.getInstance(this);


        // Định nghĩa mảng "actions" trực tiếp trong mã Java
        String[] actions = {"count-upper-lower", "perfect-number"};

        // Tạo ArrayAdapter cho Spinner và thiết lập dữ liệu cho nó
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, actions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Thiết lập sự kiện khi người dùng nhấn nút "Submit"
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ EditText và Spinner
                String selectedAction = (String) spinner.getSelectedItem();
                String input = inputEditText.getText().toString();
                String result="";
                if (input.isEmpty()) {
                    // Nếu EditText rỗng, hiển thị thông báo Toast
                    Toast.makeText(MainActivity.this, "Vui lòng nhập dữ liệu trước khi nhấn Submit", Toast.LENGTH_SHORT).show();
                    return; // Thoát khỏi sự kiện
                }
                // Xử lý dựa trên tùy chọn được chọn từ Spinner
                if (selectedAction.equals("count-upper-lower")) {
                    // Xử lý đếm số lượng chữ cái viết hoa và viết thường
                    int upperCaseCount = countUpperCase(input);
                    int lowerCaseCount = countLowerCase(input);

                    // Hiển thị kết quả

                    resultTextView.setText("UPPER CASE: " + upperCaseCount + "\nLOWER CASE: " + lowerCaseCount);


                } else if (selectedAction.equals("perfect-number")) {
//                    // Xử lý đếm số hoàn hảo
//                    int perfectNumberCount = countPerfectNumbersInString(input);
//
//                    // Hiển thị kết quả
//                    resultTextView.setText("PERFECT NUMBERS: " + perfectNumberCount);
                    // Xử lý đếm số hoàn hảo
                    String perfectNumbers = findPerfectNumbersInString(input);

                    // Hiển thị danh sách số hoàn hảo
                    resultTextView.setText("PERFECT NUMBERS: " + perfectNumbers);
                }
//                saveToDatabase(selectedAction, input, result);

            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, history.class);
                startActivity(intent);
            }
        });
    }
    private void saveToDatabase(String selectedAction, String input, String result) {
        HistoryItem historyItem = new HistoryItem(selectedAction, input, result);
        appDatabase.historyItemDao().insert(historyItem);
    }

    private int countUpperCase(String input) {
        // Hàm đếm số lượng chữ cái viết hoa trong chuỗi
        int count = 0;
        for (char c : input.toCharArray()) {
            if (Character.isUpperCase(c)) {
                count++;
            }
        }
        return count;
    }

    private int countLowerCase(String input) {
        // Hàm đếm số lượng chữ cái viết thường trong chuỗi
        int count = 0;
        for (char c : input.toCharArray()) {
            if (Character.isLowerCase(c)) {
                count++;
            }
        }
        return count;
    }


    public int countPerfectNumbersInString(String input) {
        int count = 0;

        // Tách chuỗi thành các số bằng dấu phẩy
        String[] numbers = input.split(",");

        for (String numStr : numbers) {
            try {
                int number = Integer.parseInt(numStr.trim()); // Loại bỏ khoảng trắng và chuyển sang số nguyên

                if (isPerfectNumber(number)) {
                    count++;
                }
            } catch (NumberFormatException e) {
                // Xử lý khi có số không hợp lệ trong chuỗi
            }
        }

        return count;
    }

    public boolean isPerfectNumber(int number) {
        if (number <= 1) {
            return false;
        }

        int sum = 1;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                sum += i;
                if (i != number / i) {
                    sum += number / i;
                }
            }
        }

        return sum == number;
    }
    public String findPerfectNumbersInString(String input) {
        List<Integer> perfectNumbersList = new ArrayList<>();
        String[] numbers = input.split(",");

        for (String numStr : numbers) {
            try {
                int number = Integer.parseInt(numStr.trim());
                if (isPerfectNumber(number)) {
                    perfectNumbersList.add(number);
                }
            } catch (NumberFormatException e) {
                // Xử lý khi có số không hợp lệ trong chuỗi
            }
        }

        if (perfectNumbersList.isEmpty()) {
            return "Không có số hoàn hảo trong chuỗi";
        } else {
            return TextUtils.join(", ", perfectNumbersList);
        }
    }

}
