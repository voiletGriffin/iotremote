package com.rawtalent.relaycontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.autofill.AutofillValue;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.core.View;

public class MainActivity extends AppCompatActivity {

    Switch r1,r2,r3,r4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r1=findViewById(R.id.relay1);
        r2=findViewById(R.id.relay2);
        r3=findViewById(R.id.relay3);
        r4=findViewById(R.id.relay4);


        getData();

        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    setData("relay1",1);
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                        }else{
                    setData("relay1",0);
                }
            }
        });

        r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    setData("relay2",1);
                    Toast.makeText( MainActivity.this, "relay 2 started", Toast.LENGTH_SHORT).show();
                }else{
                    setData("relay2",0);
                }
            }
        });
        r3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    setData("relay3",1);
                    Toast.makeText( MainActivity.this, "relay 1 started", Toast.LENGTH_SHORT).show();
                }else{
                    setData("relay3",0);
                }
            }
        });
        r4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    setData("relay4",1);
                    Toast.makeText( MainActivity.this, "relay 1 started", Toast.LENGTH_SHORT).show();
                }else{
                    setData("relay4",0);
                }
            }
        });
    }



    public void getData(){
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("State").document("Relay").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                try {


                    long a =  documentSnapshot.getLong("relay1");
                    long b = documentSnapshot.getLong("relay2");
                    long c = documentSnapshot.getLong("relay3");
                    long d =  documentSnapshot.getLong("relay4");

                    if (a == 1) {
                        r1.setChecked(true);
                    }

                    if (b == 1) {
                        r2.setChecked(true);
                    }

                    if (c == 1) {
                        r3.setChecked(true);
                    }

                    if (d == 1) {
                        r4.setChecked(true);
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Failed to load data: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setData(String name,long data){
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("State").document("Relay").update(name,data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Status changed successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}