package com.example.fureverhome.ui.community;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fureverhome.R;
import com.example.fureverhome.model.Discussion;
import com.example.fureverhome.model.DiscussionUtils;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class DiscussionGenerateActivity extends AppCompatActivity {


    private static final int REQUEST_IMAGE_PICK = 101;

    private ImageView imagePreview;
    private TextInputEditText inputTitle, inputDescription;
    private Spinner spinnerTaskType;
    private Uri selectedImageUri;
    private Button btnSubmit;

    private final String[] taskTypes = {"Volunteer", "Workshop", "Networking", "Seminar", "Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.fragment_discussion_generate, null);
        setContentView(view);


        // 初始化控件
        inputTitle = view.findViewById(R.id.inputTitle);
        inputDescription = view.findViewById(R.id.inputDescription);
        spinnerTaskType = view.findViewById(R.id.spinnerTaskType);
//        btnPickImage = view.findViewById(R.id.btnPickImage);
        btnSubmit = view.findViewById(R.id.btnSubmitDiscussion);


        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish()); // Go back to previous screen

        // --- 初始化 Spinner 选项 ---
        String[] taskTypes = {"Volunteer", "Workshop", "Networking", "Seminar", "Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, taskTypes);
        spinnerTaskType.setAdapter(adapter);


        // --- 提交按钮逻辑 ---
        btnSubmit.setOnClickListener(v -> {
            String title = inputTitle.getText().toString().trim();
            String description = inputDescription.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            String selectedType = spinnerTaskType.getSelectedItem().toString();

            // 模拟创建成功
            Toast.makeText(this, "Discussion created:\n" + title, Toast.LENGTH_LONG).show();

            // 生成唯一 ID 和模拟发布日期
            String id = UUID.randomUUID().toString();
            String postedDate = new java.text.SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());

// 创建新的 Discussion 对象（你需要根据 Discussion 构造函数顺序来）
            Discussion newDiscuss = new Discussion(
                    R.drawable.ic_pet,
                    title,// 默认图标
                    postedDate,
                    id,
                    description,
                    selectedType
            );

// 存入工具类
            DiscussionUtils.addDiscussion(newDiscuss);

            Toast.makeText(this, "Discussion created and saved successfully!", Toast.LENGTH_LONG).show();
            finish(); // 可选：跳回 Discussion 列表页面

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
