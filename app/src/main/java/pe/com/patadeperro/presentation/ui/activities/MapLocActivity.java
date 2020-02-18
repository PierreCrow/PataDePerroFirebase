package pe.com.patadeperro.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.com.patadeperro.R;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolClickListener;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.domain.model.Lost;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconSize;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static pe.com.patadeperro.presentation.ui.activities.a00MainActivity.EXTRA_MESSAGE;


public class MapLocActivity
        extends AppCompatActivity
        implements OnMapReadyCallback, PermissionsListener {

    private PermissionsManager permissionsManager;
    private MapView mapView;
    private MapboxMap mapboxMap;

    private static final String SOURCE_ID = "SOURCE_ID"; // symbolLayerIconFeatureList
    private static final String LAYER_ID = "LAYER_ID";

    private static final String ICON_ID = "ICON_ID";
    private static final String ICON_HOME = "ICON_HOME";
    private static final String ICON_WORK = "ICON_WORK";

    private LatLng homePoint = new LatLng(-12.1161, -77.0465);
    private LatLng workPoint = new LatLng(-12.0948, -77.0377);

    LocationComponent locationComponent;

    private ArrayList<Lost> listaLost;
    private ArrayList<Symbol> listaSymbol = new ArrayList<>();

    private final int SPLASH_DISPLAY_LENGTH = 1500;
    private Long t0Long = System.currentTimeMillis();
    private Long t1Long = System.currentTimeMillis();
    private ArrayList<CharSequence> listaToastMsg = new ArrayList<>();

    // test bs
    private BottomSheetBehavior sheetBehavior;
    private LinearLayout bottom_sheet;

    private CoordinatorLayout mCoordinatorLayout;

    // bottom sheet (bs) data
    private TextView bsPoint;
    private TextView bsAddress;
    private TextView bsPetName;

    private boolean symbolClickedFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        Mapbox.getInstance(this,
                "pk.eyJ1IjoiZWNvbnRyZXJhczU3IiwiYSI6ImNrNmpwc2JqeTAwM3UzZHAxbW5peDQ3Z2wifQ.DfmZtFCXhX7lEWhx6ZVEpg");
        setContentView(R.layout.activity_map_loc);  // conexión al layout


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("listaLost");
        if (bundle != null) {
            this.listaLost = (ArrayList<Lost>) bundle.getSerializable("listaLost");
        }   // Recibe la lista de Lost

        // test bs
        bsPoint = findViewById(R.id.point);
        bsAddress = findViewById(R.id.address);
        bsPetName = findViewById(R.id.petName);

        bottom_sheet = findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        Button btn_bottom_sheet = findViewById(R.id.btn_bottom_sheet);

        bottom_sheet.setVisibility(View.GONE);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

// click event for show-dismiss bottom sheet
        btn_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bottom_sheet.getVisibility() != View.VISIBLE) {
                    bottom_sheet.setVisibility(View.VISIBLE);
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    btn_bottom_sheet.setText("Expand Sheet");
                    return;
                }

                switch (sheetBehavior.getState()) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED); break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED); break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        bottom_sheet.setVisibility(View.GONE);
                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED); break;
                    default:
                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED); break;
                }
            }
        });
