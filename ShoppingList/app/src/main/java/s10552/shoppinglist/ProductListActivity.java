package s10552.shoppinglist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.AdapterView;

import s10552.shoppinglist.db.DatabaseHelper;
import s10552.shoppinglist.entities.Product;

public class ProductListActivity extends OptionsHandler {

    private ArrayList<Product> shoppingList = null;
    private ProductAdapter adapter = null;
    private ListView lv = null;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOptions();
        setContentView(R.layout.activity_product_list);

        final Button addProductButton = (Button) findViewById(R.id.add_product);
        final Intent addProductActivityIntent = new Intent(this, AddProductActivity.class);
        final Intent editProductActivityIntent = new Intent(this, EditProductActivity.class);

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(addProductActivityIntent);
            }
        });

        db = new DatabaseHelper(getApplicationContext());

        adapter = new ProductAdapter(this, db.getAll());
        lv = (ListView) findViewById(R.id.product_list_view);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {

                int productId = getProductId(position);

                Product selectedItem = db.getById(productId);
                selectedItem.setBought(selectedItem.getBought() == 1 ? 0 : 1);
                db.updateProduct(selectedItem);

                adapter.changeCursor(db.getAll());

            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {

                int productId = getProductId(position);

                editProductActivityIntent.putExtra("id", productId);
                startActivity(editProductActivityIntent);

                return true;
            }
        });
    }

    private int getProductId(int position) {
        Cursor cur = (Cursor)adapter.getItem(position);
        cur.moveToPosition(position);
        return cur.getInt(cur.getColumnIndexOrThrow("_id"));
    }
}
