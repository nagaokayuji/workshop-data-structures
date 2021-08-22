# TreeMap

平衡二分探索木 の一種である **赤黒木** による実装です（難しいです）。HashMap と比べてメモリ使用量が少なく、最悪計算量の面で優れています。

また、**要素はソートされた状態を維持**されます。

- 値の取得
    - $O(\log N)$
- 値の削除
    - $O(\log N)$
- 値の追加
    - $O(\log N)$
- null 不可

## Kotlin で使用する場合

`java.util.TreeMap` を import して使用できます。

```kotlin
import java.util.TreeMap
```
## 特徴
- キーがソートされる
```kotlin
import java.util.TreeMap
fun main() {
    val items = TreeMap<String, String>()
    items["A"] = "VALUE_A"
    items["1"] = "VALUE_1"
    items["X"] = "VALUE_X"
    println(items.keys) // [1, A, X]
}
```
[playground](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImltcG9ydCBqYXZhLnV0aWwuVHJlZU1hcFxuZnVuIG1haW4oKSB7XG4gICAgdmFsIGl0ZW1zID0gVHJlZU1hcDxTdHJpbmcsIFN0cmluZz4oKVxuICAgIGl0ZW1zW1wiQVwiXSA9IFwiVkFMVUVfQVwiXG4gICAgaXRlbXNbXCIxXCJdID0gXCJWQUxVRV8xXCJcbiAgICBpdGVtc1tcIlhcIl0gPSBcIlZBTFVFX1hcIlxuICAgIHByaW50bG4oaXRlbXMua2V5cykgLy8gWzEsIEEsIFhdXG59IiwicGxhdGZvcm0iOiJqYXZhIiwiYXJncyI6IiJ9)


## ユースケース　
- 順序を維持したい場合
- メモリ使用量を節約したい場合