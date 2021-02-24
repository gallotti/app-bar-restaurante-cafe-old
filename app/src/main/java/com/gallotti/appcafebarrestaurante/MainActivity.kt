package com.gallotti.appcafebarrestaurante

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gallotti.appcafebarrestaurante.feature.store.StoreListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpFragments()
    }

    private fun setUpFragments() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, StoreListFragment(), StoreListFragment.TAG).commit();
    }
}