// callback for do something
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {

                if (bottom_sheet.getVisibility() != View.VISIBLE) {
                    btn_bottom_sheet.setText("Open Sheet");
                    return;
                }

                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        btn_bottom_sheet.setText("Open Sheet"); break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        btn_bottom_sheet.setText("Expand Sheet"); break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        btn_bottom_sheet.setText("Close Sheet"); break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
        // end test bs


        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {

        MapLocActivity.this.mapboxMap = mapboxMap;

        List<Feature> symbolLayerIconFeatureList = new ArrayList<>();
        symbolLayerIconFeatureList.add(Feature.fromGeometry(
                Point.fromLngLat(-57.225365, -33.213144)));
        symbolLayerIconFeatureList.add(Feature.fromGeometry(
                Point.fromLngLat(-54.14164, -33.981818)));
        symbolLayerIconFeatureList.add(Feature.fromGeometry(
                Point.fromLngLat(-56.990533, -30.583266)));


        mapboxMap.setStyle(
//                new Style.Builder().fromUri("mapbox://styles/mapbox/cjerxnqt3cgvp2rmyuxbeqme7")
                new Style.Builder().fromUri("mapbox://styles/mapbox/cjf4m44iw0uza2spb3q0a7s41")
//                Style.MAPBOX_STREETS) // <- no tiene .withImage


                        // Add the SymbolLayer icon image to the map style
                        .withImage(ICON_ID,
                                BitmapFactory.decodeResource(
                                        MapLocActivity.this.getResources(), R.drawable.custom_marker))
                        .withImage(ICON_HOME,
                                BitmapFactory.decodeResource(
                                        MapLocActivity.this.getResources(), R.drawable.custom_marker_green))
                        .withImage(ICON_WORK,
                                BitmapFactory.decodeResource(
                                        MapLocActivity.this.getResources(), R.drawable.custom_marker_red))

                        // Adding a GeoJson source for the SymbolLayer icons.
                        .withSource(new GeoJsonSource(SOURCE_ID,
                                FeatureCollection.fromFeatures(symbolLayerIconFeatureList)))

                        // Adding the actual SymbolLayer to the map style. An offset is added that the
                        // bottom of the red marker icon gets fixed to the coordinate, rather than
                        // the middle of the icon being fixed to the coordinate point. This is
                        // offset is not always needed and is dependent on the image that you use
                        // for the SymbolLayer icon.
                        // https://docs.mapbox.com/android/java/examples/generate-an-optimized-route/
                        .withLayer(new SymbolLayer(LAYER_ID, SOURCE_ID)
                                .withProperties(PropertyFactory.iconImage(ICON_ID),
                                        iconSize(0f), // invisible en la zona de definición
                                        iconAllowOverlap(true),
                                        iconIgnorePlacement(true),
                                        iconOffset(new Float[]{0f, -9f})) // casi no afecta?
                        ),


                new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        enableLocationComponent(style);

                        SymbolManager symbolManager = new SymbolManager(mapView, mapboxMap, style);
                        symbolManager.setIconAllowOverlap(true);
                        symbolManager.setIconIgnorePlacement(true);

// Add symbol at specified lat/lon

                        Symbol symbol = symbolManager.create(new SymbolOptions()
//                                .withLatLng(new LatLng(60.169091, 24.939876))
//                                .withLatLng(new LatLng(-12.1161, -77.0465))
                                        .withLatLng(homePoint)
                                        .withIconImage(ICON_HOME)
//                                .withIconSize(2.0f));
                                        .withIconSize(0.1f)   // perfecto, aquí se controla el tamaño
                                        .withIconOffset(new Float[]{-0f, -100f})
                        );

                        Symbol symbol2 = symbolManager.create(new SymbolOptions()
//                                .withLatLng(new LatLng(60.169091, 24.939876))
//                                .withLatLng(new LatLng(-12.1161, -77.0465))
                                        .withLatLng(workPoint)
                                        .withIconImage(ICON_HOME)
                                        .withIconSize(0.1f)   // perfecto, aquí va el tamaño
                                        .withIconOffset(new Float[]{-0f, -100f})
                        );

                        if (listaLost != null) {
                            for (int i = 0; i < listaLost.size(); i++) {
                                Lost lost = listaLost.get(i);
                                double lat = 0d;
                                if (lost.getLat() != "") {
                                    lat = Double.parseDouble(lost.getLat());
                                }
                                double lon = 0d;
                                if (lost.getLng() != "") {
                                    lon = Double.parseDouble(lost.getLng());
                                }
                                LatLng wkPoint = new LatLng(lat, lon);
                                if (lat != 0d) {

                                    // Add symbol at specified location
                                    Symbol wkSymbol = symbolManager.create(new SymbolOptions()
                                            .withLatLng(wkPoint)
                                            .withIconImage(ICON_ID)
                                            .withIconSize(0.1f)   // perfecto, aquí va el tamaño
                                            .withIconOffset(new Float[]{-0f, -100f})
                                    );

                                    listaSymbol.add(wkSymbol);

                                } // item, if not null or zero
                            } // for...
                        } // array not null


                        // onClic anywhere...
                        mapboxMap.addOnMapClickListener(new MapboxMap.OnMapClickListener() {
                            @Override
                            public boolean onMapClick(@NonNull LatLng point) {

                                WaitAMomentPls(0, String.format(
                                        "User clicked at: %s", point.toString() )  );

                                if (symbolClickedFlag) {
                                    symbolClickedFlag = false;
                                    return true;
                                }

                                bottom_sheet.setVisibility(View.GONE);
                                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);


                                return true;   // 2020-02-17 ecv: TRUE o FALSE igual llega a MARKER
                            } // onMapClick
                        }); // addOnMapClickListener


                        // Add click listener and toast on click
                        symbolManager.addClickListener(
                                new OnSymbolClickListener() {
                                    @Override
                                    public void onAnnotationClick(Symbol symbol) {

                                        // busca item lost

                                        String petName = "nop";
                                        String address = "no address yet";
                                        String point = "nowhere";
                                        Lost lost;

                                        for (int i = 0; i < listaSymbol.size(); i++) {
                                            if (symbol == listaSymbol.get(i)) {
                                                lost = listaLost.get(i);
                                                petName = lost.getPetName();
                                                address = lost.getLostAddress();
                                                point = lost.getLat() + "; " + lost.getLng();
                                                break;
                                            } // found
                                        } // loop

                                        if(symbol.getIconImage()==ICON_WORK){
                                            symbol.setIconImage(ICON_HOME);
                                        } else {
                                            symbol.setIconImage(ICON_WORK);
                                        }
                                        symbolManager.update(symbol);

                                        WaitAMomentPls(1, "clic en symbol de: " + petName);

                                        bsPoint.setText("Mascota: [" + petName + "]");
                                        bsAddress.setText(address);
                                        bsPetName.setText(point);
                                        bottom_sheet.setVisibility(View.VISIBLE);
                                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                                        symbolClickedFlag = true;

                                    } // onAnnotationClick
                                } // add click listener, onSymbolClickListener
                        ); // add clickListener





                    } // onStyleLoaded
                });

    }

    private void enableLocationComponent(
            @NonNull Style loadedMapStyle) {

        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            // Get an instance of the component
            LocationComponent
                    locationComponent = mapboxMap.getLocationComponent();

            // Activate with options
            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(
                            this, loadedMapStyle).build());

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);

            // 2020-02-14 ecv: para usarse en otros sitios de esta clase
            this.locationComponent = locationComponent;

        } else {

            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);

        }

    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this,
                "Para mostrar la ciudad donde estamos",
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        } else {
            Toast.makeText(this,
                    "Sin permisos de ubicación, mejor cerramos.",
                    Toast.LENGTH_LONG)
                    .show();
            finish();
        }
    }

    public void onBtnNextClicked(View v) {
        // toast
        Context context = getApplicationContext();
        CharSequence text =
                "Click Botón MapLoc";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(this, a30LostAddListActivity.class);
        String message = "algún dato para la siguiente pantalla";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    } // on clic

    public void onBtnCenterClicked(View v) {
        // toast
        Context context = getApplicationContext();
        CharSequence text =
                "Click Botón Center";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // action
//        LatLng currentLatLng = (LatLng) ...
        Location lastLocation = this.locationComponent.getLastKnownLocation();
        LatLng lastPoint = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());

        CameraPosition position = new CameraPosition.Builder()
