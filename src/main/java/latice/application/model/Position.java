package latice.application.model;

import java.util.Objects;

public class Position {
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		return Objects.equals(x, other.x) && Objects.equals(y, other.y);
	}


	private Integer x;
	private Integer y;
	
	
	public Position(final Integer x, final Integer y){
		this.x = x;
		this.y = y;
	}


	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}


	public Integer x() {
		return x;
	}


	public Integer y() {
		return y;
	}
}
