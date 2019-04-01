package Ej1;

public class Ciudad {

	private String barrios;
	private String vecinos;

	public static Ciudad create(String s) {
		return new Ciudad(s);
	}

	private Ciudad(String s) {
		String[] t = s.split(",");
		barrios = t[0];
		vecinos = t[1];

	}

//	public static List<Integer> listaVecinos(String s) {
//		Integer i = 0;
//		List<Integer> l1= new ArrayList<>();
//		String[] t= s.split("-");
//	for (i = 0; i < t.length; i++) {
//		l1.add(Integer.parseInt(t[i]));
//	}
//		
//		return l1;

	public String getBarrios() {
		return barrios;
	}

	public String getVecinos() {
		return vecinos;

	}

}
