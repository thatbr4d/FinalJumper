package com.bradleywilcox.finaljumper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by MichaelCha on 4/28/2017.
 */

public class About extends AppCompatActivity implements View.OnClickListener {

    public TextView txtAbout, txtAbout2;
    public Button butt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        txtAbout = (TextView) findViewById(R.id.textViewAbout);
        txtAbout2 = (TextView) findViewById(R.id.textViewAbout2);
        butt1 = (Button) findViewById(R.id.buttonAboutClose);

        butt1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v==butt1)
        {
            Intent intent = new Intent(this, Start.class);
            startActivity(intent);
        }
    }
}
