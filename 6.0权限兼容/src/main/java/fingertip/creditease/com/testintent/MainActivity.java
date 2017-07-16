package fingertip.creditease.com.testintent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private PermissionsChecker mPermissionsChecker; // 权限检测器
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[] {
            Manifest.permission.CALL_PHONE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPermissionsChecker = new PermissionsChecker(this);
    }


    public void ontest(View view) {
        if (Build.VERSION.SDK_INT>=23) {
            String[] permissions = PERMISSIONS;
            if (mPermissionsChecker.lacksPermissions(permissions)) {
                requestPermissions(permissions, PermissionsType.WRITE_EXTERNAL_STORAGE_CODE);
            } else {
                //需要权限才能操作的代码
                Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "15510030090");
                intent.setData(data);
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//
//                    return;
//                }
                startActivity(intent);
            }
        } else {
            //需要权限才能操作的代码
            Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + "15510030090");
            intent.setData(data);
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//
//                return;
//            }
            startActivity(intent);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionsType.WRITE_EXTERNAL_STORAGE_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];
                if (permission.equals(android.Manifest.permission.CALL_PHONE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        //需要权限才能操作的代码
                        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + "15510030090");
                        intent.setData(data);
//                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                            // TODO: Consider calling
//                            //    ActivityCompat#requestPermissions
//                            // here to request the missing permissions, and then overriding
//                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                            //                                          int[] grantResults)
//                            // to handle the case where the user grants the permission. See the documentation
//                            // for ActivityCompat#requestPermissions for more details.
//
//                            return;
//                        }
                        startActivity(intent);
//                        movieDetailIvCache.setSelected(true);
                    } else {
                        Toast.makeText(this,"no permission",Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
