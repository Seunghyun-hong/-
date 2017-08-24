package hongseung.com.nbread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ScreenFragment.OnSendMessageListener {

    private ScreenFragment mFragment1;
    private String mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 액티비티에 프래그먼트 연결

        mFragment1 = new ScreenFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container1, mFragment1)
                .commit();
    }

    @Override
    public void onSendMessage(String message) {
        mMessage = message;
        mFragment1.sendMessage(mMessage);
    }


//    public void shareButtonClicked1(View view) {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setType("text/plaint");
//        intent.putExtra(Intent.EXTRA_TEXT, mMessage);
//
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }


    public void shareButtonClicked(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plaint");
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}

