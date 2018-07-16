package com.buzzbil.rxandroidsample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.buzzbil.rxandroidsample.R;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class RxViewActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_view);

        Button button = findViewById(R.id.rx_view_btn);
        Observable<Object> clickObs = RxView.clicks(button);
        CheckBox checkBox = findViewById(R.id.rx_view_checkbox);
        Observable<Boolean> checkedTrueObs = RxCompoundButton.checkedChanges(checkBox)
                .skipInitialValue();

        Disposable disposable = clickObs
                // 클릭 스트림에 체크 스트림의 마지막 값을 조합해서 새로운 스트림을 만든다.
                .withLatestFrom(checkedTrueObs, new BiFunction<Object, Boolean, Boolean>() {
                    @Override
                    public Boolean apply(Object o, Boolean checked) throws Exception {
                        // 새로운 스트림의 데이터는 체크 상태인지 아닌지를 나타낸다.
                        return checked;
                    }
                })
                // 체크 상태인 경우만 필터링 한다.
                .filter(new Predicate<Boolean>() {
                    @Override
                    public boolean test(Boolean aBoolean) throws Exception {
                        return aBoolean == true;
                    }
                })
                // 여기까지 오면 클릭 시점에 마지막 체크 상태가 true 인 경우인 스트림을 나타낸다.
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Toast.makeText(RxViewActivity.this, "result : " + o, Toast.LENGTH_SHORT).show();
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
