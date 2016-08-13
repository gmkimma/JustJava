package com.gregkimma.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    CheckBox whippedCream;
    CheckBox chocolate;

    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        name = (EditText) findViewById(R.id.name);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        if (name.getText().toString().equals("")) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show();
        } else {
            displayMessage(createOrderSummary());
        }
    }

    /**
     * This method is called when the '+' button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "Cannot go above 100 cups", Toast.LENGTH_SHORT).show();
        }
        else {
            quantity++;
            display(quantity);
        }
    }

    /**
     * This method is called when the '-' button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "Cannot go below 1 cup", Toast.LENGTH_SHORT).show();
        }
        else {
            quantity--;
            display(quantity);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int price = 5;
        if (whippedCream.isChecked()) {
            price += 1;
        }
        if (chocolate.isChecked()) {
            price += 2;
        }
        return price * quantity;
    }

    private String createOrderSummary() {
        String priceMessage = "Name: " + name.getText();
        priceMessage += "\nAdd whipped cream? " + whippedCream.isChecked();
        priceMessage += "\nAdd chocolate? " + chocolate.isChecked();
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: " + NumberFormat.getCurrencyInstance().format(calculatePrice());
        priceMessage += "\nThank You!";
        return priceMessage;
    }
}
