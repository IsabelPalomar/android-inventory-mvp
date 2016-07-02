package android.example.com.inventoryapp.products;

import java.util.List;

public interface ProductsView {

    void showProgress();

    void hideProgress();

    void setProducts(List<String> products);

    void showMessage(String message);
}
