package com.example.manisha.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Summary extends AppCompatActivity {
    static String currency = "$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);


        Bundle extras = getIntent().getExtras();
        String getBillAmt = extras.getString("billAmount");
        TextView billAmt = (TextView)findViewById(R.id.sum_BillAmount);
        billAmt.setText(currency+getBillAmt);

        String getNumPeeps = extras.getString("numPeople");
        TextView numPeeps = (TextView)findViewById(R.id.sum_NumPeople);
        numPeeps.setText(getNumPeeps);

        String getTipPercent = extras.getString("tipPercent");
        TextView tipPercent = (TextView)findViewById(R.id.sum_TipPercent);
        tipPercent.setText(getTipPercent+ "%");

        //tip amount = billamt*tipPercent
        TextView tipAmount= (TextView)findViewById(R.id.sum_TipAmount);
        float tipAmountFloat=  Float.parseFloat(getBillAmt)* (Float.parseFloat(getTipPercent)/100);
        tipAmount.setText(currency +tipAmountFloat);

        //total amount = tipAmount +bill amount
        TextView totalAmount= (TextView)findViewById(R.id.sum_TotalAmount);
        Float totalAmountFloat = tipAmountFloat + Float.parseFloat(getBillAmt);
        totalAmount.setText(currency + totalAmountFloat);

        //tip per person = tipamount/num
        TextView tipPerPerson= (TextView)findViewById(R.id.sum_TipPerPerson);
        float tipPerPersonFloat = (tipAmountFloat/ Float.parseFloat(getNumPeeps));
        tipPerPerson.setText(currency + tipPerPersonFloat);

        //total amount paid per person = totalamount
        // / numpeople
        TextView totalPerPerson= (TextView)findViewById(R.id.sum_EachPersonPays);
        float totalPerPersonFloat = (totalAmountFloat/Float.parseFloat(getNumPeeps));
        totalPerPerson.setText(currency + totalPerPersonFloat);


    }
}
