package hongseung.com.nbread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements ScreenFragment.OnSendMessageListener {

    private ScreenFragment mFragment1;
    private String getMessage;
    // 광고를 달았다
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 광고를 달았다.
        MobileAds.initialize(this,"ca-app-pub-3008643822833798~5909809915");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);



        mFragment1 = new ScreenFragment();

        // R.id.container 영역에 프래그먼트를 붙이겠다.
        // 근데 굳이 동적으로 붙일 필요가... 없긴 한데..
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container1, mFragment1)
                .commit();

    }


    //여기서 메세지를 뽑아낼 수 있다.
    // 프래그먼트와 통신
    @Override
    public void onSendMessage(String message) {
        getMessage = message;
    }


    // 공유
    public void shareButtonClicked(View view) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plaint");
        sendIntent.putExtra(Intent.EXTRA_TEXT, getMessage);

        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }
}

