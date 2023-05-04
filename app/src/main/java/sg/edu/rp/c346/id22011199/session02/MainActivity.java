package sg.edu.rp.c346.id22011199.session02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    TextView displayBill;
    TextView displayAmount;
    EditText displayEnterAmount;
    TextView displayNoOfPax;
    EditText displayEnterNo;
    ToggleButton displayNoSvs;
    ToggleButton displayGST;
    TextView displayDiscount;
    EditText displayDisAmt;
    RadioGroup rgPayment;
    Button displaySplit;
    Button displayReset;
    TextView displayTotal;
    TextView displayEach;
    EditText displayPhone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayBill = findViewById(R.id.textViewBill);
        displayAmount = findViewById(R.id.textViewAmt);
        displayEnterAmount = findViewById(R.id.editTextAmt);
        displayNoOfPax = findViewById(R.id.textViewNoPax);
        displayEnterNo = findViewById(R.id.editTextEnterNo);
        displayNoSvs = findViewById(R.id.toggleButtonNoSvs);
        displayGST = findViewById(R.id.toggleButtonGST);
        displayDiscount = findViewById(R.id.textViewDiscount);
        displayDisAmt = findViewById(R.id.editTextDiscount);
        rgPayment = findViewById(R.id.radioGrpPay);
        displaySplit = findViewById(R.id.buttonSplit);
        displayReset = findViewById(R.id.buttonReset);
        displayTotal = findViewById(R.id.textViewTotal);
        displayEach = findViewById(R.id.textViewEach);
        displayPhone = findViewById(R.id.editTextPhone);

        displaySplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (displayEnterAmount.getText().toString().trim().length() != 0 && displayNoOfPax.getText().toString().trim().length() != 0) {

                    double newAmount = 0.0;
                    if (!displayNoSvs.isChecked()) {
                        double Amount = Double.parseDouble(displayEnterAmount.getText().toString());


                    } else if (displayNoSvs.isChecked() && displayGST.isChecked()) {
                        newAmount = Double.parseDouble(displayEnterAmount.getText().toString()) * 1.18;


                    }
                    if (displayDiscount.getText().toString().trim().length() != 0) {
                        newAmount *= 1 - Double.parseDouble(displayDiscount.getText().toString()) / 100;
                    }
                    if (rgPayment.getCheckedRadioButtonId() == R.id.radioButtonCash) {
                        String mode = "In Cash";
                    } else {
                        String mode = "Via PayNow to" + displayPhone;

                    }
                    displayTotal.setText("Total Bill: $" + String.format("%.2f", newAmount));
                    int numPerson = Integer.parseInt(displayNoOfPax.getText().toString());
                    if (numPerson != 1) {
                        displayEach.setText("Each Pays: $" + String.format("%.2f", newAmount / numPerson));

                    } else {
                        displayEach.setText("Each Pays: $" + newAmount);
                    }
                    displayReset.setOnClickListener((new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            displayEnterAmount.setText("");
                            displayNoOfPax.setText("");
                            displayNoSvs.setChecked(false);
                            displayGST.setChecked(false);
                            displayDiscount.setText("");


                        }
                    }));
                }
            }
        });

    }
}
