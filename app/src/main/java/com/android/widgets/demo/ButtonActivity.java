package com.android.widgets.demo;

import android.app.Activity;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ButtonActivity extends Activity {
	
    private String mPlantillaMensajeBoton;
    private String mPlantillaMensajeImageBoton;
    private String mPlantillaMensajeToggleBotonSimple;
    private String mPlantillaMensajeCheckBox;
    public boolean FirstTime = true;
    RadioButton lastClicked;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttons); 
        mPlantillaMensajeBoton = getString(R.string.plantilla_mensaje_boton);
        mPlantillaMensajeImageBoton = getString(R.string.plantilla_mensaje_imagebutton);
        mPlantillaMensajeToggleBotonSimple = getString(R.string.plantilla_mensaje_togglebutton_simple);
        mPlantillaMensajeCheckBox = getString(R.string.plantilla_mensaje_checkbox);
        final RadioGroup group = (RadioGroup) findViewById(R.id.RGroup);
        group.setOnCheckedChangeListener(new RadioGroupInfo());
    }
    
    /** Makes a Toast showing the label of the Button, RadioButton, or CheckBox.
     *  ImageButtons do not have text, and are not subclasses of Button, so you
     *  cannot pass an ImageButton to this method.
     *  You need the muestraInfoImageButton method.
     */
    
    public void muestraTextoBoton(View clickedButton) { 
        Button button = (Button)clickedButton;
        CharSequence text = button.getText();
        String message = String.format(mPlantillaMensajeBoton, text);
        showToast(message);
    }
    public void muestraTextoCheckBoxBoton(View clickedButton) {
        CheckBox button = (CheckBox)clickedButton;
        CharSequence text = button.getText();
        String message1 = String.format(mPlantillaMensajeCheckBox, text);
        if(button.isChecked()){
            message1 += " Button checked";
        }else {
            message1 += " Button not checked";
        }
        showToast(message1);
    }

    public void muestraInfoImageButton(/*int imageId*/View clickedImageButton) {
        ImageButton imageButton = (ImageButton)clickedImageButton;
        CharSequence imageText = imageButton.getContentDescription();
        String message = String.format(mPlantillaMensajeImageBoton, imageText);
        showToast(message);
    }
    
    /** Makes a Toast showing whether you turned ToggleButton on or off. Also
     *  shows the resultant text (label).
     */
    
    public void muestraInfoToggleBoton(View clickedToggleButton) { 
        ToggleButton toggleButton = (ToggleButton)clickedToggleButton;
        CharSequence text = toggleButton.getText();
        String status;
        if (toggleButton.isChecked()) {
            status = "ON";
        } else {
            status = "OFF";
        }
        String message = String.format(mPlantillaMensajeToggleBotonSimple, status, text);
        showToast(message);
    }
    
    
    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private class RadioGroupInfo implements OnCheckedChangeListener {
        private String mPlantillaMensajeNuevaSeleccion;
        private String mPlantillaSeleccionCambiada;

        public RadioGroupInfo() {
            mPlantillaMensajeNuevaSeleccion = getString(R.string.plantilla_mensaje_nuevaseleccion);
            mPlantillaSeleccionCambiada = getString(R.string.plantilla_mensaje_seleccioncambiada);
        }
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton button = (RadioButton) findViewById(group.getCheckedRadioButtonId());
            if(!FirstTime) {
                String message = String.format(mPlantillaSeleccionCambiada, button.getText(), lastClicked.getText());
                showToast(message);
            }else{
                String message = String.format(mPlantillaMensajeNuevaSeleccion, button.getText());
                showToast(message);
            }

            if (group.getCheckedRadioButtonId() != checkedId) {
                group.check(checkedId);
            }
            lastClicked = button;
            FirstTime = false;
        }
    }
}
