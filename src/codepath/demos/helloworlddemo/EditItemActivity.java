package codepath.demos.helloworlddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by burak.malkoc on 12/9/2016.
 */

public class EditItemActivity extends Activity{
    Button saveButton;
    EditText updateText;
    String editedTextStr;
    String itemPosition;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        updateText = (EditText) findViewById(R.id.editText);
        saveButton = (Button) findViewById(R.id.button);
        bringTheTextForEditing();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTheEditedText();
            }
        });
    }

    public void bringTheTextForEditing(){
        String itemCameBy = DataTransferSIngleton.getInstance().getText();
        itemPosition = DataTransferSIngleton.getInstance().getTextPosition();
//        String itemCameBy = getIntent().getStringExtra("editItem");
//        itemPosition = getIntent().getStringExtra("editItemPosition");
        updateText.setText(itemCameBy);
    }

    public void sendTheEditedText(){
        editedTextStr = updateText.getText().toString();
        Intent intent = new Intent(EditItemActivity.this, HelloWorldActivity.class);
//        intent.setAction(Intent.ACTION_SEND);
        DataTransferSIngleton.getInstance().setText(editedTextStr);
        DataTransferSIngleton.getInstance().setTextPosition(itemPosition);
//        intent.putExtra("editItem", editedTextStr);
//        intent.putExtra("editItemPosition", itemPosition);
//        setResult(Activity.RESULT_OK, intent);

        startActivity(intent);
//        startActivityForResult(intent, REQUEST_CODE);
    }
}
