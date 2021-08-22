# TreeMap

平衡二分探索木 による実装[^1]です。

HashMap と比べてメモリ使用量が少なく、最悪計算量の面で優れています。

また、**要素はソートされた状態を維持**されます。

- 値の取得
    - $O(\log N)$
- 値の削除
    - $O(\log N)$
- 値の追加
    - $O(\log N)$
- null 不可

## Kotlin で使用する場合

`java.util.TreeMap` が使用できます。

```kotlin
import java.util.TreeMap
```

もしくは [`sortedMapOf()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-map-of.html)
や[`toSortedMap()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/to-sorted-map.html) などで`java.util.TreeMap`が使用されます。


## 特徴
- キーがソートされる
```kotlin
fun main() {
    val items = sortedMapOf<String, String>()
    items["A"] = "VALUE_A"
    items["1"] = "VALUE_1"
    items["X"] = "VALUE_X"
    println(items.keys) // [1, A, X]
}
```
[playground](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImZ1biBtYWluKCkge1xuICAgIHZhbCBpdGVtcyA9IHNvcnRlZE1hcE9mPFN0cmluZywgU3RyaW5nPigpXG4gICAgaXRlbXNbXCJBXCJdID0gXCJWQUxVRV9BXCJcbiAgICBpdGVtc1tcIjFcIl0gPSBcIlZBTFVFXzFcIlxuICAgIGl0ZW1zW1wiWFwiXSA9IFwiVkFMVUVfWFwiXG4gICAgcHJpbnRsbihpdGVtcy5rZXlzKSAvLyBbMSwgQSwgWF1cbn0iLCJwbGF0Zm9ybSI6ImphdmEiLCJhcmdzIjoiIn0=)


- key は null 不可
```kotlin
sortedMapOf<String?, String>() // error
```
## ユースケース　
- 順序を維持したい場合
- メモリ使用量を節約したい場合

---

[^1]: [TreeMap (Java Platform SE 8 )](https://docs.oracle.com/javase/jp/8/docs/api/java/util/TreeMap.html)