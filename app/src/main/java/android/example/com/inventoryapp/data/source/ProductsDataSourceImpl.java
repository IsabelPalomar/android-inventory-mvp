package android.example.com.inventoryapp.data.source;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.example.com.inventoryapp.data.Product;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


public class ProductsDataSourceImpl implements ProductsDataSource{

    private ProductsDbHelper mDbHelper;

    private ProductsDataSourceImpl(@NonNull Context context) {
        mDbHelper = new ProductsDbHelper(context);
    }


    @Override
    public void getProducts(@NonNull LoadProductsCallback callback) {

        List<Product> products = new ArrayList<Product>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ProductsContract.ProductEntry.COLUMN_NAME_ENTRY_ID,
                ProductsContract.ProductEntry.COLUMN_NAME_PRODUCT,
                ProductsContract.ProductEntry.COLUMN_NAME_PRICE,
                ProductsContract.ProductEntry.COLUMN_NAME_QUANTITY
        };

        Cursor c = db.query(
                ProductsContract.ProductEntry.TABLE_NAME, projection, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                int entryId = c.getInt(c.getColumnIndexOrThrow(ProductsContract.ProductEntry.COLUMN_NAME_ENTRY_ID));
                String productName = c.getString(c.getColumnIndexOrThrow(ProductsContract.ProductEntry.COLUMN_NAME_PRODUCT));
                double price =
                        c.getDouble(c.getColumnIndexOrThrow(ProductsContract.ProductEntry.COLUMN_NAME_PRICE));
                int quantity =  c.getInt(c.getColumnIndexOrThrow(ProductsContract.ProductEntry.COLUMN_NAME_QUANTITY))
                Product product = new Product(entryId, productName, price, quantity);
                products.add(product);
            }
        }
        if (c != null) {
            c.close();
        }

        db.close();

        if (products.isEmpty()) {
            // This will be called if the table is new or just empty.
            callback.onDataNotAvailable();
        } else {
            callback.onTasksLoaded(products);
        }

    }
}


