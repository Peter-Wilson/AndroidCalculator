package inventtech.calculator3000;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    private float total;
    private float operator;
    private String operation;
    private String prev_operation;
    private boolean equalsJustPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        getSupportActionBar().hide();
        operation = "";
        prev_operation = "";
        total = 0;
        operator = 0;
        equalsJustPressed = true;



        TextView output = (TextView)findViewById(R.id.value);
        output.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/calculator_led.ttf"));

        TextView history = (TextView)findViewById(R.id.history);
        history.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/calculator_led.ttf"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ConcatNumber(View v)
    {
        TextView value = (TextView) findViewById(R.id.value);
        String enteredValue = value.getText().toString();

        if(equalsJustPressed)
        {
            enteredValue = "0";
            equalsJustPressed = false;
        }

        switch(v.getId()) {
            case (R.id.one):
                enteredValue = appendDigit("1", enteredValue);
                break;
            case (R.id.two):
                enteredValue = appendDigit("2", enteredValue);
                break;
            case (R.id.three):
                enteredValue = appendDigit("3", enteredValue);
                break;
            case (R.id.four):
                enteredValue = appendDigit("4", enteredValue);
                break;
            case (R.id.five):
                enteredValue = appendDigit("5", enteredValue);
                break;
            case (R.id.six):
                enteredValue = appendDigit("6", enteredValue);
                break;
            case (R.id.seven):
                enteredValue = appendDigit("7", enteredValue);
                break;
            case (R.id.eight):
                enteredValue = appendDigit("8", enteredValue);
                break;
            case (R.id.nine):
                enteredValue = appendDigit("9", enteredValue);
                break;
            case (R.id.zero):
                enteredValue = appendDigit("0", enteredValue);
                break;
            case(R.id.decimal):
                enteredValue = appendDigit(".", enteredValue);
                break;

        }

        setTheOutput(value, enteredValue);
    }

    public String appendDigit(String digit, String output)
    {
        //don't allow more than 9 characters
        if(output.length() > 11) return output;

        //only allow a single decimal
        if(digit.equals(".") && output.contains(".")) return output;

        //if the first zero, overwrite the value
        if (output.equals("0") && !digit.equals(".")){
            return ""+digit;
        }

        //concat the digit
        return output+digit;
    }

    public void setTheOutput(TextView view, String s)
    {
        s.replace('*','-');
        view.setText(s);
    }

    public void OperandFunction(View v)
    {
        TextView value = (TextView) findViewById(R.id.value);
        if(value.getText().toString().equals("ERROR")) return;

        switch(((Button)v).getText().toString()) {
            //don't perform on total but on new value
            case "+-":
                if(equalsJustPressed == true) total *= -1;
                if(Float.valueOf(value.getText().toString()) == 0) return;
                setTheOutput(value, "" + (Float.valueOf(value.getText().toString()) * -1));
                break;


            //don't perform on total but on new value
            case "%":
                if(equalsJustPressed == true) total /= 100;
                setTheOutput(value, "" + (Float.valueOf(value.getText().toString()) / 100));
                break;
        }
    }

    public void Correct(View v)
    {
        Clear();
    }

    private void Clear() {
        ((TextView) findViewById(R.id.value)).setText("0");
        total = 0;
        operation = "";
        equalsJustPressed = false;
    }

    public void PlusMinus(View v)
    {
        TextView value = (TextView) findViewById(R.id.value);

        if(!operation.equals("")) {
            Equals(v);
        }
        else
        {
            if(value.getText().toString().equals("ERROR")) return;
            operator = Float.valueOf(value.getText().toString());
            total = operator;
            setTheOutput(value, "0");
            equalsJustPressed = true;
        }

        operation = ((Button)v).getText().toString();

    }

    public void Equals(View v)
    {
        
        TextView value = (TextView) findViewById(R.id.value);
        if(operation != "") {
            operator = Float.valueOf(value.getText().toString());
        }
        else if(prev_operation != "") {
            operation = prev_operation;
        }

        switch(operation){
            case "+":
                total += operator;
                setTheOutput(value, "" + total);
                break;

            case "-":
                total -= operator;
                setTheOutput(value, "" + total);
                break;

            case "/":
                if(operator == 0) { total = 0; setTheOutput(value, "ERROR");break; }
                total /= operator;
                setTheOutput(value, "" + total);
                break;

            case "x":
                total *= operator;
                setTheOutput(value,""+total);
                break;
        }

        equalsJustPressed = true;
        prev_operation = operation;
        operation = "";

    }
}
