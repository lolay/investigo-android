package com.lolay.android.tracker;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by darryl on 1/6/15.
 */
public class LolayGoogleAnalyticsTracker extends LolayBaseTracker{

    private static final String TAG = LolayGoogleAnalyticsTracker.class.getSimpleName();
    private String apiKey;
    private String platform;
    private Map<Object, Object> globalParametersValue;
    private static Tracker tracker = null;
    private static LolayGoogleAnalyticsTracker instance = null;

    public LolayGoogleAnalyticsTracker(Context context, String apiKey) {
        this.apiKey = apiKey;
        this.platform = clientPlatform();

        if (tracker == null)
        {
            tracker = GoogleAnalytics.getInstance(context).newTracker(apiKey);
        }

    }

    @Override
    public void setIdentifier(String identifier) {
        // You only need to set User ID on a tracker once. By setting it on the tracker, the ID will be
        // sent with all subsequent hits.
        tracker.set("&uid", identifier);
    }

    public void setGlobalParametersValue(Map<Object, Object> globalParametersValue) {
        this.globalParametersValue = globalParametersValue;
    }



    @Override
    public void logEvent(Context context, String name) {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("events")
                .setAction(name)
                .setLabel("label")
                .setValue(0)
                .build());
    }

    @Override
    public void logEventWithParams(Context context, String name, Map<Object, Object> parameters) {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("events")
                .setAction(name)
                .setLabel("label")
                .setValue(0)
                .build());
    }

    @Override
    public void logEventWithObjectsAndKeys(Context context, String name, Object... objectsAndKeys) {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("events")
                .setAction(name)
                .setLabel("label")
                .setValue(0)
                .build());
    }

    @Override
    public void logPage(Context context, String name) {
        tracker.setScreenName(name);
        tracker.send(new HitBuilders.AppViewBuilder().build());
    }

    @Override
    public void logPageWithParams(Context context, String name, Map<Object, Object> parameters) {
        tracker.setScreenName(name);
        tracker.send(new HitBuilders.AppViewBuilder().build());
    }

    @Override
    public void logPageWithObjectsAndKeys(Context context, String name, Object... objectsAndKeys) {
        tracker.setScreenName(name);
        tracker.send(new HitBuilders.AppViewBuilder().build());
    }

    @Override
    public void logException(Context context, Throwable throwable) {

// Build and send exception.
        tracker.send(new HitBuilders.ExceptionBuilder()
                .setDescription(throwable.getClass().getSimpleName() + ":" + throwable.getMessage())
                .setFatal(false)
                .build());
    }

    @Override
    public void logException(Context context, String errorId, String message, Throwable throwable) {

// Build and send exception.
        tracker.send(new HitBuilders.ExceptionBuilder()
                .setDescription(String.format("(%s): %s", errorId, message))
                .setFatal(false)
                .build());
    }

    @Override
    public void logTiming(Context context, Map<Object, Object> timingData) {

        if (!timingData.containsKey("category"))
            throw new IllegalArgumentException("logTiming requires category key");

        if (!timingData.containsKey("value"))
            throw new IllegalArgumentException("logTiming requires value key");

        if (!timingData.containsKey("name"))
            throw new IllegalArgumentException("logTiming requires name key");

        if (!timingData.containsKey("label"))
            throw new IllegalArgumentException("logTiming requires label key");

        tracker.send(new HitBuilders.TimingBuilder()
                .setCategory((String)timingData.get("category"))
                .setValue((Long)timingData.get("value"))
                .setVariable((String)timingData.get("name"))
                .setLabel((String)timingData.get("label"))
                .build());
    }

    @Override
    public void logPurchase(Context context, Map<Object, Object> purchaseData) {

        if (!purchaseData.containsKey("transactionId"))
            throw new IllegalArgumentException("logPurchase requires transactionId key");

        if (!purchaseData.containsKey("productName"))
            throw new IllegalArgumentException("logPurchase requires productName key");

        if (!purchaseData.containsKey("productId"))
            throw new IllegalArgumentException("logPurchase requires productId key");

        if (!purchaseData.containsKey("categoryName"))
            throw new IllegalArgumentException("logPurchase requires categoryName key");

        if (!purchaseData.containsKey("price"))
            throw new IllegalArgumentException("logPurchase requires price key");

        if (!purchaseData.containsKey("quantity"))
            throw new IllegalArgumentException("logPurchase requires quantity key");

        if (!purchaseData.containsKey("currency"))
            throw new IllegalArgumentException("logPurchase requires currency key");

        tracker.send(new HitBuilders.ItemBuilder()
                .setTransactionId((String)purchaseData.get("transactionId"))
                .setName((String)purchaseData.get("productName"))
                .setSku((String)purchaseData.get("productId"))
                .setCategory((String)purchaseData.get("categoryName"))
                .setPrice((Double) purchaseData.get("price"))
                .setQuantity((Long) purchaseData.get("quantity"))
                .setCurrencyCode((String) purchaseData.get("currency"))
                .build());

    }

    @Override
    public void logRegistration(Context context, Map<Object, Object> registrationData) {

        if (!registrationData.containsKey("userName"))
            throw new IllegalArgumentException("logRegistration requires userName key");

        String userName = registrationData.containsKey("userName") ?
                (String)registrationData.get("userName") : null;

        setIdentifier(userName);

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("UX")
                .setAction("User Register")
                .setLabel(null)
                .setValue(0)
                .build());
    }

    private String clientPlatform() {
        String manufacturer = Build.MANUFACTURER;
        String product = Build.PRODUCT;
        String model = Build.MODEL;
        String systemVersion = Build.VERSION.RELEASE;
        int sdk = Build.VERSION.SDK_INT;
        return String.format("%s %s (%s): %s %d", manufacturer, product, model, systemVersion, sdk);
    }

    @Override
    public void onStart(Activity activity){
        super.onStart(activity);
    }

    @Override
    public void onStop(Activity activity){
        super.onStop(activity);
    }
}
