# 基本的な関数

Collection のよく使われる関数としては以下のようなものがあります。

## `contains()`

```kotlin
abstract operator fun contains(
    element: @UnsafeVariance E
): Boolean
```

ある要素を含むかどうかを返します。
存在判定を簡潔に書けるため重宝します。

<pre class="kt">
fun main() {
  val l = listOf(1,1,2)
  println(l.contains(1)) // true
}
</pre>

## `map()`

```kotlin
inline fun <T, R> Iterable<T>.map(
    transform: (T) -> R
): List<R>
```

[高階関数](https://ja.wikipedia.org/wiki/%E9%AB%98%E9%9A%8E%E9%96%A2%E6%95%B0) (関数を引数に取る)の一種です。

各要素に対して、与えられた関数を順番に処理していきます。

例えば、各要素を二乗する場合は次のようになります。

<pre class="kt">
fun main() {
  val l = listOf(1,1,2)
  println(l.map{it * it}) // [1, 1, 4]
}
</pre>

## `filter()`
```kotlin
fun <T> Iterable<T>.filter(
    predicate: (T) -> Boolean
): List<T>
```

条件に合致する（渡した関数での評価結果が真となる）要素のみ残します。

<pre class="kt">
fun main() {
  val l = listOf(1,1,2)
  println(l.filter{it == 1}) // [1, 1]
}
</pre>
