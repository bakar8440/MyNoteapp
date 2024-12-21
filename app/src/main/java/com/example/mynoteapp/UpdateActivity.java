package com.example.mynoteapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mynoteapp.room.UserDao;
import com.example.mynoteapp.room.UserDatabase;
import com.example.mynoteapp.room.Users;

public class UpdateActivity extends AppCompatActivity {
    private EditText title, description;
    private Button updateButton;
    private Users users;
    private UserDatabase userDatabase;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.userDao();

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        updateButton = findViewById(R.id.updateButton);

        users = (Users) getIntent().getSerializableExtra("model");
        title.setText(users.getTitle());
        description.setText(users.getDescription());


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users userModel=new Users(users.getId(),title.getText().toString(),description.getText().toString());
                userDao.update(userModel);
                finish();
            }
        });

    }
}