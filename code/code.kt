import java.util.*

fun main() {
  val startTime = System.currentTimeMillis()
  val randomList = (0..1000).toList().shuffled()

  val h = listOf(1,2,3,4)
  println(h.javaClass.kotlin)
  val j = mutableListOf(1,2,3,4)
  println(j.javaClass.kotlin)

  val al = arrayListOf(1,2,3,4)
  println(al.javaClass.kotlin)
  println(al.javaClass)
  println(List::class.java)
  println(ArrayList::class.java)
  val st:Queue<Int> = LinkedList()
  st.add(13)
  val b = st.peek()
  println(b)

  val ad = ArrayDeque<Int>()
  println(ad.javaClass.kotlin)
  println(ad)
  val valmp = mutableMapOf(1 to 3)
  println(valmp.javaClass.kotlin)
  val ts = TreeSet<Int>()
  println(ts.javaClass.kotlin)
  ts.add(3)
  ts.add(32)
  ts.add(1)
  println(ts.contains(3))
  println(ts.contains(2))
  println(ts.toString())
  val endTime = System.currentTimeMillis()
  ts.forEach{ 
    println(it)
  }

  val list: List<Int> = listOf(13,13,23,11,5,3,21, 1, 1, 2, 3, 5, 8, 13, 21)
  val dedupList: List<Int> = list.toSet().toList()
  println(dedupList)
  println("[実行時間] ${endTime - startTime} ms")
}