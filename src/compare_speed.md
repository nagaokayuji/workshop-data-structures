# 遅い例と速い例
## 存在確認

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


## 最小値の検索

次のような処理を考えます。

- 以下を$M$回繰り返す
- 長さ$N$のリストの中から最小値を探し、2倍したものに置き換える

### List で処理する

以下は最小値の検索に `minOrNull()`、インデックスの検索に `indexOf()` を用いた実装です。

<pre class="kt">
const val N:Int = 100000
const val M:Int = 10000
fun main() {
  val originalList = (1..101000).toList()
  // サイズNのリスト
  val list = originalList.shuffled().take(N).toMutableList()
  
  val startTime = System.currentTimeMillis()
  // 以下の操作を M 回行いたい！
  // - 要素の中から最小値を見つけ、2倍したい
  repeat(M){
      val min = list.minOrNull()!!
      val index = list.indexOf(min)
      list[index] = min * 2
  }
  val endTime = System.currentTimeMillis()
  println("[実行時間] \${endTime - startTime} ms")
}
</pre>
[playground🔗](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImNvbnN0IHZhbCBOOkludCA9IDEwMDAwMFxuY29uc3QgdmFsIE06SW50ID0gMTAwMDBcbmZ1biBtYWluKCkge1xuICB2YWwgb3JpZ2luYWxMaXN0ID0gKDEuLjEwMTAwMCkudG9MaXN0KClcbiAgLy8g44K144Kk44K6TuOBruODquOCueODiFxuICB2YWwgbGlzdCA9IG9yaWdpbmFsTGlzdC5zaHVmZmxlZCgpLnRha2UoTikudG9NdXRhYmxlTGlzdCgpXG4gIFxuICB2YWwgc3RhcnRUaW1lID0gU3lzdGVtLmN1cnJlbnRUaW1lTWlsbGlzKClcbiAgLy8g5Lul5LiL44Gu5pON5L2c44KSIE0g5Zue6KGM44GE44Gf44GE77yBXG4gIC8vIC0g6KaB57Sg44Gu5Lit44GL44KJ5pyA5bCP5YCk44KS6KaL44Gk44GR44CBMuWAjeOBl+OBn+OBhFxuICByZXBlYXQoTSl7XG4gICAgICB2YWwgbWluID0gbGlzdC5taW5Pck51bGwoKSEhXG4gICAgICB2YWwgaW5kZXggPSBsaXN0LmluZGV4T2YobWluKVxuICAgICAgbGlzdFtpbmRleF0gPSBtaW4gKiAyXG4gIH1cbiAgdmFsIGVuZFRpbWUgPSBTeXN0ZW0uY3VycmVudFRpbWVNaWxsaXMoKVxuICBwcmludGxuKFwiW+Wun+ihjOaZgumWk10gJHtlbmRUaW1lIC0gc3RhcnRUaW1lfSBtc1wiKVxufSIsInBsYXRmb3JtIjoiamF2YSIsImFyZ3MiOiIifQ==)

実行すると以下のようになりました。

```
[実行時間] 4027 ms
```

### Map で処理する
一方、TreeMap を利用して以下のように同様の処理を実施できます。

