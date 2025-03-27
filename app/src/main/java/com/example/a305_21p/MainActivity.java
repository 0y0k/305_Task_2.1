package com.example.a305_21p;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerSourceUnit;
    private Spinner spinnerDestinationUnit;
    private EditText editTextValue;
    private Button buttonConvert;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeWidgets();
        setupSpinners();
        setupButtonClickListener();
    }

    private void initializeWidgets() {
        spinnerSourceUnit = findViewById(R.id.spinner_source_unit);
        spinnerDestinationUnit = findViewById(R.id.spinner_destination_unit);
        editTextValue = findViewById(R.id.editText_value);
        buttonConvert = findViewById(R.id.button_convert);
        textViewResult = findViewById(R.id.textView_result);
    }

    private void setupSpinners() {
        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(this,
                R.array.all_units, android.R.layout.simple_spinner_item);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSourceUnit.setAdapter(unitAdapter);
        spinnerDestinationUnit.setAdapter(unitAdapter);
    }

    private void setupButtonClickListener() {
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sourceUnit = spinnerSourceUnit.getSelectedItem().toString();
                String destinationUnit = spinnerDestinationUnit.getSelectedItem().toString();
                String inputValueStr = editTextValue.getText().toString();

                if (inputValueStr.isEmpty()) {
                    textViewResult.setText("Please enter a value to convert.");
                    return;
                }

                double inputValue = Double.parseDouble(inputValueStr);
                double convertedValue = convertValue(sourceUnit, destinationUnit, inputValue);

                if (convertedValue == -1) {
                    textViewResult.setText("Invalid unit combination or error in conversion.");
                } else {
                    textViewResult.setText(String.format("Converted Value: %.2f", convertedValue));
                }
            }
        });
    }

    private double convertValue(String sourceUnit, String destinationUnit, double value) {
        if (isLengthUnit(sourceUnit) && isLengthUnit(destinationUnit)) {
            return UnitConverter.convertLength(sourceUnit, destinationUnit, value);
        } else if (isWeightUnit(sourceUnit) && isWeightUnit(destinationUnit)) {
            return UnitConverter.convertWeight(sourceUnit, destinationUnit, value);
        } else if (isTemperatureUnit(sourceUnit) && isTemperatureUnit(destinationUnit)) {
            return UnitConverter.convertTemperature(sourceUnit, destinationUnit, value);
        } else {
            return -1; // Invalid combination
        }
    }

    private boolean isLengthUnit(String unit) {
        return unit.equals("inch") || unit.equals("foot") || unit.equals("yard") || unit.equals("mile");
    }

    private boolean isWeightUnit(String unit) {
        return unit.equals("pound") || unit.equals("ounce") || unit.equals("ton");
    }

    private boolean isTemperatureUnit(String unit) {
        return unit.equals("C") || unit.equals("F") || unit.equals("K");
    }
}