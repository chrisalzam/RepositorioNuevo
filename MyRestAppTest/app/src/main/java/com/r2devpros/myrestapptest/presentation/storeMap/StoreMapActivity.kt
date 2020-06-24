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
import com.r2devpros.myrestapptest.presentation.*
import timber.log.Timber

class StoreMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private var latitudeStore = 0.0
    private var longitudeStore = 0.0
    private var idStore: String? = ""
    private var addressStore: String? = ""
    private var phoneStore: String? = ""
    private var serviceStore: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        getData()
        Timber.d("StoreMapActivity_TAG: onCreate: $latitudeStore, $longitudeStore")
    }

    private fun getData() {
        val bundle = intent.getBundleExtra(GOOGLE_MAP_FIRST_KEY)
        idStore = bundle?.getString(GOOGLE_MAP_ID_STORE_KEY, "")
        addressStore = bundle?.getString(GOOGLE_MAP_ADDRESS_STORE_KEY, "")
        phoneStore = bundle?.getString(GOOGLE_MAP_PHONE_STORE_KEY, "")
        serviceStore = bundle?.getString(GOOGLE_MAP_HOURS_SERVICE_STORE_KEY, "")
        latitudeStore.apply {
            bundle?.getDouble(GOOGLE_MAP_LATITUDE_STORE_KEY, 0.0)
        }
        longitudeStore.apply {
            bundle?.getDouble(GOOGLE_MAP_LONGITUDE_STORE_KEY, 0.0)
        }
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(latitudeStore, longitudeStore)
        //- Generar llave de Google Maps
        //- Actualizar Google_maos_api.xml
        //- Modify response from API (Rest API /Server Repository) StoresByZipCodeResponse.kt
        //-
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}