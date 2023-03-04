package s10552.shoppinglist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ProductAdapter extends CursorAdapter {

    public ProductAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.adapter_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView display_name = (TextView) view.findViewById(R.id.adapter_name);
        TextView display_quantity = (TextView) view.findViewById(R.id.adapter_quantity);
        TextView display_price = (TextView) view.findViewById(R.id.adapter_price);
        TextView display_bought = (TextView) view.findViewById(R.id.adapter_bought);


        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
        double price = cursor.getInt(cursor.getColumnIndexOrThrow("price"));
        int bought = cursor.getInt(cursor.getColumnIndexOrThrow("bought"));

        display_name.setText(name);
        display_quantity.setText(String.valueOf(quantity));
        display_price.setText(String.valueOf(price));

        if(bought == 1){
            display_bought.setText("Bought");
        } else {
            display_bought.setText("To Buy");
        }
    }
}