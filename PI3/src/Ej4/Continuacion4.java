package Ej4;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import tiposEJ4.Arista;
import tiposEJ4.Vertice;
import us.lsi.graphs.GraphsReader;

public class Continuacion4 {

// http://www.webgraphviz.com/
//	A: ¿Están todos los lugares conectados entre sí?
//  B: ¿Cuáles son los lugares que pueden visitarse inicialmente sin haber visitado otros anteriormente?
//	C: Dado un subconjunto de los monumentos en un cierto orden, comprobar que cumple las restricciones de precedencia
// y encontrar en tal caso el camino de tiempo mínimo que respete ese orden.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph<Vertice, Arista> g = cargarGrafo("./ficheros/p4.txt");
		Graph<Vertice, Arista> g2 = cargarGrafo2("./ficheros/p4-2.txt");

		g.edgeSet().forEach(a -> g.setEdgeWeight(a, a.getDuracion())); // repesar aristas grafo pesos
		apartadoA(g);
		apartadoB(g2);
		apartadoC(g2,g, "Sitio0", "Sitio2", "Sitio4");
		apartadoC(g2,g, "Sitio5", "Sitio7", "Sitio9");
		apartadoC(g2,g, "Sitio3", "Sitio5", "Sitio4");
	}

	private static void apartadoA(Graph<Vertice, Arista> g) { 

		System.out.println("========================================================================= APARTADO A =========================================================================");

		ConnectivityInspector<Vertice, Arista> conIns = new ConnectivityInspector<>(g);
		boolean monumentosConectados = conIns.isConnected();
		List<Set<Vertice>> monConectados = conIns.connectedSets();

		System.out.println("¿Están todos los lugares conectados entre sí?: " + monumentosConectados);
		System.out.println(monConectados);
	}

	private static void apartadoB(Graph<Vertice, Arista> g2) {
		System.out.println("========================================================================= APARTADO B =========================================================================");

		List<Vertice> l1 = new ArrayList<>(g2.vertexSet());
		List<Vertice> listaGrados = new ArrayList<>();

		for (int i = 0; i < g2.vertexSet().size() - 1; i++) {
			if (g2.inDegreeOf(l1.get(i)) == 0) {
				listaGrados.add(l1.get(i));
	
			}
		}

		System.out.println(
				"¿Cuáles son los lugares que pueden visitarse inicialmente sin haber visitado otros anteriormente?: "
						+ listaGrados);

	}

	private static void apartadoC(Graph<Vertice, Arista> g2, Graph<Vertice, Arista> g,String o, String i, String d) {
		System.out.println(
				"========================================================================= APARTADO C =========================================================================");
		try {
			ShortestPathAlgorithm<Vertice, Arista> alg = new DijkstraShortestPath<>(g2); //precedencias
			ShortestPathAlgorithm<Vertice, Arista> algpesos = new DijkstraShortestPath<>(g); //base

			Vertice from = Vertice.create(o);
			Vertice inter = Vertice.create(i);
			Vertice to = Vertice.create(d);
			//grafo precedencias 
			GraphPath<Vertice, Arista> gp = alg.getPath(from, inter); // (from,inter, to);
			GraphPath<Vertice, Arista> gp1 = alg.getPath(inter, to);
            // grafo base
			GraphPath<Vertice, Arista> gpesos = algpesos.getPath(from, inter); // (from,inter, to);
			GraphPath<Vertice, Arista> gpesos1 = algpesos.getPath(inter, to);
			
			List<Vertice> l1 = Stream.concat(gp.getVertexList().stream(), gp1.getVertexList().stream()).distinct()
					.collect(Collectors.toList());
			System.out.println("Visita de " + "[" + o + "," + i + "," + d + "]"  +" respetando el orden de menor tiempo"
					+ " ("+((gpesos.getWeight() + gpesos1.getWeight()))+ "mins):\n"+l1);
		} catch (Exception e) {
			System.out.println("No es posible construir según el grafo visitar: " + "[" + o + "," + i + "," + d + "]");

		}
	}

	private static Graph<Vertice, Arista> cargarGrafo(String nombreFichero) { // cargar grafo
		return GraphsReader.newGraph(nombreFichero, Vertice::create, // primer create para ciudad , el largo
				Arista::create, // primero para caretera
				() -> new SimpleWeightedGraph<>(Vertice::create, Arista::create)); // los creates vacios
	}

	private static Graph<Vertice, Arista> cargarGrafo2(String nombreFichero) { // cargar grafo
		return GraphsReader.newGraph(nombreFichero, Vertice::create, // primer create para ciudad , el largo
				Arista::create, // primero para caretera
				() -> new DirectedWeightedMultigraph<>(Vertice::create, Arista::create));
	}
}
//Set<Vertice> conjunto1 = new HashSet<>();
//Graph<Vertice, Arista> subgrafo = AsSubgraph<>(g2,conjunto1);