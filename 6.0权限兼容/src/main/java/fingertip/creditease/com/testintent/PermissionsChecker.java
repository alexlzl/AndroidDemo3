package fingertip.creditease.com.testintent;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2016/12/25 下午11:43
 */

public class PermissionsChecker {
    private final Context mContext;
    public PermissionsChecker(Context context) {
        mContext = context.getApplicationContext();
    }
    // 判断权限集合
    public boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }
    // 判断是否缺少权限
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission)
                == PackageManager.PERMISSION_DENIED;
    }
}
