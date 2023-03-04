package s10552.shoppinglist;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import s10552.shoppinglist.db.DatabaseHelper;
import s10552.shoppinglist.entities.Product;

public class EditProductActivity extends OptionsHandler {

    private DatabaseHelper db;
    private int id;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOptions();
        setContentView(R.layout.activity_edit_product);

        db = new DatabaseHelper(getApplicationContext());
        id = getIntent().getIntExtra("id", 0);
        product = db.getById(id);

        final EditText editProductName = (EditText) findViewById(R.id.edit_activity_name);
        final EditText editProductQuantity = (EditText) findViewById(R.id.edit_activity_quantity);
        final EditText editProductPrice = (EditText) findViewById(R.id.edit_activity_price);

        final Button saveButton = (Button) findViewById(R.id.edit_activity_save);
        final Button cancelButton = (Button) findViewById(R.id.edit_activity_cancel);
        final Button deleteButton = (Button) findViewById(R.id.edit_activity_delete);

        editProductName.setText(product.getName());
        editProductQuantity.setText(""+product.getQuantity());
        editProductPrice.setText(""+product.getPrice());

        final Intent productListActivityIntent = new Intent(this, ProductListActivity.class);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(productListActivityIntent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editProductName.getText().toString();
                String quantityString = editProductQuantity.getText().toString();
                String priceString = editProductPrice.getText().toString();

                if(name.isEmpty() || quantityString.isEmpty() || priceString.isEmpty()){
                    Toast.makeText(EditProductActivity.this, getResources().getString(R.string.FIELDS_REQUIRED_ERROR), Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!name.equals(product.getName()) && db.getByName(name) != null){
                    Toast.makeText(EditProductActivity.this, getResources().getString(R.string.NAME_TAKEN_ERROR), Toast.LENGTH_SHORT).show();
                    return;
                }

                int quantity = Integer.parseInt(quantityString);
                double price = Double.parseDouble(priceString);


                product.setName(name);
                product.setQuantity(quantity);
                product.setPrice(price);

                db.updateProduct(product);

                startActivity(productListActivityIntent);
                Toast.makeText(EditProductActivity.this, getResources().getString(R.string.SAVED), Toast.LENGTH_SHORT).show();

            }

        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle(getResources().getString(R.string.ALERT_DIALOG_TITLE));
                builder.setMessage(getResources().getString(R.string.ALERT_DIALOG_TEXT));
                builder.setIconAttribute(android.R.attr.alertDialogIcon);

                builder.setPositiveButton(getResources().getString(R.string.ALERT_DIALOG_YES), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int ii) {

                        Product selectedItem = db.getById(id);
                        db.deleteProduct(selectedItem);
                        startActivity(productListActivityIntent);
                        Toast.makeText(EditProductActivity.this, getResources().getString(R.string.DELETED), Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton(getResources().getString(R.string.ALERT_DIALOG_NO), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int ii) {
                                dialog.dismiss();
                            }
                        }
                );

                builder.show();
            }
        });


    }
}
