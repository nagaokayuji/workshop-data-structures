# HashMap

ハッシュテーブルを用いた実装です。

Kotlin で `mapOf()` 等で宣言すると `HashMap` になるようです。

後述する TreeMap と比べて(平均)計算量に優れています。

- 値の取得
  - 平均 $O(1)$, 最悪 $O(N)$
- 値の削除
  - 平均 $O(1)$, 最悪 $O(N)$
- 値の追加
  - 平均 $O(1)$, 最悪 $O(N)$
- 値の検索
  - $O(N)$


```kotlin
fun main() {
    val items = HashMap<String, String>()
    items["A"] = "VALUE_A"
    items["1"] = "VALUE_1"
    items["X"] = "VALUE_X"
    println(items.keys) // [A, 1, X]
}
```
[playground](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImZ1biBtYWluKCkge1xuICAgIHZhbCBpdGVtcyA9IEhhc2hNYXA8U3RyaW5nLCBTdHJpbmc+KClcbiAgICBpdGVtc1tcIkFcIl0gPSBcIlZBTFVFX0FcIlxuICAgIGl0ZW1zW1wiMVwiXSA9IFwiVkFMVUVfMVwiXG4gICAgaXRlbXNbXCJYXCJdID0gXCJWQUxVRV9YXCJcbiAgICBwcmludGxuKGl0ZW1zLmtleXMpIC8vIFtBLCAxLCBYXVxufSIsInBsYXRmb3JtIjoiamF2YSIsImFyZ3MiOiIifQ==)
