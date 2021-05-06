package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class VisuRvActivity extends AppCompatActivity {

    EditText pt1;
    EditText pt2;
    EditText pt3;
    EditText pt4;
    EditText pt5;
    EditText pt6;
    EditText pt7;
    EditText pt8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visu_rv);

        pt1 = (EditText) findViewById(R.id.pt1);
        pt2 = (EditText) findViewById(R.id.pt2);
        pt3 = (EditText) findViewById(R.id.pt3);
        pt4 = (EditText) findViewById(R.id.pt4);
        pt5 = (EditText) findViewById(R.id.pt5);
        pt6 = (EditText) findViewById(R.id.pt6);
        pt7 = (EditText) findViewById(R.id.pt7);
        pt8 = (EditText) findViewById(R.id.pt8);

    }
}