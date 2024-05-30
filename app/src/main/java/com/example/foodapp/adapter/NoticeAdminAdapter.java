package com.example.foodapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.dao.NoticeDAO;
import com.example.foodapp.model.Notice;

import java.util.ArrayList;

public class NoticeAdminAdapter extends RecyclerView.Adapter<NoticeAdminAdapter.MyViewHoler> {
    private ArrayList<Notice> list;
    private Context context;
    private NoticeDAO noticeDAO;

    public NoticeAdminAdapter(ArrayList<Notice> list, Context context) {
        this.list = list;
        this.context = context;
        this.noticeDAO = new NoticeDAO(context);
    }

    @NonNull
    @Override
    public MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_notice_admin, parent, false);
        return new MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoler holder, int position) {
        Notice notice = list.get(holder.getAdapterPosition());
        holder.tvTitle.setText(notice.getTitle());
        holder.tvContent.setText(notice.getContent());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(notice, holder.getAdapterPosition());
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the notice from the database
                boolean deleted = noticeDAO.delete(notice.getId());
                if (deleted) {
                    list.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    notifyItemRangeChanged(holder.getAdapterPosition(   ), list.size());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoler extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvContent;
        ImageButton btnDelete, btnEdit;

        public MyViewHoler(@NonNull View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvContent = view.findViewById(R.id.tvContent);
            btnDelete = view.findViewById(R.id.btnDelete);
            btnEdit = view.findViewById(R.id.btnEdit);
        }
    }

    private void showEditDialog(Notice notice, int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_edit_notice, null);
        builder.setView(dialogView);

        EditText etTitle = dialogView.findViewById(R.id.etTitle);
        EditText etContent = dialogView.findViewById(R.id.etContent);

        etTitle.setText(notice.getTitle());
        etContent.setText(notice.getContent());

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newTitle = etTitle.getText().toString();
                String newContent = etContent.getText().toString();

                notice.setTitle(newTitle);
                notice.setContent(newContent);

                boolean updated = noticeDAO.update(notice);
                if (updated) {
                    list.set(position, notice);
                    notifyItemChanged(position);
                    list.clear();
                    list.addAll(noticeDAO.getAll());
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
