# その他のデータ構造


基本的なデータ構造であるスタック（Stack）やキュー（Queue）について触れておきます。

これらはList構造などを用いて容易に実装できるため、「Listの上手な使い方の１つ」としてとらえられます。




## Stack

Stack は LIFO(Last In First Out) のデータ構造です。

以下のメソッドが用意されており、いずれも$O(1)$ です。

- 要素数を取得
- 先頭に追加(push)
- 先頭の値を取得(peek)
- 先頭の値を削除(pop)


用途としてはundo処理などの戻る処理や幅優先探索などがあります。

[`java.util.Stack`](https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html) などで利用できます。

### 使用例: 括弧列のバリデーション
<pre class="kt">
import java.util.Stack

fun main() {
    println(varidate("(あ)((ああ)あああ)(ああ)()"))  // true
    println(varidate("(あ)((ああ))あああ)(ああ)()")) // false
}

fun varidate(target: String):Boolean {
  val stack = Stack&lt;Char&gt;() // スタックを初期化

  for (character in target) {
    when (character) {
      '(' -> stack.push(character)
      ')' -> if (stack.empty()) {
          return false
      } else {
          stack.pop()
      }
    }
  }
  return stack.empty()
}
</pre>



## Queue

<div align="center">
<img src="https://res.cloudinary.com/ddaz9etkx/image/upload/v1628516592/ot/toilet_gyouretsu_qigx4p.png" width="50%">
</div>

Queue は FIFO(First In First Out) のデータ構造です。

以下のようなメソッドが用意されており、いずれも$O(1)$ です。

- 要素数を取得
- 末尾に追加(add)
- 先頭の値を取得(peek)
- 先頭の値を削除(remove)

用途としてはジョブスケジューリング、トポロジカルソート[^1]、深さ優先探索などがあります。

[`java.util.Queue`](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html) などで利用できます。
### 使用例: ラウンドロビン

<pre class="kt">
import java.util.*

fun main() {
  val st:Queue&lt;String&gt; = LinkedList&lt;String&gt;().apply{
      add("hoge")
      add("fuga")
      add("piyo")
  }
  repeat(10) {
      println(st.peek()) // Queue の先頭を取得
      st.add(st.poll())  // Queue の先頭を取り出し、末尾に追加
  }
}
</pre>


## Deque

Stack と Queue 両方の機能を持ったデータ構造です！

先頭または末尾へのデータ追加・参照・削除がすべて$O(1)$ で行えます。


<hr>

[^1]: 有向非巡回グラフ（一般的にDAGと呼ばれる）に順序をつける操作です。