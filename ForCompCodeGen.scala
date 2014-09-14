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

class Monads(funcname: String, n: Int) {
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
	def codeblock = s"""
def $funcname(): Int = {
	%s

	%s

	return 1
}
""".format(define, forComprehension)
}

object CodeGenerator {
	val base = """
object %s {
	def main(args: Array[String]) {
		%s
	}
}
"""

	def main(args: Array[String]) {
		val className = args(0)
		val depth = args(1).toInt
		val repeat = args(2).toInt
		println(base.format(
			className,
			Range(0,repeat).toList.map{ rep =>
				new Monads("f%d".format(rep), depth).codeblock
			}.reduce(_ + "\n\n" + _)
		))
	}
}
