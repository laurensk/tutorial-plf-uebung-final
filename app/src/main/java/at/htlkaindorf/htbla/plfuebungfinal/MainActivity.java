package at.htlkaindorf.htbla.plfuebungfinal;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    private Button buttonNextActivity;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private List<String> listEntries = new ArrayList<>();

    private ActivityResultLauncher<Intent> launchSecondActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    String listEntry = result.getData().getStringExtra(Intent.EXTRA_TEXT);
                    listEntries.add(listEntry);
                    adapter.notifyDataSetChanged();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        buttonNextActivity = findViewById(R.id.buttonNextActivity);
        listView = findViewById(R.id.listView);

        listEntries.add("Never");
        listEntries.add("Gonna");
        listEntries.add("Give");
        listEntries.add("You");
        listEntries.add("Up");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listEntries);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            String selection = (String) listView.getItemAtPosition(position);
            Toast.makeText(MainActivity.this, "Element '" + selection + "' wird gelöscht...", Toast.LENGTH_SHORT).show();

            listEntries.remove(position);
            adapter.notifyDataSetChanged();
        });

        buttonNextActivity.setOnClickListener((view) -> {
            String stringForNextActivity = editText.getText().toString();

            Intent intent = new Intent(MainActivity.this, AddEntryActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, stringForNextActivity);

            launchSecondActivity.launch(intent);
        });
    }
}