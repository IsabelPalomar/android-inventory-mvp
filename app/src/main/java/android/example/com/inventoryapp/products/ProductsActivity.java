package android.example.com.inventoryapp.products;

import android.example.com.inventoryapp.R;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class ProductsActivity extends AppCompatActivity implements ProductsView, AdapterView.OnItemClickListener {

    private ListView lvProducts;
    private ProgressBar pbProducts;
    private ProductsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProducts = (ListView) findViewById(R.id.lvProducts);
        pbProducts = (ProgressBar) findViewById(R.id.pbProducts);

        lvProducts.setOnItemClickListener(this);
        presenter = new ProductsPresenterImpl(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        pbProducts.setVisibility(View.VISIBLE);
        lvProducts.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        pbProducts.setVisibility(View.INVISIBLE);
        lvProducts.setVisibility(View.VISIBLE);
    }

    @Override
    public void setProducts(List<String> products) {
        lvProducts.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onProductClicked(position);
    }
}
