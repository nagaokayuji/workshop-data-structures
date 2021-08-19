const val N:Int = 100000
const val M:Int = 100000
fun main() {
  val originalList = (0..1000000).toList()
  // サイズNのリスト
  val list1 = originalList.shuffled().take(N)
  // サイズMのリスト
  val list2 = originalList.shuffled().take(M)


  val startTime = System.currentTimeMillis()
  
  // 問題の処理
  val list2Set = list2.toHashSet()
  val list1Set = list1.toHashSet()
  val countInList2 = list1Set.intersect(list2Set).size

  val endTime = System.currentTimeMillis()

  println("${countInList2} 件見つかりました。")
  println("[実行時間] ${endTime - startTime} ms")
}