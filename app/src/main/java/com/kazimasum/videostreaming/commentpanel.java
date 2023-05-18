package com.kazimasum.videostreaming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class commentpanel extends AppCompatActivity
{
    EditText commenttext;
    Button commentsubmit;
    DatabaseReference userref, commentref;
    String postkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentpanel);

        postkey=getIntent().getStringExtra("postkey");

        userref= FirebaseDatabase.getInstance().getReference().child("userprofile");
        commentref=FirebaseDatabase.getInstance().getReference().child("myvideos").child(postkey).child("comments");

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final String userId=user.getUid();

        commenttext=(EditText)findViewById(R.id.comment_text);
        commentsubmit=(Button)findViewById(R.id.comment_submit);

                commentsubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                        public void onClick(View view) {
                            userref.child(userId).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                   if(snapshot.exists())
                                   {
                                       String username=snapshot.child("uname").getValue().toString();
                                       String uimage=snapshot.child("uimage").getValue().toString();
                                       processcomment(username,uimage);
                                   }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                    }

                    private void processcomment(String username, String uimage)
                    {
                       String commentpost=commenttext.getText().toString();
                       String randompostkey=userId+""+new Random().nextInt(1000);

                        Calendar datevalue=Calendar.getInstance();
                        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-mm-yy");
                        String cdate=dateFormat.format(datevalue.getTime());

                        SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm");
                        String ctime=timeFormat.format(datevalue.getTime());

                        HashMap cmnt=new HashMap();
                        cmnt.put("uid",userId);
                        cmnt.put("username",username);
                        cmnt.put("userimage",uimage);
                        cmnt.put("usermsg",commentpost);
                        cmnt.put("date",cdate);
                        cmnt.put("time",ctime);

                        commentref.child(randompostkey).updateChildren(cmnt)
                                .addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if(task.isSuccessful())
                                            Toast.makeText(getApplicationContext(),"Comment Added",Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(getApplicationContext(),task.toString(),Toast.LENGTH_LONG).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });


                    }
                });
    }
}