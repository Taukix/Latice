package latice.application.model;

public enum Shape {
	BIRD("BIRD"), FEATHER("FEATHER"), TURTLE("TURTLE"), DOLPHIN("DOLPHIN"), FLOWER("FLOWER"), LIZARD("LIZARD");
	
	private String symbol;
	
	Shape(final String symbol){
		this.symbol = symbol;
	}
}
