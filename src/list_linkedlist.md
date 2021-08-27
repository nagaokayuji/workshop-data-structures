# LinkedList

ArrayList の弱点である挿入や削除に強いデータ構造です。

各要素には「前の要素や後ろの要素が何か」の情報だけを持たせることにより、挿入や削除の処理を簡潔に実現しています。

このとき、「自分が全体の何番目の要素か」という情報は管理されないことに注意してください。i 番目の要素にアクセスするためには、先頭（または末尾）から順にたどっていく必要があります。


- i番目へのアクセス
    - $O(N)$
    - 順にたどっていく必要があるため遅い
    - 軽い気持ちで使ってはいけない
      - [軽い気持ちでLinkedListを使ったら休出する羽目になった話 - Qiita](https://qiita.com/neko_machi/items/d620c4a8958e74df3550)

- 特定の位置に挿入
    - $O(N)$
    - ArrayList よりは軽い
- i 番目の要素を削除
    - $O(N)$
      - $i$番目の要素にアクセスするために$O(N)$かかる
    - ArrayList よりは軽い
- 特定要素を削除
  - $O(1)$
    - リンクをつなぎ替えるだけでよい
- 値の検索
    - $O(N)$
    - ArrayList と同様

## Kotlin から使用する場合

`java.util.LinkedList` を import して双方向リンクリストを使用できます。


```kotlin
import java.util.LinkedList
fun main() {
    val linkedList: LinkedList<Int> =  LinkedList<Int>()
    linkedList.addAll(listOf(123,777,2,0,-999,50)) // この順番で挿入
    println(linkedList[2]) // 2
    println(linkedList.contains(777)) // true
    println(linkedList.filter{it==777}) // [777]
    println(linkedList.size) // 6
}
```
[playground🔗](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImltcG9ydCBqYXZhLnV0aWwuTGlua2VkTGlzdFxuZnVuIG1haW4oKSB7XG4gICAgdmFsIGxpbmtlZExpc3Q6IExpbmtlZExpc3Q8SW50PiA9ICBMaW5rZWRMaXN0PEludD4oKVxuICAgIGxpbmtlZExpc3QuYWRkQWxsKGxpc3RPZigxMjMsNzc3LDIsMCwtOTk5LDUwKSkgLy8g44GT44Gu6aCG55Wq44Gn5oy/5YWlXG4gICAgcHJpbnRsbihsaW5rZWRMaXN0WzJdKSAvLyAyXG4gICAgcHJpbnRsbihsaW5rZWRMaXN0LmNvbnRhaW5zKDc3NykpIC8vIHRydWVcbiAgICBwcmludGxuKGxpbmtlZExpc3QuZmlsdGVye2l0PT03Nzd9KSAvLyBbNzc3XVxuICAgIHByaW50bG4obGlua2VkTGlzdC5zaXplKSAvLyA2XG59IiwicGxhdGZvcm0iOiJqYXZhIiwiYXJncyI6IiJ9)


## ユースケース
- ランダムなアクセスが不要の場合
  - 前から順番に処理をしていく場合などは使いやすい
- 要素の追加・削除が必要な場合
  - ArrayList より速い

