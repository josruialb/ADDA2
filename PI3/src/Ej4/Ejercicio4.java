package Ej4;

import java.io.PrintWriter;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.io.IntegerComponentNameProvider;
import tiposEJ4.Arista;
import tiposEJ4.Vertice;
import us.lsi.common.Files2;
import us.lsi.graphs.GraphsReader;

public class Ejercicio4 {

	public static void main(String[] args) {
		Graph<Vertice, Arista> g = cargarGrafo("./ficheros/p4.txt");
		Graph<Vertice, Arista> g2 = cargarGrafo2("./ficheros/p4-2.txt");
		
		exportarGrafo(g, "./ficheros/p4.gv");
		exportarGrafo2(g2, "./ficheros/p4-2.gv");

		System.out.println(g);
		System.out.println(g2);

	}

	private static void exportarGrafo(Graph<Vertice, Arista> g, String nf) { // nf = nombre fichero
		DOTExporter<Vertice, Arista> de = new DOTExporter<Vertice, Arista>(new IntegerComponentNameProvider<>(),
				v -> v.getNombre(), a -> "T=" + a.getDuracion());

		PrintWriter pw = Files2.getWriter(nf);
		de.exportGraph(g, pw);

	}

	private static void exportarGrafo2(Graph<Vertice, Arista> g2, String nf) { // nf = nombre fichero
		DOTExporter<Vertice, Arista> de = new DOTExporter<Vertice, Arista>(new IntegerComponentNameProvider<>(),
				v -> v.getNombre(),
				a -> "");

		PrintWriter pw = Files2.getWriter(nf);
		de.exportGraph(g2, pw);

	}

	private static Graph<Vertice, Arista> cargarGrafo(String nombreFichero) {
		return GraphsReader.newGraph(nombreFichero, Vertice::create, Arista::create,
				() -> new SimpleWeightedGraph<>(Vertice::create, Arista::create));
	}

	private static Graph<Vertice, Arista> cargarGrafo2(String nombreFichero) {
		return GraphsReader.newGraph(nombreFichero, Vertice::create, Arista::create,
				() -> new DirectedMultigraph<>(Vertice::create, Arista::create, false));
	}
}
