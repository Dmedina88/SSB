package <%= appPackage %>.base.util

import android.widget.EditText
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable

/**
 * Created by davidmedina on 6/29/17.
 */

fun EditText.TextChangeSkipInit(): Observable<String> =
    this.textChanges().skipInitialValue().map { it.toString() }