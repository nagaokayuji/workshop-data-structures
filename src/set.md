# Set

集合を管理するデータ構造です。List と同じ `Collection` を継承しているため、`filter`, `forEach` などのメソッドが利用できます。

```kotlin
interface Set<out E> : Collection<E>
```

HashSet, TreeSet などがありますが、

内部の実装では Map が使用されているため 計算量も Map の場合と同様になります。

### List との違い

一般的なSetでは重複が許されません。

また、順序を持たないため $i$ 番目へのアクセス などの概念がありません。

そのため、次のようなコードにより List から重複を排除することができます。

```kotlin
val list: List<Int> = listOf(1, 1, 2, 3, 5, 8, 13, 21)
// dedupList: [1, 2, 3, 5, 8, 13, 21] になる
val dedupList: List<Int> = list.toSet().toList()
```

Kotlin では `distinct()` メソッドがあるため、重複を排除したい場合は distinct() を使用するほうが分かりやすいです。

※ 内部の実装では同様の操作を行っています。

[kotlin/_Arrays.kt at 80cce1dc5280eb9135390270c8644a7b8d198071 · JetBrains/kotlin](https://github.com/JetBrains/kotlin/blob/80cce1dc5280eb9135390270c8644a7b8d198071/libraries/stdlib/common/src/generated/_Arrays.kt#L11828)

また、ArrayList では平均計算量 $O(N)$ となる存在確認の処理 ( `contains()`など) が Set を使用することで $O(1)$ や $O(\log N)$ で行えるため、計算量の改善が期待できます。