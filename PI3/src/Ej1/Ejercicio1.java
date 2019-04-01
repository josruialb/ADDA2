package Ej1;

import java.util.Arrays;

import us.lsi.lpsolve.solution.AlgoritmoPLI;
import us.lsi.lpsolve.solution.SolutionPLI;

public class Ejercicio1 {
/*PI3.1. Estaciones de bomberos: una ciudad está considerando la ubicación de sus estaciones de
bomberos. La ciudad se compone de n barrios. Cada barrio es vecino de otros barrios. Una estación
de bomberos se puede colocar en cualquier barrio bi y es capaz de gestionar los incendios tanto de
dicho barrio bi como de cualquier barrio vecino de bi. El objetivo es minimizar el número de
estaciones de bomberos.
Como ejemplo concreto, teniendo un total de 6 barrios b0...b5, y siendo los grupos de vecinos
de cada barrio {b0, b1}, {b0, b1, b5}, {b2, b3}, {b2, b3, b4}, {b3, b4, b5}, {b1, b4, b5}, la solución
óptima sería establecer dos estaciones de bomberos, en los barrios b1 y b3 */
	
	
	
	
	public static void main(String[] args) {
		SolutionPLI alg=AlgoritmoPLI.getSolutionFromFile("./ficheros/p1.txt");
		System.out.println("Valor resultante de  F.O): "+alg.getGoal()); 
		System.out.println("Valor variables:"+Arrays.toString(alg.getSolution()));
		
	}}