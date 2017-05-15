package project.dely.com.fewmasksample;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(new CustomTextWatcherForPhoneNumber());
    }

    private class CustomTextWatcherForPhoneNumber implements TextWatcher {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            editText.removeTextChangedListener(this);

            editText.setText(maskChangeMethod(s, MainActivity.this));
            editText.setSelection(editText.getText().length());
            editText.addTextChangedListener(this);
        }
    }

    String  maskChangeMethod(Editable s, Context context){
        String formattedString = "";
        try {
            String originalString = s.toString();

            if (originalString.contains(" ")) {
                originalString = originalString.replaceAll(" ", "");
            }
            Long long_val = Long.parseLong(originalString);

            Resources res = context.getResources();
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);

            switch (s.length()){
                case 6:
                    formatter.applyPattern(res.getString(R.string.mask_for_6));
                    break;
                case 7:
                    formatter.applyPattern(res.getString(R.string.mask_for_7));
                    break;
                case 8:
                    formatter.applyPattern(res.getString(R.string.mask_for_8));
                    break;
                case 9:
                    formatter.applyPattern(res.getString(R.string.mask_for_9));
                    break;
                case 10:
                    formatter.applyPattern(res.getString(R.string.mask_for_10));
                    break;
                case 11:
                    formatter.applyPattern(res.getString(R.string.mask_for_11));
                    break;
                case 12:
                    formatter.applyPattern(res.getString(R.string.mask_for_12));
                    break;
                case 13:
                    formatter.applyPattern(res.getString(R.string.mask_for_13));
                    break;
                case 14:
                    formatter.applyPattern(res.getString(R.string.mask_for_14));
                    break;
                case 15:
                    formatter.applyPattern(res.getString(R.string.mask_for_15));
                    break;
                default:
                    formatter.applyPattern(res.getString(R.string.mask));
                    break;
            }

            formattedString = formatter.format(long_val);
            formattedString = formattedString.replaceAll(","," ");



        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return formattedString;
    }
}
