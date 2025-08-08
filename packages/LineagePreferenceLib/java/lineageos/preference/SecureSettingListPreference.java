package lineageos.preference;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.preference.ListPreference;

public class SecureSettingListPreference extends ListPreference {

    private SecureSettingsStore dataStore;
    private boolean isAutoSummary = false;

    public SecureSettingListPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public SecureSettingListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SecureSettingListPreference(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        dataStore = new SecureSettingsStore(context.getContentResolver());
        setPreferenceDataStore(dataStore);
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
        if (isAutoSummary || TextUtils.isEmpty(getSummary())) {
            setSummary(getEntry(), true);
        }
    }

    @Override
    public void setSummary(CharSequence summary) {
        setSummary(summary, false);
    }

    private void setSummary(CharSequence summary, boolean autoSummary) {
        isAutoSummary = autoSummary;
        super.setSummary(summary);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        String value = restorePersistedValue
                ? getPersistedString(defaultValue != null ? (String) defaultValue : null)
                : (String) defaultValue;
        setValue(value);
    }

    public int getIntValue(int defaultValue) {
        String value = getValue();
        if (value == null) return defaultValue;
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
