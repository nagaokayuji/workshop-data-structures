# Collection について

今回扱うデータ構造である List や Set が継承しているインタフェースです。

複数の要素を扱う上で便利な機能が揃っています。

```kotlin
interface Collection<out E> : Iterable<E>
```

詳しくは以下に記載があります。

[Collection - Kotlin Programming Language](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/)
