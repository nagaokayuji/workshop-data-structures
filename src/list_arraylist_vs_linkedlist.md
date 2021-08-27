# ArrayList v.s. LinkedList

比較すると以下のようになります。

**比較結果** にはより効率の良い方を載せています。

| 操作          | ArrayList                  | LinkedList | 比較結果       |
| ------------- | -------------------------- | ---------- | -------------- |
| 末尾に挿入    | $O(1)$(平均), $O(N)$(最悪) | $O(1)$     | LinkedList     |
| $i$番目に挿入 | $O(N)$                     | $O(N)$     | $i$ による[^1] |
| 値で検索      | $O(N)$                     | $O(N)$     | ArrayList[^2]  |
| $i$番目を取得 | $O(1)$                     | $O(N)$     | ArrayList      |
| $i$番目を削除 | $O(N)$                     | $O(N)$     | $i$ による[^1] |


---

[^1]: 末尾に近い場合はどちらも速い。$i$ が 0 に近い場合はLinkedListが有利。$i$ が中央付近にある時はArrayListが速い。
[playground🔗](https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjcwIiwiY29kZSI6ImltcG9ydCBqYXZhLnV0aWwuTGlua2VkTGlzdFxuY29uc3QgdmFsIE46SW50ID0gMTAwMDAwXG5jb25zdCB2YWwgTTpJbnQgPSAzMDAwMFxuZnVuIG1haW4oKSB7XG4gIHZhbCBvcmlnaW5hbExpc3QgPSAoMS4uMTAxMDAwMCkudG9MaXN0KClcbiAgdmFsIGFycmF5TGlzdCA9IEFycmF5TGlzdDxJbnQ+KClcbiAgdmFsIGxpbmtlZExpc3QgPSBMaW5rZWRMaXN0PEludD4oKVxuICBcbiAgb3JpZ2luYWxMaXN0LnNodWZmbGVkKCkudGFrZShOKS5mb3JFYWNoe1xuICAgICAgYXJyYXlMaXN0LmFkZChpdClcbiAgICAgIGxpbmtlZExpc3QuYWRkKGl0KVxuICB9XG4gIHZhbCBzdGFydFRpbWUxID0gU3lzdGVtLmN1cnJlbnRUaW1lTWlsbGlzKClcbiAgZm9yIChpIGluIDAuLk0pIHtcbiAgICAgIGFycmF5TGlzdC5yZW1vdmVBdCgzMClcbiAgfVxuICB2YWwgZW5kVGltZTEgPSBTeXN0ZW0uY3VycmVudFRpbWVNaWxsaXMoKVxuICB2YWwgc3RhcnRUaW1lMiA9IFN5c3RlbS5jdXJyZW50VGltZU1pbGxpcygpXG4gIGZvciAoaSBpbiAwLi5NKSB7XG4gICAgICBsaW5rZWRMaXN0LnJlbW92ZUF0KDMwKVxuICB9XG4gIHZhbCBlbmRUaW1lMiA9IFN5c3RlbS5jdXJyZW50VGltZU1pbGxpcygpXG4gIHByaW50bG4oXCJbQXJyYXlMaXN0XSAke2VuZFRpbWUxIC0gc3RhcnRUaW1lMX0gbXNcIilcbiAgcHJpbnRsbihcIltMaW5rZWRMaXN0XSAke2VuZFRpbWUyIC0gc3RhcnRUaW1lMn0gbXNcIilcblxufSIsInBsYXRmb3JtIjoiamF2YSIsImFyZ3MiOiIifQ==)

[^2]: ArrayList では連続したメモリ領域に格納されるため、読み込み効率が高いため。
