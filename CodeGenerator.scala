import math._

sealed abstract class Monad {
	val name: String
	def define: String
	def forComprehension: String
}

case class MyOption(name: String) extends Monad {
	override def define = s"val $name = None"
	override def forComprehension = s"i$name <- $name.toList"
}
case class MyList(name: String) extends Monad {
	override def define = s"val $name = Nil"
	override def forComprehension = s"i$name <- $name"
}

class Monads(n: Int) {
	def randInt(max: Int): Int = {
		return (math.random * max).toInt
	}

	val data: List[Monad] = Range(0, n).toList.map{ i =>
		randInt(2) match {
			case 0 => MyOption("v" + i.toString)
			case _ => MyList("v" + i.toString)
		}
	}

	def define = data.map(_.define).reduce(_ + "\n" + _)
	def forComprehension = """
val x = for {
	%s
} yield {
	1 + 1
}
	""".format(data.map(_.forComprehension).reduce(_ + "\n" + _))
}

object CodeGenerator {
	val base = """
object TestCode {
	def main(args: Array[String]) {
		%s

		%s
	}
}
"""

	def main(args: Array[String]) {
		val monads = new Monads(23)
		println(base.format(monads.define, monads.forComprehension))
	}
}
