package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.dao.NoticeDAO;
import com.example.foodapp.model.Notice;

import java.util.ArrayList;

public class NotificationUser extends RecyclerView.Adapter<NotificationUser.MyViewHolder> {
    private ArrayList<Notice> list;
    private Context context;
    private NoticeDAO noticeDAO;

    public NotificationUser(ArrayList<Notice> list, Context context) {
        this.list = list;
        this.context = context;
        this.noticeDAO = new NoticeDAO(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_notice_user, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Notice notice = list.get(holder.getAdapterPosition());
        holder.tvTitle.setText(notice.getTitle());
        holder.tvContent.setText(notice.getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvContent;
        ImageButton btnDelete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitleU);
            tvContent = view.findViewById(R.id.tvContentU);
        }
    }
}
