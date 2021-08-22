# Map

整数以外でも添字に取れる配列のように扱えることから **連想配列** とも呼ばれます。Key-Value 型のデータ構造です。

[Map - Kotlin Programming Language](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/)

```kotlin
interface Map<K, out V>
```

Key に該当するものが分かっている場合は高速にデータを取得することができます。


## 例

適当な Map を作成し、キーに対応する値を取得するには以下のようにします。

```kotlin
fun main() {
    val items = mapOf<String, String>("A" to "VALUE_A", "B" to "VALUE_B")
    println(items.get("A")) // VALUE_A
}
```


## 特徴
- 一意なキーが必要
- キーの存在判定が速い
- キーを指定した場合の値へのアクセスが速い
- 値の検索は速くない
  - 順番に見ていく必要がある

したがって、うまくキーが選べれば非常に便利な使い方ができます。


## List から Map に変換する

いくつか方法があるので紹介します。

### [`toMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/to-map.html)

```kotlin
fun <K, V> Iterable<Pair<K, V>>.toMap(): Map<K, V>
```

Key-Value の Pair から Map に変換できます。

### [`associate`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/associate.html) 

```kotlin
inline fun <T, K, V> Iterable<T>.associate(
    transform: (T) -> Pair<K, V>
): Map<K, V>
```

次のような使い方ができます。
```kotlin
data class User(val name: String, val score: Int)
fun main() {
  val list = listOf(User("Tom", 30), User("John", 99), User("James", 77))
  val map = list.associate{it.name to it}
  println(map) //{Tom=User(name=Tom, score=30), John=User(name=John, score=99), James=User(name=James, score=77)}
}
```
[playground](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImRhdGEgY2xhc3MgVXNlcih2YWwgbmFtZTogU3RyaW5nLCB2YWwgc2NvcmU6IEludClcbmZ1biBtYWluKCkge1xuICB2YWwgbGlzdCA9IGxpc3RPZihVc2VyKFwiVG9tXCIsIDMwKSwgVXNlcihcIkpvaG5cIiwgOTkpLCBVc2VyKFwiSmFtZXNcIiwgNzcpKVxuICB2YWwgbWFwID0gbGlzdC5hc3NvY2lhdGV7aXQubmFtZSB0byBpdH1cbiAgcHJpbnRsbihtYXApIC8ve1RvbT1Vc2VyKG5hbWU9VG9tLCBzY29yZT0zMCksIEpvaG49VXNlcihuYW1lPUpvaG4sIHNjb3JlPTk5KSwgSmFtZXM9VXNlcihuYW1lPUphbWVzLCBzY29yZT03Nyl9XG59IiwicGxhdGZvcm0iOiJqYXZhIiwiYXJncyI6IiJ9)


上述の `toMap` を使用して以下のように書くこともできます。
```kotlin
data class User(val name: String, val score: Int)
fun main() {
  val list = listOf(User("Tom", 30), User("John", 99), User("James", 77))
  val map = list.map{it.name to it}.toMap()
  println(map) //{Tom=User(name=Tom, score=30), John=User(name=John, score=99), James=User(name=James, score=77)}
}
```
[playground](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImRhdGEgY2xhc3MgVXNlcih2YWwgbmFtZTogU3RyaW5nLCB2YWwgc2NvcmU6IEludClcbmZ1biBtYWluKCkge1xuICB2YWwgbGlzdCA9IGxpc3RPZihVc2VyKFwiVG9tXCIsIDMwKSwgVXNlcihcIkpvaG5cIiwgOTkpLCBVc2VyKFwiSmFtZXNcIiwgNzcpKVxuICB2YWwgbWFwID0gbGlzdC5tYXB7aXQubmFtZSB0byBpdH0udG9NYXAoKVxuICBwcmludGxuKG1hcCkgLy97VG9tPVVzZXIobmFtZT1Ub20sIHNjb3JlPTMwKSwgSm9obj1Vc2VyKG5hbWU9Sm9obiwgc2NvcmU9OTkpLCBKYW1lcz1Vc2VyKG5hbWU9SmFtZXMsIHNjb3JlPTc3KX1cbn0iLCJwbGF0Zm9ybSI6ImphdmEiLCJhcmdzIjoiIn0=)

### [`groupBy`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/group-by.html)

value に List を使用したい場合に便利です。

```kotlin
inline fun <T, K> Iterable<T>.groupBy(
    keySelector: (T) -> K
): Map<K, List<T>>
```

次のように使用することができます。

桁数ごとにグルーピング
```kotlin
fun main() {
  val list = listOf(1234,12,2,34,1234,6,1,0,-9587,-95,-921)
  val map = list.groupBy{ 
      var ret = 0
      var value = if (it > 0) {it} else {-it}
      while (value > 0) {
          ret = ret + 1
          value = value / 10
      }
      ret
  }
  println(map) // {4=[1234, 1234, -9587], 2=[12, 34, -95], 1=[2, 6, 1], 0=[0], 3=[-921]}
}
```
[playground](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImZ1biBtYWluKCkge1xuICB2YWwgbGlzdCA9IGxpc3RPZigxMjM0LDEyLDIsMzQsMTIzNCw2LDEsMCwtOTU4NywtOTUsLTkyMSlcbiAgdmFsIG1hcCA9IGxpc3QuZ3JvdXBCeXsgXG4gICAgICB2YXIgcmV0ID0gMFxuICAgICAgdmFyIHZhbHVlID0gaWYgKGl0ID4gMCkge2l0fSBlbHNlIHstaXR9XG4gICAgICB3aGlsZSAodmFsdWUgPiAwKSB7XG4gICAgICAgICAgcmV0ID0gcmV0ICsgMVxuICAgICAgICAgIHZhbHVlID0gdmFsdWUgLyAxMFxuICAgICAgfVxuICAgICAgcmV0XG4gIH1cbiAgcHJpbnRsbihtYXApIC8vIHs0PVsxMjM0LCAxMjM0LCAtOTU4N10sIDI9WzEyLCAzNCwgLTk1XSwgMT1bMiwgNiwgMV0sIDA9WzBdLCAzPVstOTIxXX1cbn0iLCJwbGF0Zm9ybSI6ImphdmEiLCJhcmdzIjoiIn0=)