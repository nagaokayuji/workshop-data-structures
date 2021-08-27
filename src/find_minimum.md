# 最小値の検索


次のような処理を考えます。

- 以下を$M$回繰り返す
- 長さ$N$のリストの中から最小値を探し、2倍したものに置き換える

## List で処理する場合

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

## Map で処理する場合
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


## 計算量を見積もる
- List の場合
  - 最小値の検索 平均 $O(N)$
  - インデックスの取得 平均 $O(N)$
  - 2倍して置き換える処理 $O(1)$
  
より、時間計算量は$O(MN)$ となります。

- Map の場合
  - 最小値の検索・取得 $O(\log N)$
  - 2倍して置き換える処理 $O(\log N)$

より、時間計算量は$O(M \log N)$ となります。
