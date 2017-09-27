package com.example.root.newsgallery1;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by root on 20/9/17.
 */

public class OTPTextWatcher implements TextWatcher {

    EditText editText;
    OTPTextWatcher(EditText editText1){
        editText = editText1;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


}
