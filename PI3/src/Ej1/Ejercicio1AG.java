package Ej1;

import java.util.ArrayList;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import tiposEJ4.Arista;
import tiposEJ4.Vertice;
import us.lsi.ag.IndexChromosome;
import us.lsi.ag.IndexProblemAG;
import us.lsi.graphs.GraphsReader;

public class Ejercicio1AG implements IndexProblemAG<List<Vertice>> {
	private Graph<Vertice, Arista> grafo;
	private List<Vertice> ciudades;
private static Double PESO_TOTAL;
	
	public Ejercicio1AG(String nameF) {
		grafo = cargarGrafo(nameF); 
		ciudades = new ArrayList<Vertice>(grafo.vertexSet());
	PESO_TOTAL = grafo.edgeSet().stream()
			.mapToDouble(Arista::).sum();	
	}
	
	private Graph<Vertice, Arista> cargarGrafo(String nameF) {
		Supplier<Graph<Vertice,Arista>> creator = () -> new SimpleWeightedGraph<>(Vertice::create, Arista::create);
		return GraphsReader.newGraph(nameF,
				Vertice::create,
				Arista::create, 
				creator,
				Arista::getKm);
	}

	@Override
	public Integer getObjectsNumber() {
		
		return grafo.vertexSet().size();
	}

	@Override
	public Double fitnessFunction(IndexChromosome cr) {  //se le pone un menos delante para hacer el minimo
		List<Vertice> sol = getSolucion(cr);
		return -sumaPesos(sol);
	}

	private Double sumaPesos(List<Vertice> cities) {
		return IntStream.range(0, cities.size()-1).boxed().mapToDouble(i->pesoArista(cities.get(i),cities.get(i+1)))
		.sum();
		
	}

	private Double pesoArista(Vertice c1, Vertice c2) {
		if(grafo.containsEdge(c1,c2))
			return grafo.getEdge(c1, c2).getKm();
		else
			return PESO_TOTAL;
		
	}

	@Override
	public List<Vertice> getSolucion(IndexChromosome cr) {
	List<Integer> cromo = cr.decode();
	List<Vertice> res = new ArrayList<>();
	for(Integer i: cromo) {
		res.add(ciudades.get(i));

	}
	res.add(res.get(0));
		return res;
	}

}

}
