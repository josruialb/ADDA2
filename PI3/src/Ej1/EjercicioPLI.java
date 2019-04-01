package Ej1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import us.lsi.common.Streams2;
import us.lsi.lpsolve.solution.AlgoritmoPLI;
import us.lsi.lpsolve.solution.SolutionPLI;

public class EjercicioPLI {
	private static final Integer MAX_BOMB= 6, VMIN = 1;
	public static void main(String[] args) {
		List<Ciudad> escenario = cargaDatos("./ficheros/vecino.txt");
		String def = definirProblema(escenario);

		SolutionPLI alg = AlgoritmoPLI.getSolution(def);
		System.out.println("Valor resultante de  F.O): " + alg.getGoal());
		System.out.println("Valor variables:" + Arrays.toString(alg.getSolution()));
	}
	private static List<Ciudad> cargaDatos(String f) {
		return Streams2.fromFile(f).map(Ciudad::create).collect(Collectors.toList());
	}


	private static String definirProblema(List<Ciudad> escenario) {
		String res = defFuncObj(escenario);
		res = res + restBombMin(escenario);
		return res + restVbles(escenario);
	}
	private static String restVbles(List<Ciudad> e) { 
		return IntStream.range(0, e.size()).boxed()// ya que da valores int pequeños los boxeamos
				.map(i->"b" +i+ "+"+ e.get(i).getVecinos().replaceAll("-","+") + ">=" + VMIN)
				.collect(Collectors.joining(";\n", "", ";"));
	
	}
		private static String restBombMin(List<Ciudad> lo) {
			return IntStream.range(0, lo.size()).boxed()
					.map(i -> "b" + i )
					.collect(Collectors.joining("+", "", "<="  + MAX_BOMB+ ";\n"));
		}
	
	private static String defFuncObj(List<Ciudad> lo) {
		return IntStream.range(0, lo.size()).boxed().map(i -> "b" + i )
				.collect(Collectors.joining("+", "min:", ";\n"));
	}



	
}
