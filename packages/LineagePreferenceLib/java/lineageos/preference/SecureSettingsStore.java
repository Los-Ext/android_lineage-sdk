package lineageos.preference;

import android.content.ContentResolver;
import android.os.UserHandle;
import androidx.preference.PreferenceDataStore;
import android.provider.Settings;

public class SecureSettingsStore extends PreferenceDataStore {

    private final ContentResolver contentResolver;
    private static final int USER = UserHandle.USER_CURRENT;

    public SecureSettingsStore(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    private int getIntSecure(String key, int defValue) {
        return Settings.Secure.getIntForUser(contentResolver, key, defValue, USER);
    }

    private long getLongSecure(String key, long defValue) {
        return Settings.Secure.getLongForUser(contentResolver, key, defValue, USER);
    }

    private float getFloatSecure(String key, float defValue) {
        return Settings.Secure.getFloatForUser(contentResolver, key, defValue, USER);
    }

    private String getStringSecure(String key, String defValue) {
        String value = Settings.Secure.getStringForUser(contentResolver, key, USER);
        return value != null ? value : defValue;
    }

    private void putIntSecure(String key, int value) {
        Settings.Secure.putIntForUser(contentResolver, key, value, USER);
    }

    private void putLongSecure(String key, long value) {
        Settings.Secure.putLongForUser(contentResolver, key, value, USER);
    }

    private void putFloatSecure(String key, float value) {
        Settings.Secure.putFloatForUser(contentResolver, key, value, USER);
    }

    private void putStringSecure(String key, String value) {
        Settings.Secure.putStringForUser(contentResolver, key, value, USER);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return getIntSecure(key, defValue ? 1 : 0) != 0;
    }

    public void putBoolean(String key, boolean value) {
        putIntSecure(key, value ? 1 : 0);
    }

    public int getInt(String key, int defValue) {
        return getIntSecure(key, defValue);
    }

    public void putInt(String key, int value) {
        putIntSecure(key, value);
    }

    public long getLong(String key, long defValue) {
        return getLongSecure(key, defValue);
    }

    public void putLong(String key, long value) {
        putLongSecure(key, value);
    }

    public float getFloat(String key, float defValue) {
        return getFloatSecure(key, defValue);
    }

    public void putFloat(String key, float value) {
        putFloatSecure(key, value);
    }

    public String getString(String key, String defValue) {
        return getStringSecure(key, defValue);
    }

    public void putString(String key, String value) {
        putStringSecure(key, value);
    }
}
