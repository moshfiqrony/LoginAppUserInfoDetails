package moshfiqrony.com.mysql2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class EditUserInfo extends AppCompatActivity {
    EditText fullName, phone, address, passport, email, userName, dob;
    Button update;
    SqlMain sqlMain;
    FirebaseAuth firebaseAuth;
    User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);

        firebaseAuth = FirebaseAuth.getInstance();

        dob = (EditText) findViewById(R.id.dob);
        fullName = (EditText) findViewById(R.id.fullName);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        passport = (EditText) findViewById(R.id.passport);
        email = (EditText) findViewById(R.id.backUpEmail);
        userName = (EditText) findViewById(R.id.username);
        try {
            Intent intent = getIntent();
            if(intent.getSerializableExtra("User Data") != null){
                user = (User) intent.getSerializableExtra("User Data");
                fillUserInfo();
            }
        }catch (Exception e){

        }
        update = (Button) findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    user.setEmail(firebaseAuth.getCurrentUser().getEmail());
                    user.setFname(fullName.getText().toString().trim());
                    user.setUserName(userName.getText().toString().trim());
                    user.setPassport(passport.getText().toString().trim());
                    user.setPhone(phone.getText().toString().trim());
                    user.setAddress(address.getText().toString().trim());
                    user.setbEmail(email.getText().toString().trim());
                    user.setDob(dob.getText().toString().trim());
                    connectdb();

            }
        });

    }

    public void dateDialog(View v){
        DatePicker datePicker = new DatePicker(this);
        int day = datePicker.getDayOfMonth();
        int month = (datePicker.getMonth())-1;
        int year = datePicker.getYear();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dob.setText(year + "-" + month + "-" + dayOfMonth);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void connectdb(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SqlMain.getUpdateString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditUserInfo.this, response, Toast.LENGTH_SHORT).show();
                Log.d("mr", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditUserInfo.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                final String email = "email";
                final String fullname = "fullname";
                final String phone = "phone";
                final String address = "address";
                final String passport = "passport";
                final String bemail = "bemail";
                final String username = "username";
                final String dob = "dob";

                Map<String, String> params = new HashMap<String, String>();
                params.put(email, user.getEmail());
                params.put(fullname, user.getFname());
                params.put(phone, user.getPhone());
                params.put(address, user.getAddress());
                params.put(passport, user.getPassport());
                params.put(bemail, user.getbEmail());
                params.put(username, user.getUserName());
                params.put(dob, user.getDob());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EditUserInfo.this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditUserInfo.this, UserHome.class);
        intent.putExtra("User Data", user);
        startActivity(intent);
    }

    public void fillUserInfo(){
        fullName.setText(user.getFname());
        phone.setText(user.getPhone());
    }

}

