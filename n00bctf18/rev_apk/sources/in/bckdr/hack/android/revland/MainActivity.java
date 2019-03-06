package in.bckdr.hack.android.revland;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        Log.d(TAG, "There are 4 parts of the flag. All of equal length !!!");
        Log.d(TAG, "flag format: n00bCTF{secret_flag_here}");
    }

    private String flagPartOne(int key) {
        Log.d(TAG, "Oops I forgot the key");
        String flagPartOne = "";
        for (int t : new int[]{68, 26, 26, 72, 105, 126, 108, 81, 88, 79, 92, 79, 88, 89}) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(flagPartOne);
            stringBuilder.append((char) (key ^ t));
            flagPartOne = stringBuilder.toString();
        }
        return flagPartOne;
    }
}
