package <%= appPackage %>.base.util

fun notBlank(vararg strings: String): Boolean {
  return strings.all {
    it.isNotBlank()
  }
}