class Int1(x: Int)
class Int2(x: Int)

object HelloWorld {
	val x  = 0
	implicit val y1: Int1 = new Int1(0)
	implicit val y2: Int2 = new Int2(0)

	def f(x: Int)(implicit y1: Int1, y2: Int2): Int = {
		0
	}

	def main(args: Array[String]) {
		f(x)
		println("Hello World")
	}
}
