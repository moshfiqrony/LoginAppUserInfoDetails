package moshfiqrony.com.mysql2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserHome extends AppCompatActivity implements View.OnClickListener {
    Button logout, editInfo;
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;

    EditUserInfo editUserInfo = new EditUserInfo();

    User user = new User();

    TextView welcome, email, name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        try {
            Intent intent = getIntent();
            if(intent.getSerializableExtra("User Data") != null){
                user = (User) intent.getSerializableExtra("User Data");
            }
        }catch (Exception e){

        }

        logout = (Button) findViewById(R.id.logout);
        editInfo = (Button) findViewById(R.id.editInfo);
        welcome = (TextView) findViewById(R.id.welcomeText);
        email = (TextView) findViewById(R.id.email);
        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        mAuth=FirebaseAuth.getInstance();
        user.setEmail(mAuth.getCurrentUser().getEmail());
        getWelcomeText();




        logout.setOnClickListener(this);
        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this, EditUserInfo.class);
                intent.putExtra("User Data", user);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        mAuth.signOut();
        Intent intent = new Intent(UserHome.this, Login.class);
        startActivity(intent);
    }

    public void getWelcomeText(){
        welcome.setText("Welcome "+user.getEmail());
        email.setText(user.getEmail());
        try {
            name.setText(user.getFname());
            phone.setText(user.getPhone());
        }catch (Exception e){
            Toast.makeText(UserHome.this, "Error Retrieving Data", Toast.LENGTH_SHORT).show();
        }

    }
}
