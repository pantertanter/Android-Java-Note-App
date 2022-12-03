package dk.komputerkomputer.helloworld.second_layer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import dk.komputerkomputer.helloworld.R;
import dk.komputerkomputer.helloworld.first_layer.MainActivity;


public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        load();

        EditText edtEmTwo = findViewById(R.id.edtEmTwo);

        edtEmTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                save();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtEmTwo.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public void onLongPress(@NonNull MotionEvent e) {
                    Toast.makeText(getApplicationContext(), "Long Press", Toast.LENGTH_SHORT).show();
                    enterNoteFrontPage();
                    super.onLongPress(e);
                }

                @Override
                public boolean onDoubleTap(@NonNull MotionEvent e) {
                    Toast.makeText(getApplicationContext(), "Double Tap", Toast.LENGTH_SHORT).show();
                    enterNoteFrontPage();
                    return super.onDoubleTap(e);
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }

    //TODO Button Back, Save, Load.
    public void enterNoteFrontPage() {
        Intent firstActivityIntent = new Intent(this, MainActivity.class);
        startActivity(firstActivityIntent);
    }

    public void save() {
        EditText edtEmTwo = findViewById(R.id.edtEmTwo);
        edtEmTwo.setText(edtEmTwo.getText());
        try {
            FileOutputStream fos = openFileOutput("MainActivity4", Context.MODE_PRIVATE);
            fos.write(edtEmTwo.getText().toString().getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void load() {
        EditText edtEmTwo = findViewById(R.id.edtEmTwo);
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput("MainActivity4")));
            String inputString;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }
            edtEmTwo.setText(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
