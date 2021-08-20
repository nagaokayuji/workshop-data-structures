# その他のデータ構造


基本的なデータ構造であるスタック（Stack）やキュー（Queue）について触れておきます。

これらはList構造などを用いて容易に実装できるため、「Listの上手な使い方の１つ」としてとらえられます。

## Stack

Stack は LIFO(Last In First Out) のデータ構造です。

用途としてはundo処理などの戻る処理や幅優先探索などがあります。

`java.util.Stack` 、 `ArrayDeque` などで利用できます。

## Queue

<div align="center">
<img src="https://res.cloudinary.com/ddaz9etkx/image/upload/v1628516592/ot/toilet_gyouretsu_qigx4p.png" width="60%">
</div>


用途としてはジョブスケジューリング、トポロジカルソート[^1]、深さ優先探索などがあります。

`java.util.Queue` を継承した `java.util.LinkedList` 、`ArrayDeque` などで利用できます。

<hr>

[^1]: 有向非巡回グラフ（一般的にDAGと呼ばれる）に順序をつける操作です。