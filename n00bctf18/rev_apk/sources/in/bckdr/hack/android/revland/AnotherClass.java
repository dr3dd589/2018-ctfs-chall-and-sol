package in.bckdr.hack.android.revland;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AnotherClass extends AppCompatActivity {
    private static final String TAG = "AnotherClass";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_another_class);
        guessSecret();
    }

    private String MD5Hash(String in) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 240) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 15, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean checkForSecret(int secret) {
        String guessedFlag = new StringBuilder();
        guessedFlag.append("d0m_");
        guessedFlag.append(String.valueOf(secret));
        guessedFlag.append('}');
        return MD5Hash(guessedFlag.toString()).equals("1c661ac2b84593142f719f5a3e09f16d");
    }

    private void guessSecret() {
        for (int i = 0; i < 999999999; i++) {
            if (checkForSecret(i)) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Secret ingredIeNT for part for of flag is ");
                stringBuilder.append(String.valueOf(i));
                Log.d(str, stringBuilder.toString());
                str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("flagPartFour is: d0m_");
                stringBuilder.append(String.valueOf(i));
                stringBuilder.append("}");
                Log.d(str, stringBuilder.toString());
                return;
            }
        }
    }
}
