package latice.application.model;

public enum Shape {
	BIRD("BI"), FEATHER("FE"), TURTLE("T"), DOLPHIN("D"), FLOWER("FL"), LIZARD("L");
	
	private String symbol;
	
	Shape(final String symbol){
		this.symbol = symbol;
	}
}
