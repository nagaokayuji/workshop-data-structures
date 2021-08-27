# Collection の便利な関数

知っておくと便利なメソッドをご紹介します。


## chunked

collection を 複数のリストに分割できます。

```kotlin
fun <T, R> Iterable<T>.chunked(
    size: Int,
    transform: (List<T>) -> R
): List<R>
```

`size` に指定した個数ごとに List に分割し、関数を適用できます。

<pre class="kt">
fun main() {
  val l = listOf(1,1,2,3,5,8,13,21,34,55)
  println(l.chunked(3)) // [[1, 1, 2], [3, 5, 8], [13, 21, 34], [55]]
}
</pre>




## reduce

畳み込み関数です。
順々に関数を適用していきます。

```kotlin
fun <T, R> Iterable<T>.fold(
    initial: R,
    operation: (acc: R, T) -> R
): R
```

例えば、次のようにすると簡単に階乗が計算できます。
<pre class="kt">
fun main() {
  println((1..10).reduce{a, b -> a * b}) // 9! = 362880
}
</pre>

また、`reduce` の 初期値ありバージョンが `fold` です。

これを利用すると以下のようにフィボナッチ数列の項も求められます。

<pre class="kt">
fun main() {
  println((3..10).fold(Pair(1,1)){(a,b), _ -> Pair(b, a+b)}.second) // 55
}
</pre>







