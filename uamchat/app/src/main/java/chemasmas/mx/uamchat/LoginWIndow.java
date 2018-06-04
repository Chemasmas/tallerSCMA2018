package chemasmas.mx.uamchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

public class LoginWIndow extends AppCompatActivity {

    CallbackManager callbackManager;
    public static final String TAG = "LOGIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_window);


        //CallbackManager
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken at = loginResult.getAccessToken();
                //String user = at.getUserId();
                //String token = at.getToken();

            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginWIndow.this,"Se cancelo el Login",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG,error.getMessage());
                Toast.makeText(LoginWIndow.this,"Error en el login",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
