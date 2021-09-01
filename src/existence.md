# 存在確認

次のコードはそれぞれサイズN, サイズM のリスト中に何個の重複があるかを確認するためのものです。

## List + filter + contains

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

結果は以下です。

```
99005 件見つかりました。
[実行時間] 7080 ms
```

$10^5$ 件 $\times 10^5$ 件の比較が行われるため、これは$O(N^2)$の処理となっており、$10^{10}$ ステップ程度の処理が発生してしまいます。


## Set + filter + contains
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

以下のような結果が得られます。

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

## intersect

[便利な関数](./collection_methods.md) で紹介した`intersect`を覚えていますでしょうか。

これを使用して以下のように書くことができます。

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
  val countInLists = list1.intersect(list2).size

  val endTime = System.currentTimeMillis()
  println("\${countInLists} 件見つかりました。")
  println("[実行時間] \${endTime - startTime} ms")
}
</pre>

以下のような結果が得られます。

```
99011 件見つかりました。
[実行時間] 132 ms
```

内部では`Set`が使用されているため、こちらも高速です。
