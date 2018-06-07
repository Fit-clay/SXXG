package student.eric.com.dialogutil;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Created by Eric on 2017/9/7.
 */

public class DialogUtil {
    private static LoadingDialog loadingDialog;


    public static LoadingDialog showLodingDialog(Context context, CharSequence cs) {
        if (context == null) {
            return null;
        }

        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        loadingDialog = new LoadingDialog(context);
        if (!loadingDialog.isShowing()) {
            loadingDialog.setTitle(cs);
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.show();
        }

        return loadingDialog;
    }



    public static void dismisLodingDialog() {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public static LoadingDialog showLodingTimginDialog(Context context, CharSequence cs, long time) {
        if (context == null) {
            return null;
        }

        final LoadingDialog dialog = new LoadingDialog(context);
        //if(null != cs){ dialog.setTitle(cs); }
        dialog.setTitle(cs);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
            }
        }, time);
        return dialog;
    }


    public interface DialogCallBack {
        void onCallBack(Dialog dialog);
    }

    public interface OnProgressDialogCallBack {
        void getProgressbar(ProgressBar progressBar);
    }

    public interface OnImageDialogCallBack {
        void isDelete(boolean flag);
    }

    public interface OnItemClick {
        void ItemClick(int position);
    }


    public static AlertDialog.Builder alert;


    /**
     * 列表弹出框
     * @param context
     * @param title
     * @param araString
     * @param itemClick
     */
    public static void showCommonListDialog(Context context, String title, String[] araString, final OnItemClick itemClick) {
        alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        //设置普通文本格式的对话框，设置的是普通的Item；
        alert.setItems(araString, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                itemClick.ItemClick(which);
            }
        });
        alert.show();
    }


    public static AlertDialog showConfirmDialog(Context context, String msg) {
        return showConfirmDialog(context, null, msg,-1, false, null,0,0,0,0,0);
    }

    public static AlertDialog showConfirmDialog(Context context, String titlemsg, CharSequence errorMsg, boolean showClose, final Object[][] objects) {
        return showConfirmDialog(context, titlemsg, errorMsg,-1, showClose, objects,0,0,0,0,0);
    }

    public static AlertDialog showConfirmDialog(Context context, String titlemsg, CharSequence errorMsg) {
        return showConfirmDialog(context, titlemsg, errorMsg,-1, false, null,0,0,0,0,0);
    }

    public static AlertDialog setBgBtnColorDialog(Context context, String titlemsg, CharSequence errorMsg,int gravity,
                                                  int bgColor,int leftBgColor,int rightBgColor,int leftTvColor,int rightTvColor, final Object[][] objects) {

        return showConfirmDialog(context, titlemsg, errorMsg,gravity, false, objects,bgColor,leftBgColor,rightBgColor,leftTvColor,rightTvColor);
    }

    public static AlertDialog showConfirmDialog(Context context, String titlemsg, CharSequence errorMsg,int gravity, boolean showClose, final Object[][] objects,
                                                int bgColor,int leftBgDraw,int rightBgDraw,int leftTvColor,int rightTvColor) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.layout_common_dialog);

//        window.setBackgroundDrawable(bgColor == 0 ? new ColorDrawable(Color.TRANSPARENT) : new ColorDrawable(bgColor));
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView title = (TextView) window.findViewById(R.id.tv_title);
        TextView messageView = (TextView) window.findViewById(R.id.tv_common_dialog_message);
        TextView mConcle = (TextView) window.findViewById(R.id.bt_record_permission_dialog_concle);
        TextView mConfirm = (TextView) window.findViewById(R.id.bt_common_dialog_confirm);
        ImageView btCancel = (ImageView) window.findViewById(R.id.bt_common_dialog_cancel);

        messageView.setText(errorMsg);
        messageView.setGravity(gravity == -1 ? Gravity.CENTER : gravity);
        btCancel.setVisibility(showClose ? View.VISIBLE : View.INVISIBLE);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        };
        if (!TextUtils.isEmpty(titlemsg)) {
            title.setVisibility(View.VISIBLE);
            title.setText(titlemsg);
        } else {
            title.setVisibility(View.GONE);
        }
        btCancel.setOnClickListener(listener);
        mConcle.setOnClickListener(listener);
        mConfirm.setOnClickListener(listener);
        mConcle.setVisibility(View.GONE);
        mConfirm.setVisibility(View.GONE);

//        mConcle.setVisibility(View.VISIBLE);
        if (null != objects) {
            if (objects.length >= 1) {
                mConcle.setText(objects[0].length == 0 ? "取消" : (objects[0][0] == null ? null : (String) objects[0][0]));
                mConcle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (objects[0].length >= 2 && objects[0][1] != null) {
                            ((DialogCallBack) objects[0][1]).onCallBack(alertDialog);
                        }
                    }
                });
                mConcle.setVisibility(View.VISIBLE);
                mConcle.setTextColor(leftTvColor == 0 ? context.getResources().getColor(R.color.base_blue):leftTvColor);
                if (leftBgDraw != 0) {
                    mConcle.setBackgroundResource(leftBgDraw);
                }

            }
            if (objects.length >= 2) {
                mConfirm.setText(objects[1].length == 0 ? "确定" : (objects[1][0] == null ? null : (String) objects[1][0]));
                if (objects[1].length >= 2 && objects[1][1] != null) {
                    mConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((DialogCallBack) objects[1][1]).onCallBack(alertDialog);

                        }
                    });
                }
                mConfirm.setVisibility(View.VISIBLE);
                mConfirm.setTextColor(rightTvColor == 0 ? context.getResources().getColor(R.color.base_blue):rightTvColor);
                if (rightBgDraw != 0) {
                    mConfirm.setBackgroundResource(rightBgDraw);
                }
            }
        } else {
            mConfirm.setVisibility(View.VISIBLE);
        }
        return alertDialog;
    }


}

