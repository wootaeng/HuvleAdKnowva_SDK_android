package com.huvle.huvleadlibsample;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.byappsoft.huvleadlib.ANClickThroughAction;
import com.byappsoft.huvleadlib.AdListener;
import com.byappsoft.huvleadlib.AdView;
import com.byappsoft.huvleadlib.BannerAdView;
import com.byappsoft.huvleadlib.NativeAdResponse;
import com.byappsoft.huvleadlib.NativeAdSDK;
import com.byappsoft.huvleadlib.ResultCode;
import com.byappsoft.huvleadlib.SDKSettings;

public class MainActivity extends AppCompatActivity {

    // TODO - Adknowva SDK Library
    private BannerAdView bav = null;
    // TODO - Adknowva SDK Library
    private NativeAdSDK nativ = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // TODO - Adknowva SDK Library
        setHuvleAD(); // 허블 광고 호출(Call Huvle's advertisement)
        // TODO - Adknowva SDK Library
    }

    // TODO - Adknowva SDK Library
    private void setHuvleAD() {
        /*
            정적 구현부와 동적구현부는 참고하시어 하나만 적용하시기 바랍니다.(With checking the implementation guide below, please apply Implementation either only Dynamic or Static.)
            initBannerView 아이디 "test" 값은 http://ssp.huvle.com/ 에서 가입 > 매체생성 > zoneid 입력후 테스트 하시고, release시점에 허블에 문의주시면 인증됩니다. 배너사이즈는 변경하지 마세요.(For the “test” value below, please go to http://ssp.huvle.com/ to sign up > create media > Test your app after typing zoneid. Next, contact Huvle before releasing your app for authentication. You must not change the banner size.)
        */
        // 정적으로 구현시(When if apply Dynamic Implementation) BannerAdView Start
        bav = findViewById(R.id.banner_view);
        initBannerView(bav, "test",320,50); // 300 * 250 배너 테스트시(Example for testing 300 * 250 banner)  initBannerView(staticBav, "testbig",300,250);

        // 동적으로 구현시(When if apply Static Implementation) BannerAdView Start
        /*
        final BannerAdView bav = new BannerAdView(this);
        initBannerView(bav, "test",320,50); // 300 * 250 배너 테스트시 initBannerView(staticBav, "testbig",300,250);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.main_content);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bav.setLayoutParams(layoutParams);
        layout.addView(bav);
        */
    }
    private void initBannerView(final BannerAdView bav, String id, int w , int h) {
        bav.setPlacementID(id);
        bav.setAdSize(w, h);
        bav.setShouldServePSAs(false);
        bav.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER); // 광고 클릭시 브라우저를 기본브라우저로 Open(Open the browser as the default browser when clicking on an advertisement)
//        bav.setClickThroughAction(ANClickThroughAction.OPEN_HUVLE_BROWSER); // 광고 클릭시 브라우저를 허블로 Open - 허블 SDK 연동한 업체인경우만(Open the browser as the Huvle browser when clicking on an advertisement(When if Huvle SDK is already integrated))
        bav.setResizeAdToFitContainer(true);
        AdListener adListener = new AdListener() {
            @Override public void onAdRequestFailed(AdView bav, ResultCode errorCode) {/*광고가 없을때 처리(Handle when there is no advertiment)*/}
            @Override public void onAdLoaded(AdView bav) {Log.v("Huvle_Banner", "The Ad Loaded!");}
            @Override public void onAdLoaded(NativeAdResponse nativeAdResponse) {Log.v("Huvle_Banner", "Ad onAdLoaded NativeAdResponse");}
            @Override public void onAdExpanded(AdView bav) {Log.v("Huvle_Banner", "Ad expanded");}
            @Override public void onAdCollapsed(AdView bav) {Log.v("Huvle_Banner", "Ad collapsed");}
            @Override public void onAdClicked(AdView bav) {Log.v("Huvle_Banner", "Ad clicked; opening browser");}
            @Override public void onAdClicked(AdView adView, String clickUrl) {Log.v("Huvle_Banner", "onAdClicked with click URL");}
            @Override public void onLazyAdLoaded(AdView adView) {}
        };

        bav.setAdListener(adListener);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bav.loadAd();
            }
        }, 0);

    }
    // TODO - Adknowva SDK Library




    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        bav.destroy();
        super.onDestroy();
    }
}