<pre class="kt">
const val N:Int = 100000
const val M:Int = 10000
fun main() {
  val originalList = (1..101000).toList()
  // サイズNのリスト
  val list = originalList.shuffled().take(N).toList()
  
  val startTime = System.currentTimeMillis()

  // 以下の操作を M 回行いたい！
  // - 要素の中から最小値を見つけ、2倍したい
  val map = list.groupingBy{it}.eachCount().toSortedMap() // key: 要素の値, value: 個数
  repeat(M){
    val minElement = map.asSequence().first() // 先頭の要素 = 最小値
    val min = minElement.key
    map[min] = minElement.value - 1
    if (map[min] == 0) {
        map.remove(min)
    }
    map[min*2] = map.getOrDefault(min*2, 0) + 1
  }
  val result = map.map{ (key, value) -> List<Int>(value, {key})}.flatten().toList()
  val endTime = System.currentTimeMillis()
  println("[実行時間] \${endTime - startTime} ms")
}
</pre>
[playground🔗](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImNvbnN0IHZhbCBOOkludCA9IDEwMDAwMFxuY29uc3QgdmFsIE06SW50ID0gMTAwMDBcbmZ1biBtYWluKCkge1xuICB2YWwgb3JpZ2luYWxMaXN0ID0gKDEuLjEwMTAwMCkudG9MaXN0KClcbiAgLy8g44K144Kk44K6TuOBruODquOCueODiFxuICB2YWwgbGlzdCA9IG9yaWdpbmFsTGlzdC5zaHVmZmxlZCgpLnRha2UoTikudG9MaXN0KClcbiAgXG4gIHZhbCBzdGFydFRpbWUgPSBTeXN0ZW0uY3VycmVudFRpbWVNaWxsaXMoKVxuICAvLyDku6XkuIvjga7mk43kvZzjgpIgTSDlm57ooYzjgYTjgZ/jgYTvvIFcbiAgLy8gLSDopoHntKDjga7kuK3jgYvjgonmnIDlsI/lgKTjgpLopovjgaTjgZHjgIEy5YCN44GX44Gf44GEXG5cbiAgdmFsIG1hcCA9IGxpc3QuZ3JvdXBpbmdCeXtpdH0uZWFjaENvdW50KCkudG9Tb3J0ZWRNYXAoKSAvLyBrZXk6IOimgee0oOOBruWApCwgdmFsdWU6IOWAi+aVsFxuICByZXBlYXQoTSl7XG4gICAgdmFsIG1pbkVsZW1lbnQgPSBtYXAuYXNTZXF1ZW5jZSgpLmZpcnN0KClcbiAgICB2YWwgbWluID0gbWluRWxlbWVudC5rZXlcbiAgICBtYXBbbWluXSA9IG1pbkVsZW1lbnQudmFsdWUgLSAxXG4gICAgaWYgKG1hcFttaW5dID09IDApIHtcbiAgICAgICAgbWFwLnJlbW92ZShtaW4pXG4gICAgfVxuICAgIG1hcFttaW4qMl0gPSBtYXAuZ2V0T3JEZWZhdWx0KG1pbioyLCAwKSArIDFcbiAgfVxuICB2YWwgcmVzdWx0ID0gbWFwLm1hcHsgKGtleSwgdmFsdWUpIC0+IExpc3Q8SW50Pih2YWx1ZSwge2tleX0pfS5mbGF0dGVuKCkudG9MaXN0KClcbiAgdmFsIGVuZFRpbWUgPSBTeXN0ZW0uY3VycmVudFRpbWVNaWxsaXMoKVxuICBwcmludGxuKFwiW+Wun+ihjOaZgumWk10gJHtlbmRUaW1lIC0gc3RhcnRUaW1lfSBtc1wiKVxufSIsInBsYXRmb3JtIjoiamF2YSIsImFyZ3MiOiIifQ==)

この実行結果は以下のようになりました。
```
[実行時間] 494 ms
```
なお、この結果は List $\Leftrightarrow$ Map の変換時間も含んでいます。

このように List を使用していたところを Map (TreeMap) に変更することにより、

**4027 ms** が **494 ms** に短縮されました。


### 計算量を見積もる
- List の場合
  - 最小値の検索 平均 $O(N)$
  - インデックスの取得 平均 $O(N)$
  - 2倍して置き換える処理 $O(1)$
  
より、時間計算量は$O(MN)$ となります。

- Map の場合
  - 最小値の検索・取得 $O(\log N)$
  - 2倍して置き換える処理 $O(\log N)$

より、時間計算量は$O(M \log N)$ となります。

