package tiposEJ4;

public class Arista { // caminos
	private static int numObjs = 0;
	private Vertice v1, v2; // vertice = monumentos
	private Double duracion;
	private int id;

	public static Arista create(Vertice c1, Vertice c2, String[] tokens) {
		return new Arista(c1, c2, Double.parseDouble(tokens[2]));
	}

	public static Arista create() {
		return new Arista(null, null, null);

	}

	public Arista(Vertice v1, Vertice v2,  Double duracion) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.duracion = duracion;
		id = ++numObjs;
	}


	public Vertice getV1() {
		return v1;
	}

	public Vertice getV2() {
		return v2;
	}

	public Double getDuracion() {
		return duracion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arista other = (Arista) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String toString() {
		return   getDuracion() + "(minutos)";
	}

}
