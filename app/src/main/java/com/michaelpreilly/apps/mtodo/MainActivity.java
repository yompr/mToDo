package com.michaelpreilly.apps.mtodo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.drawable.ic_lock_lock;


// TODO: Need to create auth state listeners
// TODO: login works, but handling the success/failure of a login doesn't work right

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String mUsername;
    private String mPhotoUrl;
    public static FirebaseDatabase mDatabase;
    public static DatabaseReference userDBRef;
    public static DatabaseReference projectsDBRef;
    public static DatabaseReference utasksDBRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public static DataSnapshot projectData,taskData;
    private ListView mTaskListView;
    private ArrayAdapter<String> mTaskAdapter;
    public static List<MTask> MTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // If logged in do the add activity
                // if not do the login activity
                if (mFirebaseAuth.getCurrentUser() != null) {
                    Intent intent = new Intent(view.getContext(), MTaskActivity.class);
                    startActivityForResult(intent,0);

                            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                // Tge below works, but it's not where I want to do it
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivityForResult(intent, 0);
            }
            }
        });


        Log.d("MPR-TEST", "Start2");


        mFirebaseAuth = FirebaseAuth.getInstance();
        if (mFirebaseAuth.getCurrentUser() == null) {
            Log.d("MPR-TEST", "no firebase user");
            //@android:drawable/ic_lock_lock @color/colorAccent
            fab.setImageResource(R.drawable.common_google_signin_btn_icon_dark);


           // fab.setBackgroundDrawable(@android:drawable/ic_lock_lock);
//fab.setBackgroundColor(getResources().);
           // startActivityForResult(LoginActivity.getInstance().createSignInIntentBuilder().build(), RC_SIGN_IN);
            //Above is Mail only

        } else { //User is logged in
            mFirebaseUser = mFirebaseAuth.getCurrentUser();
            mUsername = mFirebaseUser.getDisplayName();
            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
          //  Log.d("MPR-TEST", "got passed the creation of the activity " + mFirebaseUser.getUid().toString());

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

            userDBRef = mDatabase.getReference("/Users/"+ mFirebaseUser.getUid().toString()  );

             // Read from the database
        userDBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
              //  String value = dataSnapshot.getValue(String.class);
             //  Log.d("MPR-TEST", "Top Level User Data changed);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("MPR-TEST", "Failed to read value.", error.toException());
            }

        });
            Log.d("MPR-TEST","/Users/"+mFirebaseUser.getUid().toString()+"/incompleteTasks");
            utasksDBRef = mDatabase.getReference("/Users/"+mFirebaseUser.getUid().toString()+"/incompleteTasks");

            utasksDBRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    taskData = dataSnapshot;
                    Log.d("MPR-TEST", "Task Data changed");
                    populateTaskList();
                    mTaskListView = (ListView) findViewById(R.id.mTaskListView);
                    if (MTaskList == null){
                        Log.d("MPR-MTASK", "MTASKLIST is NULL");
                    }

                    ArrayAdapter<MTask> adapter = new MTaskListArrayAdapter(MainActivity.this, MTaskList);

                    if (adapter == null){
                        Log.d("MPR-MTASK", "adapter is NULL");
                    }

                    mTaskListView.setAdapter(adapter);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("MPR-UTASK-CANCEL", "Failed to read value.", error.toException());
                }

            });




            projectsDBRef = mDatabase.getReference("/Users/"+mFirebaseUser.getUid().toString()+"/Projects");
            projectsDBRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    projectData = dataSnapshot;


                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("MPR-PROJECT-CANCEL", "Failed to read value.", error.toException());
                }

            });



            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {
                        // User is signed in
                        Log.d("MPR-AUTH-CHANGE", "onAuthStateChanged:signed_in:" + user.getUid());

                        // Authenticated successfully with authData
                       // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                       // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       // startActivity(intent);

                    } else {
                        // User is signed out
                        Log.d("MPR-AUTH-CHANGE", "onAuthStateChanged:signed_out");
                    }
                }
            };
/*
            mTaskListView = (ListView) findViewById(R.id.mTaskListView);
            ArrayAdapter adapter = new MTaskListArrayAdapter(this, MTaskList);
            mTaskListView.setAdapter(adapter); */

        }
/*

        */

    }
        @Override
        public void onStart() {
            super.onStart();
            mFirebaseAuth.addAuthStateListener(mAuthListener);
        }

        @Override
        public void onStop() {
            super.onStop();
            if (mAuthListener != null) {
                mFirebaseAuth.removeAuthStateListener(mAuthListener);
            }
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateTaskList() {
        MTaskList = new ArrayList<MTask>();

        for (DataSnapshot data : MainActivity.taskData.getChildren()) {
           // MTask myNewMTask = data.getValue(MTask.class);
            // Log.d("MPR-LIST-BUILD1",String.valueOf(data.getKey())+", ");

            //Log.d("MPR-LIST-BUILD",myNewMTask.getKey().toString());

          //  Log.d("MPR-LIST-BUILD2",data.getValue().toString());
            Map<String, String> map = (Map<String, String>) data.getValue();

            MTask myNewMTask = new MTask(data.getKey(),map);


            MTaskList.add(myNewMTask);
        }



    }
}
