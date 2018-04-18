package com.android.widgets.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GridViewActivity extends Activity {
    private String mPlantillaMensajeItemSelected;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        mPlantillaMensajeItemSelected = getString(R.string.plantilla_mensaje_spinner);
        GridView gv = (GridView) findViewById(R.id.gridView);
        List<String> androidVendors = getAndroidVendors();

        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, androidVendors);
        gv.setAdapter(Adapter);
        gv.setOnItemClickListener(new GridViewInfo());

    }

    private List<String> getAndroidVendors() {
        String[] vendorArray = { "Acer", "Dell","HTC", "Huawei", "Kyocera", "LG", "Motorola",
                "Nexus", "Samsung", "Sony Ericsson","T-Mobile", "Neptune"};
        List<String> vendorList = Arrays.asList(vendorArray);
        Collections.shuffle(vendorList);
        return(vendorList);
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private class GridViewInfo implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View selectedView, int position, long id) {
            String selection = adapterView.getItemAtPosition(position).toString();
            String message = String.format(mPlantillaMensajeItemSelected, selection);
            showToast(message);
        }
    }

}
