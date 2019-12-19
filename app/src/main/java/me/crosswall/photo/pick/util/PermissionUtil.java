package me.crosswall.photo.pick.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by yuweichen on 15/12/10.
 */
public class PermissionUtil {

    public static final int REQUEST_CODE_ASK_PERMISSIONS = 100;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_CAMERA = 10;
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_WRITE_EXTERNAL_STORAGE = 20;

    public static boolean checkPermission(Activity activity, String permission){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int storagePermission = ActivityCompat.checkSelfPermission(activity, permission);
            if (storagePermission != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    public static void showPermissionDialog(final Activity activity,String permission) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity,permission)) {

            ActivityCompat.requestPermissions(activity, new String[]{permission},
                    PermissionUtil.REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }

        ActivityCompat.requestPermissions(activity,new String[]{permission},
                PermissionUtil.REQUEST_CODE_ASK_PERMISSIONS);

    }


    public static void showAppSettingDialog(final Activity activity){
        new AlertDialog.Builder(activity)
                .setMessage("In order to run on Android M, your authorization is needed")
                .setNegativeButton("Cancel",null)
                .setPositiveButton("Ok",null)
                .show();
      /*  Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, PermissionUtil.REQUEST_PERMISSION_SETTING);*/
    }

}
