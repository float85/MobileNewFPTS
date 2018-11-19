package mobile.fpts.com.ezmibile.view.user.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ActivityLoginBinding;
import mobile.fpts.com.ezmibile.model.entity.check_update_ver.CheckUpdateVer;
import mobile.fpts.com.ezmibile.model.entity.check_update_ver.ICheckUpdate;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Language;
import mobile.fpts.com.ezmibile.util.SystemUtil;
import mobile.fpts.com.ezmibile.view.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements ILoginView{
    ActivityLoginBinding binding;
    boolean bEdUserName = true;
    private ProgressDialog m_progressShow = null;
    private ICheckUpdate msAccessor;
    private Runnable viewMarContractList;

    // TODO: TamHV 6/29/2018 set Typeface
    Typeface typeface = ResourcesCompat.getFont(App.getInstance(), R.font.free_sans);

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setupUI(findViewById(R.id.constraint));

        // TODO: TamHV 7/3/2018 Ch?nh s?a Filters
        presenter = new LoginPresenter(this::validateEdittext);
        presenter.validateEdittext();

        // TODO: TamHV 6/20/2018 checkupdate
        SharedPreferences preferences = getSharedPreferences(Define.SHARED_PREFRENCES_APP, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String language1 = preferences.getString(Define.SHARED_PREFRENCES_LANGUAGE, "vi");

        String userName = preferences.getString(Define.SHARED_PREFRENCES_USERNAME, "");
        String passWord = preferences.getString(Define.SHARED_PREFRENCES_PASSWORD, "");

        if (language1.equalsIgnoreCase("vi")) {
            Language.setLanguage();
            editor.putString(Define.SHARED_PREFRENCES_LANGUAGE, "vi");
            editor.commit();
        }

        String language = preferences.getString(Define.SHARED_PREFRENCES_LANGUAGE, "vi");
        if (language.equalsIgnoreCase("vi")) {
            binding.imgVNEN.setImageResource(R.drawable.image_language_en);
        } else {
            binding.imgVNEN.setImageResource(R.drawable.image_language_vn);
        }

        binding.edUserName.setTypeface(typeface);
        binding.edPassword.setTypeface(typeface);
        binding.checkbox.setTypeface(typeface);
        // TODO: TamHV 6/27/2018
        viewMarContractList = new Runnable() {
            @Override
            public void run() {
                if (checkUpdateVersion()) {
//                    Toast.makeText(LoginActivity.this, "C?n update", Toast.LENGTH_LONG).show();
                    handler2.sendEmptyMessage(0);
                } else {
                    if (!userName.equalsIgnoreCase("") && !passWord.equalsIgnoreCase("")) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                        finish();
                    }
                    m_progressShow.dismiss();
                }
            }
        };

        m_progressShow = ProgressDialog.show(LoginActivity.this, "",
                "Loading...Please Wait", true);
        Thread thread = new Thread(null, viewMarContractList,
                "RunOnBackground");
        thread.start();

        binding.imgVNEN.setOnClickListener(v -> {
            Language.setLanguage();
            LoginActivity.this.finish();
            LoginActivity.this.startActivity(new Intent(LoginActivity.this, LoginActivity.this.getClass()));
        });

        binding.edUserName.setOnClickListener(v -> {
            if (bEdUserName) {
                binding.edUserName.setText("058C");
                binding.edUserName.setSelection(4);
                bEdUserName = false;
            }
        });

        binding.btnLogin.setOnClickListener(v -> {

            String sUser = binding.edUserName.getText().toString();
            String sPassword = binding.edPassword.getText().toString();

            if (sUser.trim().equalsIgnoreCase("058C")) {
                binding.edUserName.setError(getResources().getString(R.string.error_login_user));
            } else if (!(sUser.trim().length() == 10)) {
                binding.edUserName.setError(getResources().getString(R.string.error_login_length));
            } else if (sPassword.trim().equalsIgnoreCase("")) {
                binding.edPassword.setError(getResources().getString(R.string.password_error));
            } else {
                if (binding.checkbox.isChecked()) {
                    editor.putString(Define.SHARED_PREFRENCES_USERNAME, sUser);
                    editor.putString(Define.SHARED_PREFRENCES_PASSWORD, sPassword);
                } else {
                    editor.putString(Define.SHARED_PREFRENCES_USERNAME, "");
                    editor.putString(Define.SHARED_PREFRENCES_PASSWORD, "");
                }

                editor.commit();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });

        //set link
        binding.txtForgotPassword.setMovementMethod(LinkMovementMethod.getInstance());
        binding.txtHelp.setMovementMethod(LinkMovementMethod.getInstance());
        binding.txtRegister.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            m_progressShow.dismiss();
            alertUpdateVer();
        }
    };

    private void alertUpdateVer() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this
        );
        alertDialogBuilder.setTitle(R.string.update_ver).setMessage(R.string.content_update_ver);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
                        Uri uri = Uri.parse("market://details?id=" + "vndungnd.com.fpts.mobile.activity");
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
//                            startActivity(new Intent(Intent.ACTION_VIEW,
//                                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + "vndungnd.com.fpts.mobile.activity")));
                        }

                        finish();
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private boolean checkUpdateVersion() {
        SystemUtil.getCurVersion(getApplicationContext());
        int ires = -1;
        try {
            if (msAccessor == null) {
                msAccessor = new CheckUpdateVer();
            }

            ires = msAccessor.CheckVersion(SystemUtil.mCurVersionName, Define.PARAM_PRODUCT_TYPE3);

            if (ires == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener((v, event) -> {
                hideSoftKeyboard(LoginActivity.this);
                return false;
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    @Override
    public void validateEdittext(InputFilter[] inputFilter) {
        binding.edUserName.setFilters(inputFilter);
        binding.edPassword.setFilters(inputFilter);
    }
}
