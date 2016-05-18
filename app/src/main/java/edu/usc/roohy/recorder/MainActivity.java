package edu.usc.roohy.recorder;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String mFileName = null;
    MediaRecorder mRecorder = null;
    MediaPlayer mPlayer = null;
    boolean isRecording = false;
    boolean isPlaying = false;

    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/audiorecordtest";

        //Sensor stuff
        //mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        //end of sensor stuff

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button recordButton = (Button) findViewById(R.id.recordButton);
        recordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(isRecording){
                    stopRecording();
                }
                else{
                    Toast recordToast = Toast.makeText(getApplicationContext(), "Recording Started", Toast.LENGTH_SHORT);
                    recordToast.show();
                    startRecording();
                }
                isRecording = !isRecording;
            }
        });

        Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(isPlaying){
                    stopPlaying();
                }
                else{
                    startPlaying();
                    Toast playToast = Toast.makeText(getApplicationContext(), "Playing started", Toast.LENGTH_SHORT);
                    playToast.show();
                }
                isPlaying = !isPlaying;
            }
        });
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
    private void startRecording(){
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setAudioSamplingRate(96000);

        try{
            mRecorder.prepare();
        }catch (IOException e){
            Log.e("ROOHY","Preparing Recorder Failed");
        }

        mRecorder.start();

    }

    private void stopRecording(){
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    private void startPlaying(){
        mPlayer = new MediaPlayer();
        try{
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        }catch( IOException e){
            Log.e("ROOOHY","Preparing player failed!!!!");
        }
    }

    private void stopPlaying(){
        mPlayer.release();
        mPlayer = null;
    }

}
