# Collection の便利な関数

知っておくと便利なメソッドをご紹介します。


## intersect()

重複する要素だけ取り出すことができます。


```kotlin
infix fun <T> Iterable<T>.intersect(
    other: Iterable<T>
): Set<T>
```

※ 戻り値が`Set`型であることに注意！

### 使用例

`list1`, `list2` の重複部分のみ取り出してみます。

<pre class="kt">
fun main() {
  val list1 = listOf(1,2,3,4,5,6,7,8,9)
  val list2 = listOf(1,4,7,9,11,13)
  println(list1.intersect(list2)) // [1, 4, 7, 9]
}
</pre>

※ `infix fun` として定義されているため、次のようにも書けます。

<pre class="kt">
fun main() {
  val list1 = listOf(1,2,3,4,5,6,7,8,9)
  val list2 = listOf(1,4,7,9,11,13)
  println(list1 intersect list2) // [1, 4, 7, 9]
}
</pre>

## reduce()

```kotlin
fun <S, T : S> Iterable<T>.reduce(
    operation: (acc: S, T) -> S
): S
```

幅広い用途に使える畳み込み関数です。

順々に関数を適用していくことができます。

### 使用例

例えば、次のようにすると簡単に階乗が計算できます。
<pre class="kt">
fun main() {
  println((1..10).reduce{a, b -> a * b}) // 9! = 362880
}
</pre>



## fold()


```kotlin
fun <T, R> Iterable<T>.fold(
    initial: R,
    operation: (acc: R, T) -> R
): R
```


また、`reduce` の 初期値ありバージョンが `fold` です。


## 使用例
例えば、以下のようにフィボナッチ数列の項が求められます。

<pre class="kt">
fun main() {
  println((3..10).fold(Pair(1,1)){(a,b), _ -> Pair(b, a+b)}.second) // 55
}
</pre>
