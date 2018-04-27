package csed.edu.alexu.eg.virtualbookshelf.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import android.content.Intent;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


import csed.edu.alexu.eg.virtualbookshelf.utility.EditFactory;
import csed.edu.alexu.eg.virtualbookshelf.R;

public class LoginActivity extends AppCompatActivity {
    private static int RC_SIGN_IN = 100;
    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private Button buttonAdd;
    private GoogleSignInAccount account;
    private EditFactory factory;

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("requestCode is ", requestCode+"");
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Log.d("Soso ", "123");
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        Log.d("Soso ", "12345");
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            Log.d("Soso", "fi awl elfunction");
            account = completedTask.getResult(ApiException.class);
            Log.d("Soso", "3dina elerror");

            Log.d("Soso Email is ", account.getEmail()); // Debug
            Log.d("Soso Token is ", account.getIdToken()); // Debug
            Log.d("Soso Id is ", account.getId()); // Debug
            Log.d("Soso", "w ry7n 3la eloop");

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("Soso", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    protected  void updateUI(GoogleSignInAccount account){
        Log.d("Soso", "UI updated");
        EditFactory.init(LoginActivity.this, account.getAccount());
        factory = EditFactory.getInstance();
        //TODO move to home activity.
       /* Intent myIntent = new Intent(this, HomeActivity.class);
        startActivity(myIntent);*/
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("840899473814-ggog8u302tcsri4otg29424dp420omi2.apps.googleusercontent.com")
                .requestScopes(new Scope("https://www.googleapis.com/auth/books"))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton = findViewById(R.id.signInButton);
        buttonAdd = findViewById(R.id.buttonAdd);
        Log.d("Soso", signInButton.toString());
        findViewById(R.id.signInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Soso", "hahahaha");
                signIn();
            }
        });
        findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    
}
