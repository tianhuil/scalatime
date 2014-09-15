import math._

object ImplicitCodeGen {
	def function(name: String, nValues: Int, nImplicits: Int): String = {
		val args = Range(0, nValues).toList
			.map(i => s"x$i: Int")
			.reduce(_ + ", " + _)

		val implicits = Range(0, nImplicits).toList
			.map(i => s"y$i: Int$i")
			.reduce(_ + ", " + _)

		val values = Range(0, nValues).toList
			.map(i => s"x$i")
			.reduce(_ + ", " + _)
		s"""
		def $name($args)(implicit $implicits): Int = {
			0
		}
		$name($values)
		"""
	}

	def implicit_(i: Int): String = {
		s"""
class Int$i(x: Int)
implicit val y$i = new Int$i(0)
		"""
	}


	def main(args: Array[String]) {
		val className = args(0)
		val nValues = args(1).toInt
		val nImplicits = args(2).toInt
		val nFunctions = args(3).toInt

		val values = Range(0, nValues + 50).toList
			.map(i => s"val x$i = 0")
			.reduce(_ + "\n " + _)

		val implicits = Range(0, nImplicits + 50).toList
			.map(i => implicit_(i))
			.reduce(_ + "\n " + _)

		val functions = Range(0, nFunctions).toList
			.map(i => function(s"f$i", nValues, nImplicits))
			.reduce(_ + "\n\n " + _)

		println(s"""
object $className {
	def main(args: Array[String]) {
		// values
		$values

		// implicits
		$implicits

		// functions
		$functions
	}
}
""")
	}
}
