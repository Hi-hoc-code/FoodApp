package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.foodapp.R;
import com.example.foodapp.dao.CartDAO;
import com.example.foodapp.dao.ProductDAO;
import com.example.foodapp.model.Cart;
import com.example.foodapp.model.Products;

public class DetailFoodActivity extends AppCompatActivity {

    private TextView tvFoodName, tvFoodPrice;
    private ImageView imgFood;
    private EditText etQuantity;
    private Button btnAddToCart;
    private ImageButton btnDecrease, btnIncrease, btnBack;
    private ProductDAO productDAO;
    private Products product;
    private CartDAO cartDAO;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        // Initialize views
        tvFoodName = findViewById(R.id.tvFoodNameDetail);
        tvFoodPrice = findViewById(R.id.tvFoodPriceDetail);
        imgFood = findViewById(R.id.imgFoodDetail);
        etQuantity = findViewById(R.id.etQuantity);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBack = findViewById(R.id.btnBack);
        productDAO = new ProductDAO(getApplicationContext());
        cartDAO = new CartDAO(getApplicationContext());

        // Get data from intent
        String nameFood = getIntent().getStringExtra("foodName");
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("idUser");
            if (userId != null) {
                Toast.makeText(this, "User ID: " + userId, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "User ID is null", Toast.LENGTH_SHORT).show();
            }
        }

        if (nameFood != null) {
            // Fetch product details using the product ID
            product = productDAO.getProductById(nameFood);

            // Check if product is not null and set data to views
            if (product != null) {
                tvFoodName.setText(product.getName());
                tvFoodPrice.setText(product.getPrice() + "$");
                int resImg = getResources().getIdentifier(product.getImage(), "drawable", getPackageName());
                imgFood.setImageResource(resImg);
            } else {
                Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity if product not found
            }
        } else {
            Toast.makeText(this, "Invalid product ID", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if no valid ID is passed
        }

        // Configure quantity buttons
        btnDecrease.setOnClickListener(v -> {
            int quantity = getQuantity();
            if (quantity > 1) {
                quantity--;
                etQuantity.setText(String.valueOf(quantity));
            }
        });

        btnIncrease.setOnClickListener(v -> {
            int quantity = getQuantity();
            if (quantity < 50) {
                quantity++;
                etQuantity.setText(String.valueOf(quantity));
            }
        });

        // Handle Add to Cart button click
        btnAddToCart.setOnClickListener(v -> {
            int quantity = getQuantity();
            if (quantity > 0 && quantity <= 50) {
                Cart cart = new Cart(userId, nameFood, product.getImage(), product.getDes(), product.getPrice());
                cartDAO.addToCart(cart);
                Toast.makeText(DetailFoodActivity.this, "Added " + quantity + " items to cart.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DetailFoodActivity.this, "Please enter a valid quantity (1-50).", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(v -> finish());

        // Add text change listener to ensure valid input
        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateQuantity();
            }
        });
    }

    private int getQuantity() {
        try {
            return Integer.parseInt(etQuantity.getText().toString());
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    private void validateQuantity() {
        int quantity = getQuantity();
        if (quantity < 1) {
            etQuantity.setText("1");
        } else if (quantity > 50) {
            etQuantity.setText("50");
        }
    }
}
