# 何度もfilterする


```kotlin
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
```
[playground🔗](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImNvbnN0IHZhbCBOOkludCA9IDUwMDAwMFxuZnVuIG1haW4oKSB7XG4gIHZhbCBvcmlnaW5hbExpc3QgPSAoMC4uTioyKS50b0xpc3QoKVxuICAvLyDjgrXjgqTjgrpO44Gu44Oq44K544OIXG4gIHZhbCBsaXN0MSA9IG9yaWdpbmFsTGlzdC5zaHVmZmxlZCgpLnRha2UoTilcblxuICB2YWwgc3RhcnRUaW1lID0gU3lzdGVtLmN1cnJlbnRUaW1lTWlsbGlzKClcblxuICAvLyBmaWx0ZXJcbiAgdmFsIGZpbHRlcmVkTGlzdCA9IGxpc3QxLmZpbHRlcntpdCAhPSAxfS5maWx0ZXJ7aXQgIT0gMn0uZmlsdGVye2l0ICE9IDN9LmZpbHRlcntpdCAhPSA0fS5maWx0ZXJ7aXQgIT0gNX1cbiAgXG4gIHZhbCBlbmRUaW1lID0gU3lzdGVtLmN1cnJlbnRUaW1lTWlsbGlzKClcbiAgcHJpbnRsbihcIlvlrp/ooYzmmYLplpNdIFwiICsgKGVuZFRpbWUgLSBzdGFydFRpbWUpLnRvU3RyaW5nKCkgKyBcIm1zXCIpXG59IiwicGxhdGZvcm0iOiJqYXZhIiwiYXJncyI6IiJ9)

実行結果

```kotlin
[実行時間] 964ms
```
