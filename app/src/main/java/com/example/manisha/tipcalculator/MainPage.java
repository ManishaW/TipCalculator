package com.example.manisha.tipcalculator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.manisha.tipcalculator.R.id.activity_main_page;
import static com.example.manisha.tipcalculator.R.id.btn_calculate;
import static com.example.manisha.tipcalculator.R.id.et_defTip;
import static com.example.manisha.tipcalculator.R.id.et_tipPercent;
import static com.example.manisha.tipcalculator.R.id.settings;
import static com.example.manisha.tipcalculator.R.id.textView;

public class MainPage extends AppCompatActivity{
    private float rateValue;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Button btnSuggest;
        btnSuggest=  (Button)findViewById(R.id.btnTipPercent);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        btnSuggest.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                onDialogCreate();

            }
        });



        Button btnCalculate;
        btnCalculate=  (Button)findViewById(R.id.btn_calculate);
        btnCalculate.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent myIntent = new Intent(MainPage.this, Summary.class);
                EditText billAmt = (EditText)findViewById(R.id.et_billAmount);
                //Log.d("yo", billAmt.getText().toString());
                myIntent.putExtra("billAmount", billAmt.getText().toString());
                EditText numPeeps = (EditText)findViewById(R.id.et_numPeople);
                myIntent.putExtra("numPeople", numPeeps.getText().toString());
                EditText tipPercent = (EditText)findViewById(R.id.et_tipPercent);
                myIntent.putExtra("tipPercent", tipPercent.getText().toString());
                if ((Float.parseFloat(billAmt.getText().toString())>0)&&
                        (Integer.parseInt(numPeeps.getText().toString())>0) && (Float.parseFloat(tipPercent.getText().toString())>=0)){
                    startActivity(myIntent);
                }
                else {
                    Toast.makeText(MainPage.this, "There is an error in one of the fields", Toast.LENGTH_SHORT).show();
                    //error
                }


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit: {
                finish();
                System.exit(0);
                break;
            }
            case R.id.settings: {
                //open dilog to change ish
                onSettingsMenuCreate();
                break;
            }
        }
        return false;
    }


    protected void onSettingsMenuCreate () {

        final AlertDialog.Builder mBuild = new AlertDialog.Builder(MainPage.this);
        final View nView = getLayoutInflater().inflate(R.layout.settings, null);

        mBuild.setView(nView);
        final AlertDialog dialog = mBuild.create();
        dialog.show();

        dialog.getWindow().setLayout(700, 600);
        final RadioButton rbDollars = (RadioButton) nView.findViewById((R.id.rb_dollar));
        final RadioButton rbEuros = (RadioButton) nView.findViewById((R.id.rb_Euro));
        RadioButton rbPounds = (RadioButton) nView.findViewById((R.id.rb_pound));
        //change all dollar signs variable and on input page
        final TextView btnSign = (TextView) findViewById(R.id.lb_sign);

        final Button btnSave = (Button) nView.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(MainPage.this, ""+rateValue, Toast.LENGTH_SHORT).show();
                if (rbDollars.isChecked()) {
                    btnSign.setText("$");
                    Summary.currency= "$";
                } else if (rbEuros.isChecked()) {
                    btnSign.setText("€");
                    Summary.currency= "€";
                } else {
                    btnSign.setText("£");
                    Summary.currency="£";
                }

                EditText settingsTipDef = (EditText) nView.findViewById(et_defTip);
                EditText tip = (EditText) findViewById(et_tipPercent);

                if (settingsTipDef.getText().toString()!=""){
                    tip.setText(settingsTipDef.getText().toString());
                }
                dialog.dismiss();
            }
        });
    }


    protected void onDialogCreate (){

        final AlertDialog.Builder mBuild = new AlertDialog.Builder(MainPage.this);
        final View mView = getLayoutInflater().inflate(R.layout.ratingbar,null);

        final RatingBar ratebar = (RatingBar)mView.findViewById(R.id.ratingBar);
        ratebar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
             //Toast.makeText(MainPage.this, ""+rating, Toast.LENGTH_SHORT).show();
                TextView sugTipAmt = (TextView) mView.findViewById(R.id.lbSuggestTipAmount);
                sugTipAmt.setText(Float.toString((rating*2)+10)+ "%");
                rateValue = ((rating*2)+10);
                Toast.makeText(MainPage.this, ""+rateValue, Toast.LENGTH_SHORT).show();

            }
        });
        mBuild.setView(mView);
        final AlertDialog dialog2=mBuild.create();
        dialog2.show();

        final Button btnUse=(Button)mView.findViewById(R.id.btnSubRating);
        btnUse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(MainPage.this, ""+rateValue, Toast.LENGTH_SHORT).show();
                EditText tipAmt = (EditText)findViewById(R.id.et_tipPercent);
                tipAmt.setText(Float.toString(rateValue));
                dialog2.dismiss();

            }
        });


    }
}

