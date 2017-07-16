package com.wondersgroup.fingertipclaim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wondersgroup.insurance.claimlib.HomeActivity;

public class MainActivity extends AppCompatActivity {

    EditText et_accesstoken, et_userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_accesstoken = (EditText) findViewById(R.id.et_accesstoken);
        et_userid = (EditText) findViewById(R.id.et_userid);
        et_accesstoken.setText("689842732eb84f018ec356591e941c32");
        et_userid.setText("123sbc");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accesstoken = et_accesstoken.getText().toString().trim();
                String userid = et_userid.getText().toString().trim();
                Bundle data = new Bundle();
                data.putString("access_token", accesstoken);
                data.putString("user_id", userid);
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                intent.putExtras(data);
                startActivityForResult(intent,100);

//                StartActivityUtil.startActivityForResult(HomeActivity.class,100, data,MainActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 100:
                if (resultCode !=1){
                    if (data != null){
                       String err_msg = data.getStringExtra("err_msg");
                        Toast.makeText(MainActivity.this, err_msg,Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}
