package com.example.mbura.airconditionfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Musa Burak BOYRAZ
 * 03.08.2017
 * Android activity connected FireBase
 * In the firebase RealTime Database you can change the value of textview on your activity
 * You can change the value of RealTime Database condition from activity when pressing buttons
 */

public class AirConditionActivity extends AppCompatActivity implements View.OnClickListener {
    private Firebase mRef;
    private Button buttonSunny;
    private Button buttonFoggy;
    private TextView textViewCondition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_condition);
        Firebase.setAndroidContext(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        navi();
        // set firebase client HERE !!!
        mRef=new Firebase("https://airconditionfirebase.firebaseio.com/condition");
        mRef.addValueEventListener(new ValueEventListener() {
            //On Firebase RealTime Database the value when change this method will run
            //and in this method changed text on activity
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String newCondition= (String) dataSnapshot.getValue();
                textViewCondition.setText(newCondition);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        buttonSunny.setOnClickListener(this);
        buttonFoggy.setOnClickListener(this);
     }

    public void navi(){
        //the definition of Xml components
        buttonSunny= (Button) findViewById(R.id.buttonsunny);
        buttonFoggy= (Button) findViewById(R.id.buttonfoggy);
        textViewCondition= (TextView) findViewById(R.id.textviewcondition);
    }


    @Override
    public void onClick(View view) {
        //Button click events
        switch (view.getId()) {
            case R.id.buttonsunny:
                mRef.setValue("Sunny");
                break;
            case R.id.buttonfoggy:
                mRef.setValue("Foggy");
                break;
        }
    }
}
