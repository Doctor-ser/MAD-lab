package com.example.dbhelper13;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText editTextId, editTextName, editTextEmail;
    Button btnAdd, btnView, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create DatabaseHelper object
        myDb = new DatabaseHelper(this);

        // Initialize EditTexts
        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);

        // Initialize Buttons
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        // Set button listeners
        addData();
        viewAll();
        updateData();
        deleteData();
    }

    // =========================
    // ADD DATA
    // =========================
    private void addData() {

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty()) {

                    Toast.makeText(
                            MainActivity.this,
                            "Please enter Name and Email",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                boolean isInserted = myDb.insertData(name, email);

                if (isInserted) {

                    Toast.makeText(
                            MainActivity.this,
                            "Data Inserted Successfully!",
                            Toast.LENGTH_SHORT
                    ).show();

                    clearFields();

                } else {

                    Toast.makeText(
                            MainActivity.this,
                            "Data Insertion Failed",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }

    // =========================
    // VIEW ALL DATA
    // =========================
    private void viewAll() {

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = myDb.getAllData();

                if (res == null) {

                    showMessage("Error", "Unable to retrieve data");
                    return;
                }

                if (res.getCount() == 0) {

                    showMessage("Error", "Nothing found");
                    res.close();
                    return;
                }

                StringBuilder buffer = new StringBuilder();

                while (res.moveToNext()) {

                    buffer.append("ID: ")
                            .append(res.getString(0))
                            .append("\n");

                    buffer.append("Name: ")
                            .append(res.getString(1))
                            .append("\n");

                    buffer.append("Email: ")
                            .append(res.getString(2))
                            .append("\n\n");
                }

                res.close();

                showMessage("Student Data", buffer.toString());
            }
        });
    }

    // =========================
    // UPDATE DATA
    // =========================
    private void updateData() {

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = editTextId.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();

                if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {

                    Toast.makeText(
                            MainActivity.this,
                            "Please enter ID, Name and Email",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                boolean isUpdated = myDb.updateData(id, name, email);

                if (isUpdated) {

                    Toast.makeText(
                            MainActivity.this,
                            "Data Updated Successfully!",
                            Toast.LENGTH_SHORT
                    ).show();

                    clearFields();

                } else {

                    Toast.makeText(
                            MainActivity.this,
                            "No record found with ID: " + id,
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }

    // =========================
    // DELETE DATA
    // =========================
    private void deleteData() {

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = editTextId.getText().toString().trim();

                if (id.isEmpty()) {

                    Toast.makeText(
                            MainActivity.this,
                            "Please enter ID",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                int deletedRows = myDb.deleteData(id);

                if (deletedRows > 0) {

                    Toast.makeText(
                            MainActivity.this,
                            "Data Deleted Successfully!",
                            Toast.LENGTH_SHORT
                    ).show();

                    clearFields();

                } else {

                    Toast.makeText(
                            MainActivity.this,
                            "No record found with ID: " + id,
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }

    // =========================
    // CLEAR INPUT FIELDS
    // =========================
    private void clearFields() {

        editTextId.setText("");
        editTextName.setText("");
        editTextEmail.setText("");

        editTextId.requestFocus();
    }

    // =========================
    // SHOW ALERT DIALOG
    // =========================
    private void showMessage(String title, String message) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);

        builder.show();
    }
}
