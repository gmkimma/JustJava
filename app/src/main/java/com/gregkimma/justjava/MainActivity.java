package com.gregkimma.justjava;

import android.content.Intent;
import android.net.Uri;
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
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        name = (EditText) findViewById(R.id.name);
        if (name.getText().toString().equals("")) {
            Toast.makeText(this, R.string.enter_name_error, Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.just_java_order) + name.getText());
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    /**
     * This method is called when the '+' button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, R.string.Above_100_error, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, R.string.Below_1_error, Toast.LENGTH_SHORT).show();
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
        String priceMessage = getString(R.string.customer_name) + name.getText();
        priceMessage += "\n" + getString(R.string.add_whipped_cream) + whippedCream.isChecked();
        priceMessage += "\n" + getString(R.string.add_chocolate) + chocolate.isChecked();
        priceMessage += "\n" + getString(R.string.quantity_display) + quantity;
        priceMessage += "\n" + getString(R.string.total) + NumberFormat.getCurrencyInstance().format(calculatePrice());
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }
}
