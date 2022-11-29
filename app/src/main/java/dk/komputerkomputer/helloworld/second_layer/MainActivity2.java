package dk.komputerkomputer.helloworld.second_layer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dk.komputerkomputer.helloworld.Note;
import dk.komputerkomputer.helloworld.R;
import dk.komputerkomputer.helloworld.first_layer.MainActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        load();
    }

    Note note = new Note();
    ArrayList<String> tempNotes = new ArrayList<>();

    //TODO Button Back, Save, Load.
    public void enterNoteFrontPage(View view) {
        Intent firstActivityIntent = new Intent(this, MainActivity.class);
        startActivity(firstActivityIntent);
    }

    public String fileName() {
        String fileName = "Note-Two.txt";
        return fileName;
    }

    public void save(View view) {
        EditText edtEmTwo = findViewById(R.id.edtEmTwo);
        edtEmTwo.setText(edtEmTwo.getText());
        try {
            FileOutputStream fos = openFileOutput("MainActivityTwo", Context.MODE_PRIVATE);
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
                    openFileInput("MainActivityTwo")));
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