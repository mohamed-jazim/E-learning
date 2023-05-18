package com.kazimasum.videostreaming;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myviewholder extends RecyclerView.ViewHolder
{
    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer simpleExoPlayer;
    TextView vtitleview;
    ImageView like_btn;
    TextView like_text;
    ImageView comment_btn;
    DatabaseReference likereference;

    public myviewholder(@NonNull View itemView)
    {
        super(itemView);
        vtitleview=itemView.findViewById(R.id.vtitle);
        simpleExoPlayerView=itemView.findViewById(R.id.exoplayerview);

        like_btn=(ImageView)itemView.findViewById(R.id.like_btn);
        like_text=(TextView)itemView.findViewById(R.id.like_text);
        comment_btn=(ImageView)itemView.findViewById(R.id.comment_btn);
    }

    public void getlikebuttonstatus(final String postkey, final String userid)
    {
        likereference= FirebaseDatabase.getInstance().getReference("likes");
            likereference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if(snapshot.child(postkey).hasChild(userid))
                   {
                      int likecount=(int)snapshot.child(postkey).getChildrenCount();
                      like_text.setText(likecount+" likes");
                      like_btn.setImageResource(R.drawable.ic_baseline_favorite_24);
                   }
                   else
                   {
                       int likecount=(int)snapshot.child(postkey).getChildrenCount();
                       like_text.setText(likecount+" likes");
                       like_btn.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                   }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }


    void prepareexoplayer(Application application, String videotitle, String videourl)
    {
        try
        {
            vtitleview.setText(videotitle);
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            simpleExoPlayer =(SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(application,trackSelector);

            Uri videoURI = Uri.parse(videourl);

            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

            simpleExoPlayerView.setPlayer(simpleExoPlayer);
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(false);


        }catch (Exception ex)
        {
            Log.d("Explayer Creshed", ex.getMessage().toString());
        }
    }
}
