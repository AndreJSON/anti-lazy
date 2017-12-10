package com.example.app;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.text.Layout;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

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


    //TODO data below should be retrieved from database instead
    int[] icons = {R.drawable.ic_edit_location_black_24dp, R.drawable.ic_edit_location_black_24dp};
    String[] locationName = {"Gym", "McDonalds"};
    String[] address = {"Storgatan 17", "Lillgatan 2"};


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

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng));

                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View alertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dialog, null);
                final EditText locationName = (EditText) alertView.findViewById(R.id.etLocName);
                final EditText locationAddress = (EditText) alertView.findViewById(R.id.etLocAddress);
                CheckBox cbIsGood = (CheckBox) alertView.findViewById(R.id.cbIsGood);
                Button add = (Button) alertView.findViewById(R.id.btnAdd);

                mBuilder.setView(alertView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!locationName.getText().toString().isEmpty() && !locationAddress.getText().toString().isEmpty()){
                            Toast.makeText(getActivity(), "Added new location!", Toast.LENGTH_SHORT).show();

                            //TODO add new values to database and update listview
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getActivity(), "Error, fill in all fields!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
