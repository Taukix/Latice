package latice.application.model;

public enum Constants {
	BOARD_SIZE(9), RACK_SIZE(5);
	
	
	private int value;
	
	private Constants(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
