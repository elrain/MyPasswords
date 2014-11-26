package elrain.ua.mypasswords.util;

import android.os.Build;

public class StringXorUtil {
    private static final int A = 1231;
    private static final float B = 44.12F;

    public static String XorString(String input) {
        String secret = generateSecret();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++)
            sb.append((char) (input.charAt(i) ^ secret.charAt(i % secret.length())));
        return sb.toString();
    }

    private static String generateSecret() {
        return String.valueOf(A) + android.hardware.Camera.CAMERA_ERROR_SERVER_DIED + android.app.Activity.ACTIVITY_SERVICE + Build.MANUFACTURER + Build.SERIAL + Build.MODEL + B;
    }
}
