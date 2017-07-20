package com.grayherring.devtalks.base.util

fun notBlank(vararg strings: String): Boolean {
  return strings.all {
    it.isNotBlank()
  }
}