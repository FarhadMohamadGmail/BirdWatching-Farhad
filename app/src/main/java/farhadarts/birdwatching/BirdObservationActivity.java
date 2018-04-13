package farhadarts.birdwatching;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BirdObservationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_LOCATION = 1;
//    private TextView locationLatAndLong;
    private LocationManager locationManager;
    String latitude, longitude;
    Button button;
    TextView textView;

    //these 5 lines are related to date and time
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;

    EditText dateTimeView;
    EditText dateTimeView2;

    Button btnDateTime;
    Button btnDateTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_observation);


        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        textView = (TextView) findViewById(R.id.locationView);
        button = (Button) findViewById(R.id.getLocationbt);

        button.setOnClickListener(this);


        dateTimeView = (EditText) findViewById(R.id.dateTimeStart);
        dateTimeView2 = (EditText) findViewById(R.id.dateTimeEnd);
        btnDateTime = (Button) findViewById(R.id.dateTimeStartBt);
        btnDateTime2 = (Button) findViewById(R.id.dateTimeEndBt);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date = simpleDateFormat.format(calendar.getTime());

//        i implemented onclicklistener on button
        btnDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateTimeView.setText(Date);
            }

        });

        btnDateTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateTimeView2.setText(Date);
            }

        });


//        locationLatAndLong = findViewById(R.id.locationView);
//
//        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
////        i clicked on bulb for giving me the persmission and the the if statement accured above
//        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
//
//        onLocationChanged(location);
//    }

//    @Override
//    public void onLocationChanged(Location location) {
////
////        if (location == null) {
////            locationLatAndLong.setText("No location found!");
////            return;
////        }
////        double longtitude = location.getLatitude();
////        double latitude = location.getLatitude();
////        locationLatAndLong.setText("Longtitude: " + longtitude + "Latitude: " + latitude);
//
//
//
//    }
//
//    @Override
//    public void onStatusChanged(String s, int i, Bundle bundle) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String s) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String s) {

    }

    @Override
    public void onClick(View view) {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(BirdObservationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (BirdObservationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(BirdObservationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                latitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                textView.setText("Your current location is" + "\n" + "Lattitude = " + latitude
                        + "\n" + "Longitude = " + longitude);

            } else if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                latitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                textView.setText("Your current location is" + "\n" + "Lattitude = " + latitude
                        + "\n" + "Longitude = " + longitude);


            } else if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                latitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                textView.setText("Your current location is" + "\n" + "Lattitude = " + latitude
                        + "\n" + "Longitude = " + longitude);

            } else {

                Toast.makeText(this, "Unble to Trace your location", Toast.LENGTH_SHORT).show();

            }
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    //the logout app bar button appeard after i added this method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_logout:
                Intent logout = new Intent(BirdObservationActivity.this, LoginActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(logout);
                Toast toast = Toast.makeText(this, "You have logged out!", Toast.LENGTH_SHORT);
                toast.show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    //Posting a bird happens down here


//    public void Register(View view) {
//
//        String nameDanish = ((EditText) findViewById(R.id.NameDanish)).getText().toString();
//        String nameEnglish = ((EditText) findViewById(R.id.NameEnglish)).getText().toString();
//        String dateTime = ((EditText) findViewById(R.id.dateTimeStart)).getText().toString();
//
//
//        String date = dateTime;
//        Calendar calendar = Calendar.getInstance();
//        String datereip = date.replace("/Date(", "").replace(")/", "");
//        Long timeInMillis = Long.valueOf(datereip);
//        calendar.setTimeInMillis(timeInMillis);
//        System.out.println(calendar.getTime());
//
//
//
//        //String jsonDocument =
//        //        "{\"Title\":\"" + title + "\", \"Author\":\"" + author + "\", \"Publisher\":\"" + publisher + "\", \"Price\":" + price + "}";
//
//        TextView messageView = findViewById(R.id.add_book_message_textview);
//        try {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("NameDanish", title);
//            jsonObject.put("NameEnglish", author);
//            jsonObject.put("Publisher", publisher);
//            jsonObject.put("Price", price);
//            String jsonDocument = jsonObject.toString();
//            messageView.setText(jsonDocument);
//            PostBookTask task = new PostBookTask();
//            task.execute("http://birdobservationservice.azurewebsites.net/Service1.svc/observations", jsonDocument);
//        } catch (JSONException ex) {
//            messageView.setText(ex.getMessage());
//        }

    }



