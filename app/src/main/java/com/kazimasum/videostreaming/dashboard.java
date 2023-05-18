package com.kazimasum.videostreaming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dashboard extends AppCompatActivity
{
   FloatingActionButton addvideo;
   RecyclerView recview;
   DatabaseReference likereference;
   Boolean testclick=false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle("VideoStreaming");



        likereference=FirebaseDatabase.getInstance().getReference("likes");

                addvideo=(FloatingActionButton)findViewById(R.id.addvideo);
                addvideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(),addvideo.class));
                    }
                });



        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<filemodel> options =
                new FirebaseRecyclerOptions.Builder<filemodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("myvideos"), filemodel.class)
                        .build();


         FirebaseRecyclerAdapter<filemodel,myviewholder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<filemodel, myviewholder>(options) {
             @Override
             protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull filemodel model) {
                 holder.prepareexoplayer(getApplication(),model.getTitle(),model.getVurl());

                 FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                 final String userid=firebaseUser.getUid();
                 final String postkey=getRef(position).getKey();

                      holder.getlikebuttonstatus(postkey,userid);

                         holder.like_btn.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                testclick=true;

                                 likereference.addValueEventListener(new ValueEventListener() {
                                     @Override
                                     public void onDataChange(@NonNull DataSnapshot snapshot) {
                                         if(testclick==true)
                                         {
                                           if(snapshot.child(postkey).hasChild(userid))
                                           {
                                             likereference.child(postkey).child(userid).removeValue();
                                             testclick=false;
                                           }
                                           else
                                           {
                                               likereference.child(postkey).child(userid).setValue(true);
                                               testclick=false;
                                           }

                                         }
                                     }

                                     @Override
                                     public void onCancelled(@NonNull DatabaseError error) {

                                     }
                                 });


                             }
                         });

                         holder.comment_btn.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                 Intent intent=new Intent(getApplicationContext(),commentpanel.class);
                                 intent.putExtra("postkey",postkey);
                                 startActivity(intent);
                             }
                         });

             }

             @NonNull
             @Override
             public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.singelrow,parent,false);
                return new myviewholder(view);
             }
         };

         firebaseRecyclerAdapter.startListening();
         recview.setAdapter(firebaseRecyclerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.appmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout: FirebaseAuth.getInstance().signOut();
                              startActivity(new Intent(dashboard.this,MainActivity.class));
                              finish();
                              break;

            case R.id.manage_profile:startActivity(new Intent(dashboard.this,update_profile.class));
                                     break;




        }
        return super.onOptionsItemSelected(item);
    }
}