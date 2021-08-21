# ArrayList

ArrayList は最も基本的なリスト構造です。

一般的な配列とほぼ同じように使えますが、**サイズが可変** で末尾への追加が容易です。

要素を追加すると、内部では自動で配列のサイズが拡張されます。

各操作の計算量は以下です。

- $i$番目へのアクセス
    - $O(1)$
    - 速い
- 末尾へ追加
    - $O(1)$ （償却計算量）
    - 便利
- 値の検索
    - 順番にループを回して比較していく必要がある
    - 最悪計算量は $O(N)$
- $i$番目の要素を削除
    - $O(N)$
    - やらないほうが良い
    - 末尾の要素だけ $O(1)$ [^1]

一般的に`listOf()` などでリスト化すると `ArrayList`が使用されます。

```kotlin
fun main() {
  println(listOf(0,1,2).javaClass.kotlin)
  // class java.util.Arrays$ArrayList (Kotlin reflection is not available)

  println(mutableListOf(0,1,2).javaClass.kotlin)
  // class java.util.ArrayList (Kotlin reflection is not available)
}
```

## ユースケース

- 高機能な配列が欲しい場合
- 可変長の配列が欲しい場合

大体のケースでは `ArrayList` を使用しておけば間違いないと思います。

### 注意

末尾以外に要素を挿入する場合や削除する場合は遅いため注意が必要です。

---

[^1]: [OpenJDK のコード](http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/util/ArrayList.java)