package android.example.com.inventoryapp.products;


import java.util.List;

public interface FindProductsInteractor {

    interface OnFinishedListener {
        void onFinished(List<String> products);
    }

    void findProducts(OnFinishedListener listener);
}
