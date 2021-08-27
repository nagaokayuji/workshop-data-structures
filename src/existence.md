# 存在確認

次のコードはそれぞれサイズN, サイズM のリスト中に何個の重複があるかを確認するためのものです。

<pre class="kt">
const val N:Int = 100000
const val M:Int = 100000
fun main() {
  val originalList = (0..101000).toList()
  // サイズNのリスト
  val list1 = originalList.shuffled().take(N)
  // サイズMのリスト
  val list2 = originalList.shuffled().take(M)
  val startTime = System.currentTimeMillis()
  // 問題の処理
  val countInLists = list1.filter{
    list2.contains(it)
  }.size

  val endTime = System.currentTimeMillis()
  println("\${countInLists} 件見つかりました。")
  println("[実行時間] \${endTime - startTime} ms")
}
</pre>
[playground🔗](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImNvbnN0IHZhbCBOOkludCA9IDEwMDAwMFxuY29uc3QgdmFsIE06SW50ID0gMTAwMDAwXG5mdW4gbWFpbigpIHtcbiAgdmFsIG9yaWdpbmFsTGlzdCA9ICgwLi4xMDEwMDApLnRvTGlzdCgpXG4gIC8vIOOCteOCpOOCuk7jga7jg6rjgrnjg4hcbiAgdmFsIGxpc3QxID0gb3JpZ2luYWxMaXN0LnNodWZmbGVkKCkudGFrZShOKVxuICAvLyDjgrXjgqTjgrpN44Gu44Oq44K544OIXG4gIHZhbCBsaXN0MiA9IG9yaWdpbmFsTGlzdC5zaHVmZmxlZCgpLnRha2UoTSlcbiAgdmFsIHN0YXJ0VGltZSA9IFN5c3RlbS5jdXJyZW50VGltZU1pbGxpcygpXG4gIC8vIOWVj+mhjOOBruWHpueQhlxuICB2YWwgY291bnRJbkxpc3RzID0gbGlzdDEuZmlsdGVye1xuICAgIGxpc3QyLmNvbnRhaW5zKGl0KVxuICB9LnNpemVcblxuICB2YWwgZW5kVGltZSA9IFN5c3RlbS5jdXJyZW50VGltZU1pbGxpcygpXG4gIHByaW50bG4oXCIke2NvdW50SW5MaXN0c30g5Lu26KaL44Gk44GL44KK44G+44GX44Gf44CCXCIpXG4gIHByaW50bG4oXCJb5a6f6KGM5pmC6ZaTXSAke2VuZFRpbWUgLSBzdGFydFRpbWV9IG1zXCIpXG59IiwicGxhdGZvcm0iOiJqYXZhIiwiYXJncyI6IiJ9)


これを実行してみると、以下のように出力されました。

```
99005 件見つかりました。
[実行時間] 7080 ms
```

$10^5$ 件 $\times 10^5$ 件の比較が行われるため、これは$O(N^2)$の処理となっており、$10^{10}$ ステップ程度の処理が発生してしまいます。

次に、list2 を Set に変換して同じ処理を実行してみます。

<pre class="kt">
const val N:Int = 100000
const val M:Int = 100000
fun main() {
  val originalList = (0..101000).toList()
  // サイズNのリスト
  val list1 = originalList.shuffled().take(N)
  // サイズMのリスト
  val list2 = originalList.shuffled().take(M)
  val startTime = System.currentTimeMillis()
  // 問題の処理
  val list2Set = list2.toSet() // Set に変換しておく
  val countInLists = list1.filter{
    list2Set.contains(it)
  }.size

  val endTime = System.currentTimeMillis()
  println("\${countInLists} 件見つかりました。")
  println("[実行時間] \${endTime - startTime} ms")
}
</pre>

こちらのコードのリンクは以下です。

[playground🔗](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImNvbnN0IHZhbCBOOkludCA9IDEwMDAwMFxuY29uc3QgdmFsIE06SW50ID0gMTAwMDAwXG5mdW4gbWFpbigpIHtcbiAgdmFsIG9yaWdpbmFsTGlzdCA9ICgwLi4xMDEwMDApLnRvTGlzdCgpXG4gIC8vIOOCteOCpOOCuk7jga7jg6rjgrnjg4hcbiAgdmFsIGxpc3QxID0gb3JpZ2luYWxMaXN0LnNodWZmbGVkKCkudGFrZShOKVxuICAvLyDjgrXjgqTjgrpN44Gu44Oq44K544OIXG4gIHZhbCBsaXN0MiA9IG9yaWdpbmFsTGlzdC5zaHVmZmxlZCgpLnRha2UoTSlcbiAgdmFsIHN0YXJ0VGltZSA9IFN5c3RlbS5jdXJyZW50VGltZU1pbGxpcygpXG4gIC8vIOWVj+mhjOOBruWHpueQhlxuICB2YWwgbGlzdDJTZXQgPSBsaXN0Mi50b1NldCgpIC8vIFNldCDjgavlpInmj5vjgZfjgabjgYrjgY9cbiAgdmFsIGNvdW50SW5MaXN0cyA9IGxpc3QxLmZpbHRlcntcbiAgICBsaXN0MlNldC5jb250YWlucyhpdClcbiAgfS5zaXplXG5cbiAgdmFsIGVuZFRpbWUgPSBTeXN0ZW0uY3VycmVudFRpbWVNaWxsaXMoKVxuICBwcmludGxuKFwiJHtjb3VudEluTGlzdHN9IOS7tuimi+OBpOOBi+OCiuOBvuOBl+OBn+OAglwiKVxuICBwcmludGxuKFwiW+Wun+ihjOaZgumWk10gJHtlbmRUaW1lIC0gc3RhcnRUaW1lfSBtc1wiKVxufSIsInBsYXRmb3JtIjoiamF2YSIsImFyZ3MiOiIifQ==)

結果は次のようになりました。

```
99005 件見つかりました。
[実行時間] 77 ms
```

**7080 ms** だった処理が **77 ms** に短縮されています。

違いは `contains` メソッドの実装が異なっているためであり、$O(1)$ での検索を実現していることです。

よって、全体での計算量は $O(N)$となり、同等の処理を数十倍の速度で達成できました。

また、オブジェクトの比較では、Map のキーに ID となる値を格納することで同様の高速化が期待できます。

## 問題

上記コードにおいて、$N > M$ の 場合と $N < M$  の場合で比較すると、どちらの効率が良いでしょうか？

