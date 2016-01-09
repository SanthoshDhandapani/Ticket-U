package com.ticketu.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.ticketu.R;
import com.ticketu.interfaces.listeners.SwitchFragment;
import com.ticketu.ui.fragment.HomeFragment;
import com.ticketu.ui.utils.PrintFontIconDrawable;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwitchFragment{

    @Override
    protected void onCreateContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Drawable drawable = PrintFontIconDrawable.getInstance(this).getDrawableFontIcon(R.string.ic_account_circle, android.R.color.white, R.dimen._50sdp);
        ((ImageView)navigationView.getHeaderView(0).findViewById(R.id.imageView)).setImageDrawable(drawable);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new HomeFragment(), HomeFragment.class.getName()).addToBackStack(null).commit();
        setMenuItems();
    }


    private void setMenuItems() {
        Menu menu = ((NavigationView) findViewById(R.id.nav_view)).getMenu();
        String[] iconsArray = getResources().getStringArray(R.array.drawer_list_icons);
        for(int index=0; index<menu.size(); index++) {
            MenuItem menuItem = menu.getItem(index);
            menuItem.setIcon(PrintFontIconDrawable.getInstance(this).getDrawableFontIcon(iconsArray[index], R.color.nav_list_icons, R.dimen._18sdp));
            //menuItem.setIcon();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ticketing) {
            // Handle the action
        } else if (id == R.id.nav_my_tickets) {

        } else if (id == R.id.nav_favourites) {

        } else if (id == R.id.nav_chats) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void replaceFragments(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, Fragment.class.getName()).addToBackStack(null).commit();
    }
}
