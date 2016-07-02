package android.example.com.inventoryapp.products;


import java.util.List;

public class ProductsPresenterImpl implements ProductsPresenter, FindProductsInteractor.OnFinishedListener  {

    private ProductsView productsView;
    private FindProductsInteractor findProductsInteractor;

    public ProductsPresenterImpl(ProductsView productsView) {
        this.productsView = productsView;
        findProductsInteractor = new FindProductsInteractorImpl();
    }

    @Override
    public void onResume() {
        if(productsView != null){
            productsView.showProgress();
        }

        findProductsInteractor.findProducts(this);

    }

    @Override
    public void onProductClicked(int position) {
        if (productsView != null) {
            productsView.showMessage(String.format("Position %d clicked", position + 1));
        }

    }

    @Override
    public void onDestroy() {
        productsView = null;
    }

    @Override
    public void onFinished(List<String> products) {
        if (productsView != null) {
            productsView.setProducts(products);
            productsView.hideProgress();
        }
    }
}
