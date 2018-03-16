package app.engine.android.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

import app.engine.android.AppEngine;
import app.engine.android.R;

public class SettingActivity extends BaseUIController implements View.OnClickListener{

    private Button colorPicker;
    private int selectedColor;
    private ToggleButton toggleButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setLayoutType(AppEngine.getInstance().constants.DRAWER_LAYOUT);
        super.onCreate(savedInstanceState);
        this.addLayout(R.layout.activity_setting);
        this.setNavBarTitle("Setting");
        this.toggleButton1 = findViewById(R.id.toggleButton1);
        this.toggleButton1.setBackgroundColor(Integer.parseInt(AppEngine.getInstance().sharedPrefUtils.getPref("THEME_COLOR", this)));

        final ColorPicker cp = new ColorPicker(this, 1, 1, 1);

        this.colorPicker = findViewById(R.id.colorPicker);
        this.colorPicker.setBackgroundColor(Integer.parseInt(AppEngine.getInstance().sharedPrefUtils.getPref("THEME_COLOR", this)));

        this.colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cp.show();
                Button okColor = (Button) cp.findViewById(R.id.okColorButton);
                okColor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedColor = cp.getColor();
                        colorPicker.setBackgroundColor(cp.getColor());
                        AppEngine.getInstance().sharedPrefUtils.putPref("THEME_COLOR",String.valueOf(cp.getColor()),getApplicationContext());
                        cp.dismiss();
                        toggleButton1.setBackgroundColor(Integer.parseInt(AppEngine.getInstance().sharedPrefUtils.getPref("THEME_COLOR", getApplicationContext())));
                    }
                });
            }
        });

    }
}
