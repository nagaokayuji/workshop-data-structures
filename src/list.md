# List

順序付きのリストです。使う機会が多いと思います。

```kotlin
interface List<out E> : Collection<E>
```

一般的なArrayList, LinkedList を取り上げます。

## ArrayList

一般的な配列とほぼ同じですが、サイズが可変で末尾への追加が容易です。

要素を追加すると、内部では自動で配列のサイズが拡張されます。

各操作の計算量は以下です。

- i番目へのアクセス
    - $O(1)$
    - 速い
- 末尾へ追加
    - $O(1)$
    - 便利
- 値の検索
    - 順番にループを回して比較していく必要がある
    - 最悪計算量は $O(N)$
    - $O(N^2)$以上の実装になりがちなので注意 （後述）
- i 番目の要素を削除
    - $O(N)$
    - やらないほうが良い

## LinkedList

ArrayList の弱点である挿入や削除に強いデータ構造です。

各要素には「前の要素や後ろの要素が何か」の情報だけを持たせることにより、挿入や削除の処理を簡潔に実現しています。

![https://res.cloudinary.com/ddaz9etkx/image/upload/v1628649602/ot/taiiku_maehe_narae_chiisaku_z6zval.png](https://res.cloudinary.com/ddaz9etkx/image/upload/v1628649602/ot/taiiku_maehe_narae_chiisaku_z6zval.png)

このとき、「自分が全体の何番目の要素か」という情報は管理されないことに注意してください。i 番目の要素にアクセスするためには、先頭（または末尾）から順にたどっていく必要があります。

- i番目へのアクセス
    - $O(N)$
    - 順にたどっていく必要があるため、遅い
    - 軽い気持ちで使ってはいけない

    [軽い気持ちでLinkedListを使ったら休出する羽目になった話 - Qiita](https://qiita.com/neko_machi/items/d620c4a8958e74df3550)

- 特定の位置に挿入
    - $O(1)$
- i 番目の要素を削除
    - $O(1)$
- 値の検索
    - $O(N)$
    - ArrayList と同様
