package chemasmas.mx.uamchat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Landing extends AppCompatActivity {

    @BindView(R.id.fullscreen_content)
    View mContentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_landing);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.fullscreen_content)
    public void gotoLogin(TextView tv){
        Intent i = new Intent(Landing.this,LoginWIndow.class);
        startActivity(i);
        finish();
        //Toast.makeText(this, "Esto es Un Toast", Toast.LENGTH_LONG).show();
    }

}
