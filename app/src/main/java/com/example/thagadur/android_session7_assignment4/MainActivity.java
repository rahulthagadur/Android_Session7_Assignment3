package com.example.thagadur.android_session7_assignment4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button chooseImage;
    ImageView image;
    Uri imageUri;
    private static final int PICK_IMAGE=100;

    Button camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialise with the Id
        chooseImage=(Button)findViewById(R.id.buttonChooseImage);
        image=(ImageView)findViewById(R.id.imageView);
        //set onclock listner for button to open galler
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryPick();
            }
        });

    }
    //Function to open a gallery application using Intents
    private void galleryPick(){
        Intent gallery=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

    //To retieve the values of Imgae receved from the gallery we use onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            if (resultCode==RESULT_OK && requestCode==PICK_IMAGE){
                //getting the image data
                imageUri=data.getData();
                //setting image on image View
                image.setImageURI(imageUri);
            }
            //if we didnt selecet any message display a toast message
            else if (resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Action Stopped in the Middle", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
