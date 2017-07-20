package com.grayherring.devtalks.base.util

import android.widget.EditText
import android.widget.TextView

fun TextView.tentativelyUpdate(string: String) {
  if (this.text != string) {
    this.text = string
  }
}

fun EditText.tentativelyUpdate(string: String) {
  if (this.text.toString() != string) {
    this.setText(string)
  }
}
