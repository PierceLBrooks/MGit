package me.sheimi.sgit;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import org.acra.ACRA;
import org.eclipse.jgit.transport.CredentialsProvider;

import me.sheimi.android.utils.SecurePrefsException;
import me.sheimi.android.utils.SecurePrefsHelper;
import me.sheimi.sgit.preference.PreferenceHelper;
import timber.log.Timber;


public abstract class SGitApplication extends Application {

    private static Context mContext;
    private static CredentialsProvider mCredentialsProvider;

    private SecurePrefsHelper mSecPrefs;
    private PreferenceHelper mPrefsHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        setAppVersionPref();
        mPrefsHelper = new PreferenceHelper(this);
        try {
            mSecPrefs = new SecurePrefsHelper(this);
            mCredentialsProvider = new AndroidJschCredentialsProvider(mSecPrefs);
        } catch (SecurePrefsException e) {
            Timber.e(e);
        }
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // The following line triggers the initialization of ACRA
        if (!BuildConfig.DEBUG) {
            ACRA.init(this);
        }
    }


    public SecurePrefsHelper getSecurePrefsHelper() {
        return mSecPrefs;
    }

    public PreferenceHelper getPrefenceHelper() {
        return mPrefsHelper;
    }

    public static Context getContext() {
        return mContext;
    }

    private void setAppVersionPref() {
        SharedPreferences sharedPreference = getSharedPreferences(
                getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);
        String version = BuildConfig.VERSION_NAME;
        sharedPreference
            .edit()
            .putString(getString(R.string.preference_key_app_version), version)
            .apply();
    }

    public static CredentialsProvider getJschCredentialsProvider() {
        return mCredentialsProvider;
    }
}
