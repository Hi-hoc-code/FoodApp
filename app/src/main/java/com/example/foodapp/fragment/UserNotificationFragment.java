package com.example.foodapp.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.adapter.NoticeAdminAdapter;
import com.example.foodapp.adapter.NotificationUser;
import com.example.foodapp.dao.NoticeDAO;
import com.example.foodapp.model.Notice;

import java.util.ArrayList;

public class UserNotificationFragment extends Fragment {
    NoticeDAO noticeDAO;
    NotificationUser notificationUser;
    ArrayList<Notice> listNotice;
    RecyclerView rcvNotice;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_notification,container,false);

        rcvNotice = view.findViewById(R.id.rcvNoticeU);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvNotice.setLayoutManager(layoutManager);

        noticeDAO = new NoticeDAO(getContext());
        listNotice=noticeDAO.getAll();
        notificationUser = new NotificationUser(listNotice, getContext());

        rcvNotice.setAdapter(notificationUser);

        return view;
    }
}
