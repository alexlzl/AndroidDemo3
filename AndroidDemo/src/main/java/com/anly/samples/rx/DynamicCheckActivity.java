package com.anly.samples.rx;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.anly.samples.R;
import com.anly.samples.base.TraceActivity;
import com.anly.samples.util.NetworkUtil;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.net.NoRouteToHostException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;

public class DynamicCheckActivity extends TraceActivity {

    @BindView(R.id.username_input)
    EditText mUsernameInput;
    @BindView(R.id.username_input_layout)
    TextInputLayout mUsernameInputLayout;
    @BindView(R.id.loading)
    ProgressBar mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_check);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        RxTextView.textChanges(mUsernameInput)
                .subscribeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        Log.d("mingjun", "textChanges filter value:" + charSequence);
                        return charSequence.length() > 1;
                    }
                })
                .debounce(800, TimeUnit.MILLISECONDS)
                .switchMap(new Func1<CharSequence, Observable<String>>() {
                    @Override
                    public Observable<String> call(CharSequence text) {
                        String username = text.toString();
                        return checkUsername(username)
                                .doOnSubscribe(new Action0() {
                                    @Override
                                    public void call() {
                                        Log.d("mingjun", "checkUsername doOnSubscribe");
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                mLoadingBar.setVisibility(View.VISIBLE);
                                            }
                                        });
                                    }
                                })
                                .onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
                                    @Override
                                    public Observable<? extends String> call(final Throwable throwable) {
                                        Log.d("mingjun", "checkUsername onErrorResumeNext");
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                mLoadingBar.setVisibility(View.GONE);
                                                mUsernameInputLayout.setError(throwable.getMessage());
                                            }
                                        });
                                        return Observable.empty();
                                    }
                                });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call(Throwable throwable) {
                        Log.d("mingjun", "outer onErrorResumeNext");
                        return Observable.empty();
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d("mingjun", "outer onCompleted");
                        mLoadingBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("mingjun", "outer onError:" + e);
                        mLoadingBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("mingjun", "outer onNext: s:" + s);
                        mLoadingBar.setVisibility(View.GONE);
                        mUsernameInputLayout.setError(s);
                    }
                });
    }

    // 模拟一个检测用户名的接口, 一般来说, 更多的是调用服务器端Api检测是否有敏感词, 是否已存在等等.
    private Observable<String> checkUsername(final String username) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (NetworkUtil.isNetworkAvailable(getApplicationContext())) {
                    subscriber.onError(new NoRouteToHostException("network error"));
                } else {
                    if (username.toLowerCase().contains("jj")
                            || username.toLowerCase().contains("sb")) {
                        subscriber.onNext("用户名包含敏感词...");
                    } else if ("anly".equalsIgnoreCase(username)) {
                        subscriber.onNext("用户已存在");
                    }
                }
            }
        });
    }


}
