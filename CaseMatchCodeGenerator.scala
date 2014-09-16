class CaseClass(nVars: Int) {
	def argLists(level: Int = 0): List[String] = {
		if (level == (nVars-1)) {
			List(
				s"x$level: Int",
				s"x$level: String"
			)
		} else {
			val restArgz = argLists(level + 1)
			restArgz.flatMap( restArgs =>
				List(
					s"x$level: Int, $restArgs",
					s"x$level: String, $restArgs"
				)
			)
		}
	}

	def caseClassDeclarations = argLists().zipWithIndex.map{
		case(argList, i) => s"	case class CC$i($argList) extends CC"
	}

	def caseClassMatch = argLists().zipWithIndex.map{ case(argList, i) => {
			val arguments = Range(0, nVars).map(i => s"x$i")reduce(_ + ", " + _)
			s"	case CC$i($arguments) => $i"
		}
	}

	def declarations() = {
"""
sealed abstract class CC

""" + caseClassDeclarations.reduce(_ + "\n" + _)
	}

	def caseMatch() = {
		val caseDefinition =  List.fill(nVars)("0").reduce(_ + ", " + _)

		val matches = caseClassMatch.reduce(_ + "\n" + _)

s"""
val cc: CC = CC0($caseDefinition)

val x: Int = cc match {
$matches
}
"""
	}
}

object CaseMatchCodeGenerator {
	def main(args: Array[String]) {
	val className = args(0)
	val caseClass = new CaseClass(args(1).toInt)
	val caseMatch = caseClass.caseMatch()
	val declarations = caseClass.declarations()

	println(s"""
object $className {
	def main(args: Array[String]) {
$declarations

$caseMatch
	}
}
""")
	}
}
