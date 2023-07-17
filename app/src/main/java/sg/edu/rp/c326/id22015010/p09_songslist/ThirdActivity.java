package sg.edu.rp.c326.id22015010.p09_songslist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class ThirdActivity extends AppCompatActivity {
    EditText titleEditText, singersEditText, yearEditText, starsEditText;
    Button updateButton, deleteButton;
    Song song;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        titleEditText = findViewById(R.id.titleEditText);
        singersEditText = findViewById(R.id.singersEditText);
        yearEditText = findViewById(R.id.yearEditText);
        starsEditText = findViewById(R.id.starsEditText);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        // Get the clicked song from the intent
        Song clickedSong = new Song(
                getIntent().getIntExtra("id", 0),
                getIntent().getStringExtra("title"),
                getIntent().getStringExtra("singers"),
                getIntent().getIntExtra("year", 0),
                getIntent().getIntExtra("stars", 0)
        );

        // Set the song object
        song = clickedSong;

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        // Display the song details
        titleEditText.setText(clickedSong.getTitle());
        singersEditText.setText(clickedSong.getSingers());
        yearEditText.setText(String.valueOf(clickedSong.getYear()));
        starsEditText.setText(String.valueOf(clickedSong.getStars()));

        // Set click listener for the update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSong();
            }
        });

        // Set click listener for the delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSong();
            }
        });
    }

    private void updateSong() {
        // Get updated values from EditTexts
        String updatedTitle = titleEditText.getText().toString();
        String updatedSingers = singersEditText.getText().toString();
        int updatedYear = Integer.parseInt(yearEditText.getText().toString());
        int updatedStars = Integer.parseInt(starsEditText.getText().toString());

        // Update the song object with the new values
        song.setTitle(updatedTitle);
        song.setSingers(updatedSingers);
        song.setYear(updatedYear);
        song.setStars(updatedStars);

        // Update the song in the database
        dbHelper.updateSong(song);

        Toast.makeText(this, "Update successful", Toast.LENGTH_LONG).show();

        // Finish the activity
        finish();
    }

    private void deleteSong() {
        // Delete the song from the database
        dbHelper.deleteSong(song);

        Toast.makeText(this, "Deleted successfully", Toast.LENGTH_SHORT).show();

        // Finish the activity
        finish();
    }
    @Override
    public void onBackPressed() {
        // Finish the activity without making any changes
        finish();
    }

}
