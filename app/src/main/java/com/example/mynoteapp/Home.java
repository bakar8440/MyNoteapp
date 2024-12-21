package com.example.mynoteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynoteapp.room.UserDao;
import com.example.mynoteapp.room.UserDatabase;
import com.example.mynoteapp.room.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class Home extends AppCompatActivity implements AdapterListener {
    EditText title, description;
    Button addItemButton;
    Button logoutButton;
    Button image;
    private FirebaseAuth mAuth;
    private UserDatabase userDatabase;
    private UserDao userDao;

    private UserAdapter userAdapter;
    RecyclerView myrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        image=findViewById(R.id.image);
        title =findViewById(R.id.title);
        description =findViewById(R.id.description);
        addItemButton = findViewById(R.id.addItemButton);
        logoutButton = findViewById(R.id.logoutButton);
        myrecycler=findViewById(R.id.userrecycler);



        userAdapter=new UserAdapter(this,this);
        myrecycler.setAdapter(userAdapter);
        myrecycler.setLayoutManager(new LinearLayoutManager(this));



        mAuth = FirebaseAuth.getInstance();

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.userDao();

        addItemButton.setOnClickListener(v -> {
            String titleText = title.getText().toString();
            String descriptionText = description.getText().toString();

            Users user = new Users(0,titleText, descriptionText);
            userAdapter.addUsers(user);
            userDao.insert(user);
            title.setText("");
            description.setText("");
            Toast.makeText(Home.this, "Item added successfully", Toast.LENGTH_SHORT).show();

        });

        FirebaseUser user = mAuth.getCurrentUser();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ImageActivity.class);
                startActivity(intent);
                finish();
            }
        });


        logoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(Home.this, MainActivity.class));
            finish();
        });
    }

    private void fetchData(){
        userAdapter.clearData();
        List<Users> usersList=userDao.getAllUsers();
        for (int i=0;i<usersList.size();i++){
            Users users=usersList.get(i);
            userAdapter.addUsers(users);
        }
    }

    @Override
    public void OnUpdate(Users users) {
        Intent intent = new Intent(Home.this, UpdateActivity.class);
        intent.putExtra("model", users);
        intent.putExtra("title", users.getTitle());
        intent.putExtra("description", users.getDescription());
        startActivity(intent);
    }

    @Override
    public void inDelete(int id, int pos) {
        userDao.delete(id);
        userAdapter.removeUsers(pos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }



}
