package com.videorecording.delarakiani.videorecording;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoRecordingMainActivity extends AppCompatActivity {

    private Button mRecordView, mPlayView;
    private VideoView mVideoView;
    private int ACTIVITY_START_CAMERA_APP = 0;


    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_recording_main);

        mRecordView = (Button) findViewById(R.id.recordButton);
        mPlayView = (Button) findViewById(R.id.playButton);
        mVideoView = (VideoView) findViewById(R.id.videoView);

        mRecordView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent callVideoAppIntel = new Intent();
                callVideoAppIntel.setAction(MediaStore.ACTION_VIDEO_CAPTURE);

                startActivityForResult(callVideoAppIntel, ACTIVITY_START_CAMERA_APP);
            }
        });

        mPlayView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               mVideoView.start();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            mVideoView.setVideoURI(videoUri);
            // the location of video captured

        }
    }




    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
