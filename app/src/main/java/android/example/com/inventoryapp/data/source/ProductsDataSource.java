package android.example.com.inventoryapp.data.source;

import android.example.com.inventoryapp.data.Product;
import android.support.annotation.NonNull;

import java.util.List;


public interface ProductsDataSource {

    void getProducts(@NonNull LoadProductsCallback callback);

    interface LoadProductsCallback {
        void onTasksLoaded(List<Product> products);

        void onDataNotAvailable();
    }
}
