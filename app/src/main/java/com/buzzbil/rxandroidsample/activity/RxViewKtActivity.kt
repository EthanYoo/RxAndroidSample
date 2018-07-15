package com.buzzbil.rxandroidsample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.buzzbil.rxandroidsample.R
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import io.reactivex.functions.BiFunction
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class RxViewKtActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_view)

        val clicks = RxView.clicks(find(R.id.rx_view_btn))
        val checkedChanges = RxCompoundButton.checkedChanges(find(R.id.rx_view_checkbox))

        clicks
                .withLatestFrom(
                        checkedChanges,
                        BiFunction<Any, Boolean, Boolean> { _, checked -> checked }
                )
                .filter { it }
                .subscribe { toast("result : $it") }
    }
}
