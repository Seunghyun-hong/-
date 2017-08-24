package hongseung.com.nbread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

    // 이건 카톡만 되더라고.
//    public void shareButtonClicked(View view) {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setType("text/plaint");
//        intent.putExtra(Intent.EXTRA_TEXT, mMessage);
//
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }

    // 이건 안되는거!!
    //    public void shareButtonClicked(String message) {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setType("text/plaint");
//        intent.putExtra(Intent.EXTRA_TEXT, message);
//
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }

    // 이건 모든앱?! 에서 된다!
    public void shareButtonClicked(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        Intent chooser = Intent.createChooser(intent, "공유");

        startActivity(chooser);
    }


}

