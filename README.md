# HuvleAdKnowva_SDK_android


## 제휴 신청
허블 애드노바(AdKnowva) SDK 제휴 방법은 https://www.huvleview.com/doc/contact.php 에 절차를 안내 드리고 있습니다.


### 적용가이드
- Usages 를 참고하시거나 아래 샘플 프로젝트를 참고해주세요.
- [샘플 프로젝트 다운로드] :http://api.huvleview.com/ko/downloads/HuvleAdLibSample.zip


## Usages
### 1. Manifest
- networkSecurityConfig 추가(Android 10(API 레벨 29) 이상을 타켓팅하는 경우 requestLegacyExternalStorage추가)
```
<uses-permission android:name="android.permission.INTERNET" />

<application
	.
	.
	android:requestLegacyExternalStorage="true"
	android:networkSecurityConfig="@xml/network"
	tools:replace="android:networkSecurityConfig"
	.
	.
	
```

### 2. SDK 추가
Huvle Adknowva SDK 를 사용하기 위해서는 gradle에 SDK를 포함한 하위 라이브러리들을 추가해야합니다.
- build.gradle(Project)
```
allprojects {
    repositories {
        google()
        jcenter()
        maven {
            name "Huvle"
            url "https://sdk.huvle.com/repository/internal"
        }
    }
}
```

- build.gradle(app)
```
android {
    ...
    defaultConfig {
        ...
        multiDexEnabled true
    }
}

dependencies {
	.
	.
	/**
	* adknowva sdk , play-service-ads 
	*/
	implementation 'com.google.android.gms:play-services-ads:20.4.0'
	implementation 'com.byappsoft.huvleadlib:HuvleAdLib:1.2.2' // Please implement after checking the latest version.
	.
	.
}
```

### 3. 앱에 적용하기
- 광고가 적용될 Activity
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate( savedInstanceState );
  setContentView( R.layout.activity_main );

  setHuvleAD(); // 허블 광고 호출
}

private void setHuvleAD() {
  final BannerAdView staticBav = findViewById(R.id.banner_view);
  // 아래 "test" 값은 http://ssp.huvle.com/ 에서 가입 > 매체생성 > zoneid 입력후 테스트 하시고, release시점에 허블에 문의주시면 인증됩니다. 배너사이즈는 변경하지 마세요.
  initBannerView(staticBav, "test",320,50);
}
private void initBannerView(final BannerAdView bav, String id, int w , int h) {
  bav.setPlacementID(id);
  bav.setAdSize(w, h);
  bav.setShouldServePSAs(false);
  bav.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER); // 광고 클릭시 브라우저를 기본브라우저로 Open
  bav.setResizeAdToFitContainer(true);
  AdListener adListener = new AdListener() {
    @Override public void onAdRequestFailed(AdView bav, ResultCode errorCode) {/*광고가 없을때 처리*/}
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
    @Override public void run() {
      bav.loadAd();
    }
  }, 0);
}
```




## License
Huvle AdKnowva SDK 의 저작권은 (주)허블에 있습니다.
```
Huvle AdKnowva SDK Android
Copyright 2021-present Huvle Corp.

Unauthorized use, modification and redistribution of this software are strongly prohibited.
```

