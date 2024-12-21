package com.example.mynoteapp;

import com.example.mynoteapp.room.Users;

public interface AdapterListener {
    void OnUpdate(Users users);
    void inDelete(int id,int pos);
}
