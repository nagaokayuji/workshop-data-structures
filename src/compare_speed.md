# 遅い例と速い例

### Kotlin のメソッドについて

Kotlin の Collection インタフェースでは便利なメソッドがいくつか用意されています。

[Collection - Kotlin Programming Language](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/#kotlin.collections.Collection)

これらのメソッドは気軽に使えますが、内部でどのような処理が走っているのかは気に留めるようにすると良いと思います。

例えば、 Kotlin の List  で `contains` メソッドを使用して存在確認をすることがあると思います。 

この操作自体は **値の検索** と同等の計算量が必要となるため、平均で$O(N)$ の計算量となります。

同様に、HashSet を使用すると値の検索は平均で $O(1)$ になるため、 `contains` メソッドを使用しても $O(1)$ で実行することができます。

確認してみましょう。

次のコードはそれぞれサイズN, サイズM のリスト中に何個の重複があるかを確認するためのものです。

```kotlin
const val N:Int = 100000
const val M:Int = 100000
fun main() {
  val originalList = (0..1000000).toList()
  // サイズNのリスト
  val list1 = originalList.shuffled().take(N)
  // サイズMのリスト
  val list2 = originalList.shuffled().take(M)

  val startTime = System.currentTimeMillis()
  
  // 問題の処理
  val countInList2 = list1.filter{
    list2.contains(it)
  }.size

  val endTime = System.currentTimeMillis()

  println("\${countInList2} 件見つかりました。")
  println("[実行時間] \${endTime - startTime} ms")

}
```


このコードを実行できるリンクを貼っておきます。

タイムアウトが発生してしまう場合は N や M の値を少し小さくしてみてください。

[Playground](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS41LjIxIiwicGxhdGZvcm0iOiJqYXZhIiwiYXJncyI6IiIsIm5vbmVNYXJrZXJzIjp0cnVlLCJ0aGVtZSI6ImlkZWEiLCJjb2RlIjoiY29uc3QgdmFsIE46SW50ID0gMTAwMDAwXG5jb25zdCB2YWwgTTpJbnQgPSAxMDAwMDBcbmZ1biBtYWluKCkge1xuICB2YWwgb3JpZ2luYWxMaXN0ID0gKDAuLjEwMDAwMDApLnRvTGlzdCgpXG4gIC8vIOOCteOCpOOCuk7jga7jg6rjgrnjg4hcbiAgdmFsIGxpc3QxID0gb3JpZ2luYWxMaXN0LnNodWZmbGVkKCkudGFrZShOKVxuICAvLyDjgrXjgqTjgrpN44Gu44Oq44K544OIXG4gIHZhbCBsaXN0MiA9IG9yaWdpbmFsTGlzdC5zaHVmZmxlZCgpLnRha2UoTSlcblxuXG4gIHZhbCBzdGFydFRpbWUgPSBTeXN0ZW0uY3VycmVudFRpbWVNaWxsaXMoKVxuICBcbiAgLy8g5ZWP6aGM44Gu5Yem55CGXG4gIHZhbCBjb3VudEluTGlzdDIgPSBsaXN0MS5maWx0ZXJ7XG4gICAgbGlzdDIuY29udGFpbnMoaXQpXG4gIH0uc2l6ZVxuXG4gIHZhbCBlbmRUaW1lID0gU3lzdGVtLmN1cnJlbnRUaW1lTWlsbGlzKClcblxuICBwcmludGxuKFwiJHtjb3VudEluTGlzdDJ9IOS7tuimi+OBpOOBi+OCiuOBvuOBl+OBn+OAglwiKVxuICBwcmludGxuKFwiW+Wun+ihjOaZgumWk10gJHtlbmRUaW1lIC0gc3RhcnRUaW1lfSBtc1wiKVxufSJ9)

私のローカル環境で実行してみると、以下のように出力されました。

```
9933 件見つかりました。
[実行時間] 9369 ms
```

$10^5$ 件 $\times 10^5$ 件の比較が行われるため、これは$O(N^2)$の処理となっており、$10^{10}$ ステップ程度の処理が発生してしまいます。

このような値の検索が必要な処理には Set （または Map） が適しています。

次に、list2 を Set に変換して同じ処理を実行してみます。

```kotlin
const val N:Int = 100000
const val M:Int = 100000
fun main() {
  val originalList = (0..1000000).toList()
  // サイズNのリスト
  val list1 = originalList.shuffled().take(N)
  // サイズMのリスト
  val list2 = originalList.shuffled().take(M)

  val startTime = System.currentTimeMillis()
  
  // 問題の処理
  val list2Set = list2.toSet()
  val countInList2 = list1.filter{
    list2Set.contains(it)
  }.size

  val endTime = System.currentTimeMillis()

  println("\${countInList2} 件見つかりました。")
  println("[実行時間] \${endTime - startTime} ms")
}
```

こちらのコードのリンクは以下です。

[Playground](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS41LjIxIiwicGxhdGZvcm0iOiJqYXZhIiwiYXJncyI6IiIsIm5vbmVNYXJrZXJzIjp0cnVlLCJ0aGVtZSI6ImlkZWEiLCJjb2RlIjoiY29uc3QgdmFsIE46SW50ID0gMTAwMDAwXG5jb25zdCB2YWwgTTpJbnQgPSAxMDAwMDBcbmZ1biBtYWluKCkge1xuICB2YWwgb3JpZ2luYWxMaXN0ID0gKDAuLjEwMDAwMDApLnRvTGlzdCgpXG4gIC8vIOOCteOCpOOCuk7jga7jg6rjgrnjg4hcbiAgdmFsIGxpc3QxID0gb3JpZ2luYWxMaXN0LnNodWZmbGVkKCkudGFrZShOKVxuICAvLyDjgrXjgqTjgrpN44Gu44Oq44K544OIXG4gIHZhbCBsaXN0MiA9IG9yaWdpbmFsTGlzdC5zaHVmZmxlZCgpLnRha2UoTSlcblxuXG4gIHZhbCBzdGFydFRpbWUgPSBTeXN0ZW0uY3VycmVudFRpbWVNaWxsaXMoKVxuICBcbiAgLy8g5ZWP6aGM44Gu5Yem55CGXG4gIHZhbCBsaXN0MlNldCA9IGxpc3QyLnRvSGFzaFNldCgpXG4gIHZhbCBjb3VudEluTGlzdDIgPSBsaXN0MS5maWx0ZXJ7XG4gICAgbGlzdDJTZXQuY29udGFpbnMoaXQpXG4gIH0uc2l6ZVxuXG4gIHZhbCBlbmRUaW1lID0gU3lzdGVtLmN1cnJlbnRUaW1lTWlsbGlzKClcblxuICBwcmludGxuKFwiJHtjb3VudEluTGlzdDJ9IOS7tuimi+OBpOOBi+OCiuOBvuOBl+OBn+OAglwiKVxuICBwcmludGxuKFwiW+Wun+ihjOaZgumWk10gJHtlbmRUaW1lIC0gc3RhcnRUaW1lfSBtc1wiKVxufSJ9)

結果は次のようになりました。

```
10137 件見つかりました。
[実行時間] 102 ms
```

9369 ms だった処理が 102 ms に短縮されています。

違いは `contains` メソッドの実装が異なっているためであり、$O(1)$ での検索を実現していることです。

よって、全体での計算量は $O(N)$となり、同等の処理を数十倍の速度で達成できました。

また、オブジェクトの比較では、Map のキーに ID となる値を格納することで同様の高速化が期待できます。