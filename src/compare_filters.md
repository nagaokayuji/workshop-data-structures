# 何度もfilterする
<script src="https://unpkg.com/kotlin-playground@1"></script>
<script>
document.addEventListener('DOMContentLoaded', function() {
  KotlinPlayground('.kt');
});
</script>
<pre class="kt">
const val N:Int = 500000
fun main() {
  val originalList = (0..N*2).toList()
  // サイズNのリスト
  val list1 = originalList.shuffled().take(N)

  val startTime = System.currentTimeMillis()

  // filter
  val filteredList = list1.filter{it != 1}.filter{it != 2}.filter{it != 3}.filter{it != 4}.filter{it != 5}
  
  val endTime = System.currentTimeMillis()
  println("[実行時間] " + (endTime - startTime).toString() + "ms")
}
</pre>

[playground🔗](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImNvbnN0IHZhbCBOOkludCA9IDUwMDAwMFxuZnVuIG1haW4oKSB7XG4gIHZhbCBvcmlnaW5hbExpc3QgPSAoMC4uTioyKS50b0xpc3QoKVxuICAvLyDjgrXjgqTjgrpO44Gu44Oq44K544OIXG4gIHZhbCBsaXN0MSA9IG9yaWdpbmFsTGlzdC5zaHVmZmxlZCgpLnRha2UoTilcblxuICB2YWwgc3RhcnRUaW1lID0gU3lzdGVtLmN1cnJlbnRUaW1lTWlsbGlzKClcblxuICAvLyBmaWx0ZXJcbiAgdmFsIGZpbHRlcmVkTGlzdCA9IGxpc3QxLmZpbHRlcntpdCAhPSAxfS5maWx0ZXJ7aXQgIT0gMn0uZmlsdGVye2l0ICE9IDN9LmZpbHRlcntpdCAhPSA0fS5maWx0ZXJ7aXQgIT0gNX1cbiAgXG4gIHZhbCBlbmRUaW1lID0gU3lzdGVtLmN1cnJlbnRUaW1lTWlsbGlzKClcbiAgcHJpbnRsbihcIlvlrp/ooYzmmYLplpNdIFwiICsgKGVuZFRpbWUgLSBzdGFydFRpbWUpLnRvU3RyaW5nKCkgKyBcIm1zXCIpXG59IiwicGxhdGZvcm0iOiJqYXZhIiwiYXJncyI6IiJ9)

実行結果

```
[実行時間] 964ms
```


## 少し改善

上記のコードでは、`filter`のたびに先頭から評価していく処理が走っていることが予想されます。

よって、条件式を1つにしてみます。

<pre class="kt">
const val N:Int = 500000
fun main() {
  val originalList = (0..N*2).toList()
  // サイズNのリスト
  val list1 = originalList.shuffled().take(N)

  val startTime = System.currentTimeMillis()

  // filter
  val filteredList = list1.filter{it != 1 && it != 2 && it != 3 && it != 4 && it != 5}
  
  val endTime = System.currentTimeMillis()
  println("[実行時間] " + (endTime - startTime).toString() + "ms")
}
</pre>
[playground](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImNvbnN0IHZhbCBOOkludCA9IDUwMDAwMFxuZnVuIG1haW4oKSB7XG4gIHZhbCBvcmlnaW5hbExpc3QgPSAoMC4uTioyKS50b0xpc3QoKVxuICAvLyDjgrXjgqTjgrpO44Gu44Oq44K544OIXG4gIHZhbCBsaXN0MSA9IG9yaWdpbmFsTGlzdC5zaHVmZmxlZCgpLnRha2UoTilcblxuICB2YWwgc3RhcnRUaW1lID0gU3lzdGVtLmN1cnJlbnRUaW1lTWlsbGlzKClcblxuICAvLyBmaWx0ZXJcbiAgdmFsIGZpbHRlcmVkTGlzdCA9IGxpc3QxLmZpbHRlcntpdCAhPSAxICYmIGl0ICE9IDIgJiYgaXQgIT0gMyAmJiBpdCAhPSA0ICYmIGl0ICE9IDV9XG4gIFxuICB2YWwgZW5kVGltZSA9IFN5c3RlbS5jdXJyZW50VGltZU1pbGxpcygpXG4gIHByaW50bG4oXCJb5a6f6KGM5pmC6ZaTXSBcIiArIChlbmRUaW1lIC0gc3RhcnRUaW1lKS50b1N0cmluZygpICsgXCJtc1wiKVxufSIsInBsYXRmb3JtIjoiamF2YSIsImFyZ3MiOiIifQ==)

実行結果は以下です。

```
[実行時間] 383ms
```

だいぶ改善されました。

## 遅延評価

これまでは[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/)インタフェースの`filter` (戻り値がList)処理を行ってきました。

続いて、[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/)インタフェースの`filter`を使用し、遅延評価にしてみます。


<pre class="kt">
const val N:Int = 500000
fun main() {
  val originalList = (0..N*2).toList()
  // サイズNのリスト
  val list1 = originalList.shuffled().take(N)

  val startTime = System.currentTimeMillis()

  // filter
  val filteredList = list1.asSequence().filter{it != 1}.filter{it != 2}.filter{it != 3}.filter{it != 4}.filter{it != 5}.toList()
  
  val endTime = System.currentTimeMillis()
  println("[実行時間] " + (endTime - startTime).toString() + "ms")
}
</pre>
[playground](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImNvbnN0IHZhbCBOOkludCA9IDUwMDAwMFxuZnVuIG1haW4oKSB7XG4gIHZhbCBvcmlnaW5hbExpc3QgPSAoMC4uTioyKS50b0xpc3QoKVxuICAvLyDjgrXjgqTjgrpO44Gu44Oq44K544OIXG4gIHZhbCBsaXN0MSA9IG9yaWdpbmFsTGlzdC5zaHVmZmxlZCgpLnRha2UoTilcblxuICB2YWwgc3RhcnRUaW1lID0gU3lzdGVtLmN1cnJlbnRUaW1lTWlsbGlzKClcblxuICAvLyBmaWx0ZXJcbiAgdmFsIGZpbHRlcmVkTGlzdCA9IGxpc3QxLmFzU2VxdWVuY2UoKS5maWx0ZXJ7aXQgIT0gMX0uZmlsdGVye2l0ICE9IDJ9LmZpbHRlcntpdCAhPSAzfS5maWx0ZXJ7aXQgIT0gNH0uZmlsdGVye2l0ICE9IDV9LnRvTGlzdCgpXG4gIFxuICB2YWwgZW5kVGltZSA9IFN5c3RlbS5jdXJyZW50VGltZU1pbGxpcygpXG4gIHByaW50bG4oXCJb5a6f6KGM5pmC6ZaTXSBcIiArIChlbmRUaW1lIC0gc3RhcnRUaW1lKS50b1N0cmluZygpICsgXCJtc1wiKVxufSIsInBsYXRmb3JtIjoiamF2YSIsImFyZ3MiOiIifQ==)

結果は次のようになりました。

```
[実行時間] 305ms
```