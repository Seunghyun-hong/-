package hongseung.com.nbread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ScreenFragment.OnSendMessageListener {

    private ScreenFragment mFragment1;

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

    // 메시지를 뿌린다.
    @Override
    public void onSendMessage(String message) {
        mFragment1.sendMessage(message);
    }

    // 이건 모든앱?! 에서 된다!
    public void shareButtonClicked(View view) {
        String message = mFragment1.shareMessage();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}

