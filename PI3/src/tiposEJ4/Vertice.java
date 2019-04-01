package tiposEJ4;

public class Vertice {
	private String nombre;

	public static Vertice create(String[] tokens) {
		return new Vertice(tokens[0]);
	}

	public static Vertice create() {
		return new Vertice("");
	}

	public static Vertice create(String nombre) {
		return new Vertice(nombre);
	}

	public Vertice(String nombre) {
		super();
		this.nombre = nombre;

	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Vertice other = (Vertice) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getNombre();
	}

}
