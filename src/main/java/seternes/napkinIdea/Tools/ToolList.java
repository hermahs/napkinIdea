package seternes.napkinIdea.Tools;

public enum ToolList {
	HAND(0),
	PENCIL(1);
	
	private final int value;
	
	private ToolList(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
