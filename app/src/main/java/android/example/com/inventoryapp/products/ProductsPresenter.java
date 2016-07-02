package android.example.com.inventoryapp.products;


public interface ProductsPresenter {

    void onResume();

    void onProductClicked(int position);

    void onDestroy();
}
