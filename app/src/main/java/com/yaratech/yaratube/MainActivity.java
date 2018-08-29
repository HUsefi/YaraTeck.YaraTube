package com.yaratech.yaratube;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.ui.login.DialogContainerFragment;
import com.yaratech.yaratube.ui.OnProductItemClick;
import com.yaratech.yaratube.ui.dashbord.BaseFragment;
import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.ui.dashbord.category.CategoryFragment;
import com.yaratech.yaratube.ui.productdetail.ProductDetailFragment;
import com.yaratech.yaratube.ui.productlist.ProductListFragment;

import static com.yaratech.yaratube.ui.productlist.ProductListFragment.PRODUCT_LIST_FRAGMENT_TAG;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        , CategoryFragment.OnCategoryFragmentActionListener
        , OnProductItemClick {

    Toolbar toolbar;
    FragmentTransaction fragmentTransaction;
    ProductListFragment productListFragment;
    ProductDetailFragment productDetailFragment;
    FragmentManager fragmentManager;
    DialogContainerFragment dialogContainerFragment;


    public BaseFragment baseFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        baseFragment = BaseFragment.newInstance();
        setFragment(baseFragment, BaseFragment.BASE_FRAGMENT_TAG);
        //setFragment(BaseFragment.newInstance());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_about_us) {

        } else if (id == R.id.nav_contact_us) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void setFragment(Fragment fragment, String fragmentName) {

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_home_fragment, fragment).commit();
        if (!fragmentName.equals("BaseFragment"))
            fragmentTransaction.addToBackStack(fragmentName);

//        FragmentManager fragmentManager=getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container_home_fragment,fragment)
//                .addToBackStack(null)
//                .commit();
    }

    @Override
    public void onCategorylistItemClicked(CategoryList category) {
        productListFragment = ProductListFragment.newInstance(category.getId());
        setFragment(productListFragment, PRODUCT_LIST_FRAGMENT_TAG);
    }


//    @Override
//    public void onHomeItemClicked(int categoryId) {
//        productDetailFragment = ProductDetailFragment.newInstance(product);
//        setFragment(productDetailFragment, ProductDetailFragment.PRODUCT_DETAIL_FRAGMENT_TAG);
//    }

    @Override
    public void onClick(Object product) {
        productDetailFragment = ProductDetailFragment.newInstance((Product) product);
        setFragment(productDetailFragment, ProductDetailFragment.PRODUCT_DETAIL_FRAGMENT_TAG);

    }

}
