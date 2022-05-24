package latice.application.model;

public enum ConstantPosition {
	CENTER(5,9);
	
	private Position pos;
	
	private ConstantPosition(int x, int y) {
		this.pos = new Position(x, y);
	}
	
	public Position pos() {
		return this.pos;
	}
}
