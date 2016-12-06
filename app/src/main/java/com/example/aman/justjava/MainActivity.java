package com.example.aman.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText name=(EditText) findViewById(R.id.name);
        String personName=name.getText().toString();
        CheckBox chkVal=(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasCream=chkVal.isChecked();
        CheckBox chkValue=(CheckBox) findViewById(R.id.chocolate_cream_checkbox);
        boolean hasChoco=chkValue.isChecked();
        int Price=calculatePrice(hasCream,hasChoco);
        String orderSummary=createOrderSummary(Price,hasCream,hasChoco,personName);
        /*displayPrice(quantity*5);*/
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"JustJava order for "+personName);
        intent.putExtra(Intent.EXTRA_TEXT,orderSummary);
        if(intent.resolveActivity(getPackageManager())!= null)
        {
            startActivity(intent);
        }

//        displayMessage(orderSummary);
    }
    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */

    public void increment(View view){
        if(quantity==100)
        {
            Toast.makeText(this, "Cannot be more than 100!!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        displayQuantity(quantity);
    }
    public void decrement(View view){


        if(quantity==0){
            Toast.makeText(this, "Cannot be negative!!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        displayQuantity(quantity);


    }
    private int calculatePrice(boolean cream,boolean chocos){
        int price=quantity*5;
        if(cream && chocos){
            price=price+(quantity*1)+(quantity*2);
        }
        else if(chocos) {
          price=price+(quantity*2);
        }
        else if(cream){
            price=price+(quantity*1);
        }
        return price;
    }
    private String createOrderSummary(int price, boolean hasWhippedCream,boolean hasChocolate,String name){
        String orderSummary="Name:"+name+"\nAdd Whipped Cream? "+hasWhippedCream ;
        orderSummary=orderSummary+ "\nAdd Chocolate? "+hasChocolate;
        orderSummary=orderSummary+ "\nQuantity: "+quantity+"\nTotal:$"+price+"\nThank You!";
        return orderSummary;
    }
}
