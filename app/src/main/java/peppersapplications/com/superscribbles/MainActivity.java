package peppersapplications.com.superscribbles;

import android.Manifest;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.Random;

import static android.graphics.Color.BLUE;


public class MainActivity extends AppCompatActivity {

    public static  myCanvas paint;
    private myVoiceReader voiceReader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        checkWritePermissions();
        checkReadPermissions();
        checkAudioPermissions();
        voiceReader = new myVoiceReader();
        voiceReader.startMySpeech(MainActivity.this);



        paint = findViewById(R.id.paint);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paint.init(metrics);


    }


    //Checks audio permissions
    public void checkAudioPermissions() {
        if (Manifest.permission.RECORD_AUDIO == null) {
            Toast.makeText(MainActivity.this,"Microphone Permission Not Enabled",Toast.LENGTH_LONG).show();
        }
    }

    //Checks write Permissions
    public void checkWritePermissions() {
        if (Manifest.permission.WRITE_EXTERNAL_STORAGE == null) {
            Toast.makeText(MainActivity.this,"WRITE Permission Not Enabled",Toast.LENGTH_LONG).show();
        }

    }

    //Check Read Permissions
    public void checkReadPermissions() {

            if (Manifest.permission.READ_EXTERNAL_STORAGE == null) {
                Toast.makeText(MainActivity.this,"READ Permission Not Enabled",Toast.LENGTH_LONG).show();
            }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void screenShot() {

        Random generator = new Random();
        int n = 1000000000;
        Bitmap bit;

        bit = paint.sendBitMap();
        n = generator.nextInt(n);
        String name = "ss-" + n + ".jpg";

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();


        File file = new File(myDir, name);
        if (file.exists()) file.delete();
        try {
            MediaStore.Images.Media.insertImage(getContentResolver(), bit, name, "Screenshot from Super Scribbles");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.erase) {
            Toast.makeText(getApplicationContext(), "Erased", Toast.LENGTH_SHORT).show();
            paint.erase();
        }

        if (id == R.id.screenShot) {
            Toast.makeText(getApplicationContext(), "Scribble Added to Gallery", Toast.LENGTH_LONG).show();
            screenShot();

        }
        if(id == R.id.microphone){
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
            voiceReader.mySpeech.startListening(intent);

        }

        if(id == R.id.help){

            Intent myIntent = new Intent(getBaseContext(),helpMenu.class);
            startActivity(myIntent);



        }


        return super.onOptionsItemSelected(item);
    }
}
