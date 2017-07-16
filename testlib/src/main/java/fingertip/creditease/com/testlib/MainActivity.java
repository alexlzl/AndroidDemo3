package fingertip.creditease.com.testlib;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//import android.support.v7.app.AppCompatActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTest(View view ){
//        JSON json;
//       String string=JSONObject.quote("");
//        Intent intent=new Intent(this, HomeActivity.class);
//        startActivity(intent);
//        Intent intent=new Intent(this, com.wondersgroup.insurance.claimlib.HomeActivity.class);
//        intent.putExtra("access_token","342jksfjksf34");
//        intent.putExtra("user_id","12");
//        startActivity(intent);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        intent.putExtra("access_token","342jksfjksf34");
        intent.putExtra("user_id","12");
        intent.setComponent(new ComponentName("com.wondersgroup.insurance.claimlib",
                "com.wondersgroup.insurance.claimlib.HomeActivity"));
        startActivity(intent);

    }
}
