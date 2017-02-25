package codepath.demos.helloworlddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class HelloWorldActivity extends Activity {

    private final int REQUEST_CODE = 20;

    ArrayList<String> todoItems;
    ArrayAdapter aToDoAdapter;
    ListView lvItems;
    EditText etEditText;
    String clickedPosition;
    ArrayList<String> editingList;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvitems);
        lvItems.setAdapter(aToDoAdapter);
        etEditText = (EditText) findViewById(R.id.etEditText);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                todoItems.remove(i);
                aToDoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clickedPosition = String.valueOf(i);
                String selectedFromList = (lvItems.getItemAtPosition(i)).toString();
                launchComposeView(selectedFromList, clickedPosition);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_hello_world, menu);
        return true;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
//            Toast.makeText(this, data.getStringExtra("editItemPosition"), Toast.LENGTH_SHORT).show();
//            updatedEditText();
//        } else {
//            System.out.println("WTH?");
//        }
//    }

    public void updatedEditText() {
        String updatingText = DataTransferSIngleton.getInstance().getText();
        int updatingTextPosition = Integer.valueOf(DataTransferSIngleton.getInstance().getTextPosition());
        System.out.println("Updated text: " + updatingText + "Updated text position: " + updatingTextPosition);
//        String updatingText = intent.getExtras().getString("editItem");
//        int updatingTextPosition = Integer.valueOf(intent.getExtras().getString("editItemPosition"));
        updateText(updatingText, updatingTextPosition);
    }

    public void launchComposeView(String writtenItem, String itemId) {
        DataTransferSIngleton.getInstance().setText(writtenItem);
        DataTransferSIngleton.getInstance().setTextPosition(itemId);
        Intent intent = new Intent(HelloWorldActivity.this, EditItemActivity.class);
        intent.putExtra("editItem", writtenItem);
        intent.putExtra("editItemPosition", itemId);
        startActivity(intent);
    }

    public void addSomeItemsToArray() {
        todoItems = new ArrayList<>();
        todoItems.add("Item 1");
    }

    public void populateArrayItems() {
        addSomeItemsToArray();
        readItems();
        aToDoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try {
            todoItems = new ArrayList<String>(FileUtils.readLines(file));
        } catch (Exception e) {
            Log.d("ReadItems error ", e.getMessage());

        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(file, todoItems);
        } catch (Exception e) {

        }
    }

    public void onAddItem(View view) {
        aToDoAdapter.add(etEditText.getText().toString());
        etEditText.setText("");
        writeItems();
    }

    public void updateText(String str, int i){
        aToDoAdapter.getItem(i);
        aToDoAdapter.insert(str, i);
        aToDoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updatedEditText();
    }
}