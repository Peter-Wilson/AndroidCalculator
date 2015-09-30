package inventtech.calculator3000;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        getSupportActionBar().hide();


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

        value.setText(enteredValue);
    }

    public String appendDigit(String digit, String output)
    {
        //don't allow more than 7 characters
        if(output.length() > 20) return output;

        //only allow a single decimal
        if(digit.equals(".") && output.contains(".")) return output;

        //if the first zero, overwrite the value
        if (output.equals("0") && !digit.equals(".")){
            return ""+digit;
        }

        //concat the digit
        return output+digit;
    }

    public void Correct(View v)
    {
            TextView value = (TextView) findViewById(R.id.value);
            String enteredValue = value.getText().toString();

            if(enteredValue.length() == 1)
                value.setText("0");
            else
                value.setText(enteredValue.substring(0,enteredValue.length()-1));
    }
}
