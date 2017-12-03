package com.example.app;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapView mMapView;
    View mView;
    ListView listView;

    int[] icons = {R.drawable.ic_edit_location_black_24dp, R.drawable.ic_edit_location_black_24dp, R.drawable.ic_add_location_black_24dp};
    String[] locationName = {"Gym", "McDonalds", "Add location"};
    String[] address = {"Storgatan 17", "Lillgatan 2", ""};


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) mView.findViewById(R.id.lvLocations);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return locationName.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint({"InflateParams", "ViewHolder"})
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //view = getLayoutInflater().inflate(R.layout.customlayout, null);
            view = LayoutInflater.from(getContext()).inflate(R.layout.customlayout, null);

            ImageButton imgBtn = (ImageButton)view.findViewById(R.id.imgMarker);
            TextView tvLocation = (TextView)view.findViewById(R.id.tvLocation);
            TextView tvAddress = (TextView)view.findViewById(R.id.tvAddress);

            imgBtn.setImageResource(icons[i]);
            tvLocation.setText(locationName[i]);
            tvAddress.setText(address[i]);

            return view;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng kista = new LatLng(59.402310, 17.945734);
        mMap.addMarker(new MarkerOptions().position(kista));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kista, 13));
    }
}
