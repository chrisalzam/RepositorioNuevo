package com.r2devpros.myrestapptest.presentation.storeMap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.r2devpros.myrestapptest.R
import com.r2devpros.myrestapptest.model.Store
import com.r2devpros.myrestapptest.presentation.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class StoreMapActivity : AppCompatActivity(), OnMapReadyCallback {
    private val viewModel by viewModel<StoreMapViewModel>()
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        getData()
    }

    private fun getData() {
        Timber.d("StoreMapActivity: getData: ")
        viewModel.store = intent.getParcelableExtra(GOOGLE_MAP_STORE)
        Timber.d("StoreMapActivity: getData: lat: ${viewModel.store?.latitude}, lon: ${viewModel.store?.longitude}")
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    /*
        //- Generar llave de Google Maps
        //- Actualizar Google_maos_api.xml
        //- Modify response from API (Rest API /Server Repository) StoresByZipCodeResponse.kt
        //-
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val currentStore = viewModel.store ?: return

        // Add a marker and move the camera
        val storeLocation = LatLng(currentStore.latitude, currentStore.longitude)
        mMap.addMarker(MarkerOptions().position(storeLocation).title(currentStore.id))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(storeLocation, 16.0f))
    }
}