package com.example.foodapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.adapter.NoticeAdminAdapter;
import com.example.foodapp.dao.NoticeDAO;
import com.example.foodapp.model.Notice;

import java.util.ArrayList;

public class AdminNoticeFragment extends Fragment {
    NoticeDAO noticeDAO;
    NoticeAdminAdapter noticeAdminAdapter;
    ArrayList<Notice> listNotice;
    RecyclerView rcvNotice;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_notice,container,false);

        rcvNotice = view.findViewById(R.id.rcvNotice);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvNotice.setLayoutManager(layoutManager);

        noticeDAO = new NoticeDAO(getContext());
        listNotice=noticeDAO.getAll();
        noticeAdminAdapter = new NoticeAdminAdapter(listNotice, getContext());

        rcvNotice.setAdapter(noticeAdminAdapter);


        return view;
    }
}