//                .target(new LatLng(51.50550, -0.07520))
//                .target(workPoint)
                .target(lastPoint)
                .zoom(14)
                .tilt(20)
                .build();
        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 1500);

    } // on clic

    protected void WaitAMomentPls(double nTimes, CharSequence sMsg) {

        t1Long = System.currentTimeMillis();    // toma tiempo actual

        Long txLong = t1Long - t0Long;            // tiempo transcurrido
        Long tzLong = Double.doubleToLongBits(
                SPLASH_DISPLAY_LENGTH * nTimes - txLong);   // lo que falta para SPLASH time

        int intTimeToWaitSplash = tzLong.intValue();
        if (intTimeToWaitSplash > 0) {
            try {
                sleep(intTimeToWaitSplash);
            } catch (InterruptedException e) {
                currentThread().interrupt();
                e.printStackTrace();
            }
        }

        listaToastMsg.add(sMsg);
        for(int i=0; i<listaToastMsg.size()-2;i++) listaToastMsg.remove(i);

        CharSequence variosToastMsg = "";
        for(int i=0; i<listaToastMsg.size();i++) {

            if(variosToastMsg.length()>0) {
                variosToastMsg = variosToastMsg.toString() + "\n" + listaToastMsg.get(i).toString();
            } else {
                variosToastMsg = listaToastMsg.get(i).toString();
            }
        }

//        Toast toast = new Toast(MapLocActivity.this);
//        toast.setGravity(Gravity.TOP, 0, 0);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setText(variosToastMsg);
//        toast.show();

        Toast.makeText(MapLocActivity.this, variosToastMsg, Toast.LENGTH_LONG)
                .show();

        t0Long = System.currentTimeMillis();    // toma tiempo actual para sgte Wait...

//        BottomSheetDialogFragment

    }

}