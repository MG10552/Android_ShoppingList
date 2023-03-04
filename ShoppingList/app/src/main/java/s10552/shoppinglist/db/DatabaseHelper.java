package s10552.shoppinglist.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import s10552.shoppinglist.entities.Product;

public class DatabaseHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String NAME = "products";
        private static final String CREATE_TABLE_QUERY = "CREATE TABLE PRODUCTS (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE, quantity REAL, price REAL, bought TEXT)";
        private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS PRODUCTS";
        private static final String SELECT_ALL_QUERY = "SELECT id as _id, name, quantity, price, bought FROM PRODUCTS";
        private static final String COLUMN_NAME = "NAME";
        private static final String COLUMN_QUANTITY = "QUANTITY";
        private static final String COLUMN_PRICE = "PRICE";
        private static final String COLUMN_BOUGHT = "BOUGHT";
        private static final String WHERE_NAME_STRING = "NAME=?";

        public DatabaseHelper(Context context) {
            super(context, NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE_QUERY);
            onCreate(db);
        }


        public void insert(Product product) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME, product.getName());
            contentValues.put(COLUMN_QUANTITY, product.getQuantity());
            contentValues.put(COLUMN_PRICE, product.getPrice());
            contentValues.put(COLUMN_BOUGHT, product.getBought());

            db.insert(NAME, null, contentValues);
        }

        public Cursor getAll(){
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor data = db.rawQuery(SELECT_ALL_QUERY, null);

            return data;
        }

        public Product getById(int id){
            SQLiteDatabase db = this.getReadableDatabase();
            Product product = null;

            Cursor data = db.rawQuery("SELECT * FROM PRODUCTS WHERE id = "+id, null);
            if (data.moveToFirst()) {
                product = new Product();
                product.setId(data.getInt(0));
                product.setName(data.getString(1));
                product.setBought(data.getInt(4));
                product.setQuantity(data.getInt(2));
                product.setPrice(data.getDouble(3));
            }
            data.close();

            return product;
        }

    public Product getByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Product product = null;

        Cursor data = db.rawQuery("SELECT * FROM PRODUCTS WHERE name = \"" + name + "\"", null);
        if (data.moveToFirst()) {
            product = new Product();
            product.setId(data.getInt(0));
            product.setName(data.getString(1));
            product.setBought(data.getInt(4));
            product.setQuantity(data.getInt(2));
            product.setPrice(data.getDouble(3));
        }
        data.close();

        return product;
    }

        public void deleteProduct(Product product){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(NAME, WHERE_NAME_STRING,
                    new String[] { product.getName() });
        }

        public void updateProduct(Product product){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME, product.getName());
            contentValues.put(COLUMN_QUANTITY, product.getQuantity());
            contentValues.put(COLUMN_PRICE, product.getPrice());
            contentValues.put(COLUMN_BOUGHT, product.getBought());

            db.update(NAME, contentValues, "id=?",  new String[] { String.valueOf(product.getId()) });
        }


}
