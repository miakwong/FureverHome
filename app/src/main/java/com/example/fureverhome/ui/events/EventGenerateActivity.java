package com.example.fureverhome.ui.events;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.fureverhome.R;
import com.example.fureverhome.model.Event;
import com.google.android.material.textfield.TextInputEditText;
import java.io.IOException;
import java.util.*;
import com.example.fureverhome.model.EventUtils;

public class EventGenerateActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 101;

    private ImageView imagePreview;
    private TextInputEditText inputStartDate, inputTime, inputDuration;
    private TextInputEditText inputTitle, inputLocation, inputOrganizer, inputDescription;
    private Spinner spinnerTaskType;
    private Uri selectedImageUri;
    private Button btnSubmit;

    private final String[] taskTypes = {"Volunteer", "Workshop", "Networking", "Seminar", "Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.fragment_event_generate, null);
        setContentView(view);


        // 初始化控件
        inputStartDate = view.findViewById(R.id.inputStartDate);
        inputTime = view.findViewById(R.id.inputTime);
        inputDuration = view.findViewById(R.id.inputDuration);
        inputTitle = view.findViewById(R.id.inputTitle);
        inputLocation = view.findViewById(R.id.inputLocation);
        inputOrganizer = view.findViewById(R.id.inputOrganizer);
        inputDescription = view.findViewById(R.id.inputDescription);
        spinnerTaskType = view.findViewById(R.id.spinnerTaskType);
//        btnPickImage = view.findViewById(R.id.btnPickImage);
        btnSubmit = view.findViewById(R.id.btnSubmitEvent);


        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish()); // Go back to previous screen

        // --- 初始化 Spinner 选项 ---
        String[] taskTypes = {"Volunteer", "Workshop", "Networking", "Seminar", "Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, taskTypes);
        spinnerTaskType.setAdapter(adapter);

        // 日期选择器
        inputStartDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(
                    this,
                    (view1, year, month, dayOfMonth) -> inputStartDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth),
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            dialog.show();
        });

        // 时间拨轮
        inputTime.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog dialog = new TimePickerDialog(
                    this,
                    (view12, hourOfDay, minute) -> inputTime.setText(String.format("%02d:%02d", hourOfDay, minute)),
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
            );
            dialog.show();
        });
        // --- 时长选择（用 TimePicker 模拟） ---
        inputDuration.setOnClickListener(v -> {
            TimePickerDialog dialog = new TimePickerDialog(
                    this,
                    (view13, hour, minute) -> inputDuration.setText(hour + "h " + minute + "m"),
                    1, 0, true);
            dialog.setTitle("Select Duration");
            dialog.show();
        });

        // --- 提交按钮逻辑 ---
        btnSubmit.setOnClickListener(v -> {
            String title = inputTitle.getText().toString().trim();
            String location = inputLocation.getText().toString().trim();
            String organizer = inputOrganizer.getText().toString().trim();
            String description = inputDescription.getText().toString().trim();

            if (title.isEmpty() || location.isEmpty() || organizer.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            String selectedType = spinnerTaskType.getSelectedItem().toString();
            String startDate = inputStartDate.getText().toString();
            String time = inputTime.getText().toString();
            String duration = inputDuration.getText().toString();

            // 模拟创建成功
            Toast.makeText(this, "Event created:\n" + title + " at " + location, Toast.LENGTH_LONG).show();

            // 生成唯一 ID 和模拟发布日期
            String id = UUID.randomUUID().toString();
            String postedDate = new java.text.SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());

// 创建新的 Event 对象（你需要根据 Event 构造函数顺序来）
            Event newEvent = new Event(
                    R.drawable.ic_pet, // 默认图标
                    id,
                    startDate,
                    time,
                    duration,
                    title,
                    location,
                    organizer,
                    selectedType,
                    description,
                    postedDate
            );

// 存入工具类
            EventUtils.addEvent(newEvent);

            Toast.makeText(this, "Event created and saved successfully!", Toast.LENGTH_LONG).show();
            finish(); // 可选：跳回 Event 列表页面

        });
    }

    // --- 图片选择返回处理 ---
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                imagePreview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}