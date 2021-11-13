package at.htlkaindorf.htbla.plfuebungfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddEntryActivity extends AppCompatActivity {

    private TextView textViewCustomText;

    private EditText editTextNewEntry;

    private Button buttonSaveEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        textViewCustomText = findViewById(R.id.textViewCustomText);
        editTextNewEntry = findViewById(R.id.editTextNewEntry);
        buttonSaveEntry = findViewById(R.id.buttonSaveEntry);

        String customText = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        if (customText != null) textViewCustomText.setText(customText);

        buttonSaveEntry.setOnClickListener((view)-> {
            String entryName = editTextNewEntry.getText().toString();

            if (entryName.length() < 1) {
                Toast.makeText(AddEntryActivity.this, "Der Name darf nicht leer sein.", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_TEXT, entryName);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}