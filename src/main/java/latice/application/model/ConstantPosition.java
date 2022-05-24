package latice.application.model;

public enum ConstantPosition {
	CENTER(5,5), LAST_POSITION(9,9), LEFT_BONUS(5,1), TOP_BONUS(1,5), RIGHT_BONUS(5,9), BOTTOM_BONUS(9,5);
	
	private Position pos;
	
	private ConstantPosition(int x, int y) {
		this.pos = new Position(x, y);
	}
	
	public Position pos() {
		return this.pos;
	}
}
