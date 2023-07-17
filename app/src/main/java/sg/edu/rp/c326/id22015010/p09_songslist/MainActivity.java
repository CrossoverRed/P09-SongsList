package sg.edu.rp.c326.id22015010.p09_songslist;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.String;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText EditTitle, EditSinger, EditYear;
    RadioButton r1Btn, r2Btn, r3Btn, r4Btn, r5Btn;
    Button btnInsert, btnShowList;
    RadioGroup radioGroup;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditTitle = findViewById(R.id.SongEdit);
        EditSinger = findViewById(R.id.SingerEdit);
        EditYear = findViewById(R.id.YearEdit);
        r1Btn = findViewById(R.id.rBtn1);
        r2Btn = findViewById(R.id.rBtn2);
        r3Btn = findViewById(R.id.rBtn3);
        r4Btn = findViewById(R.id.rBtn4);
        r5Btn = findViewById(R.id.rBtn5);
        btnInsert = findViewById(R.id.Insertbtn);
        btnShowList = findViewById(R.id.ShowListbtn);
        radioGroup = findViewById(R.id.RadioGroup);

        dbHelper = new DBHelper(getApplicationContext());

        btnInsert.setOnClickListener(v -> saveSong());

        btnShowList.setOnClickListener(v -> showSongs());

    }

    public void saveSong() {
        String title = EditTitle.getText().toString().trim();
        String singers = EditSinger.getText().toString().trim();
        int year = Integer.parseInt(EditYear.getText().toString().trim());
        int stars = getStars();
        // Check if any required fields are empty

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(singers) || year == 0 || stars == 0) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_LONG).show();
            return;
        }

        //All fields are filled, proceed with saving the song
        dbHelper.insertSong(title, singers, year, stars);

        Toast.makeText(this, "Song saved successfully", Toast.LENGTH_LONG).show();
        Log.d("Insert", "New song inserted"); // Add this line for logging
        clearFields();
    }

    public void showSongs() {
        Intent intent = new Intent(MainActivity.this, SongsActivity.class);
        startActivity(intent);
    }

    private void clearFields() {
        EditTitle.getText().clear();
        EditSinger.getText().clear();
        EditYear.getText().clear();
        radioGroup.clearCheck();
    }

    private int getStars() {
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        String selectedRating = selectedRadioButton.getText().toString();
        return Integer.parseInt(selectedRating);
    }
}