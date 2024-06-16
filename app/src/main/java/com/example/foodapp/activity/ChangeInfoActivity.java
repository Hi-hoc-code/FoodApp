package com.example.foodapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class ChangeInfoActivity extends AppCompatActivity {

    ImageView btnBackInfo;
    TextInputEditText edtChangeName, edtChangePhone;
    TextView tvNameInfo, tvPhoneInfo;
    Button btnChangeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        btnBackInfo = findViewById(R.id.btnBackInfo);
        edtChangeName = findViewById(R.id.edtChangeName);
        edtChangePhone = findViewById(R.id.edtChangePhone);
        tvNameInfo = findViewById(R.id.tvNameInfo);
        tvPhoneInfo = findViewById(R.id.tvPhoneInfo);
        btnChangeInfo = findViewById(R.id.btnChangeInfo);

        btnChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtChangeName.getText().toString().trim();
                String phone = edtChangePhone.getText().toString().trim();

                if (name.isEmpty()) {
                    edtChangeName.setError("Vui lòng nhập tên");
                    edtChangeName.requestFocus();
                    return;
                }

                if (name.length() < 10) {
                    edtChangeName.setError("Trên 10 kí tự");
                    edtChangeName.requestFocus();
                    return;
                }

                if (phone.isEmpty()) {
                    edtChangePhone.setError("Vui lòng nhập số điện thoại");
                    edtChangePhone.requestFocus();
                    return;
                }

                if (phone.length() != 10) {
                    edtChangePhone.setError("Bao gồm 10 chu số");
                    edtChangePhone.requestFocus();
                    return;
                }

                // Nếu các trường nhập liệu không rỗng và đúng định dạng, hiển thị dialog xác nhận
                showConfirmDialog(name, phone);
            }
        });

        // Xử lý sự kiện của nút quay lại
        btnBackInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Phương thức hiển thị dialog xác nhận
    private void showConfirmDialog(final String name, final String phone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_confirm_change_info);

        final AlertDialog dialog = builder.create();
        dialog.show();

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnConfirm = dialog.findViewById(R.id.btnConfirm);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // Đóng dialog khi người dùng chọn Hủy
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện cập nhật thông tin và đóng dialog
                tvNameInfo.setText(name);
                tvPhoneInfo.setText(phone);
                Toast.makeText(ChangeInfoActivity.this, "Thay đổi thông tin thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

}

