package com.android.widgets.demo;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends Activity {
    private String mPlantillaMensajeItemSelected;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        mPlantillaMensajeItemSelected = getString(R.string.plantilla_mensaje_spinner);
        ListView listv = (ListView) findViewById(R.id.listView);
        listv.setOnItemClickListener(new Listener());
    }
    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private class Listener implements AdapterView.OnItemClickListener {
        private boolean isFirst = true;
        @Override
        public void onItemClick(AdapterView<?> adapterView, View selectedView, int position, long id) {
            if (isFirst) isFirst = false;
            else {
                String selection = adapterView.getItemAtPosition(position).toString();
                String message = String.format(mPlantillaMensajeItemSelected, selection);
                showToast(message);
            }
        }
    }
}
