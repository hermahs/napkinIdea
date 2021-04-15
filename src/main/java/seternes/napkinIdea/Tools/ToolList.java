package seternes.napkinIdea.Tools;

public enum ToolList {
	HAND(0),
	PENCIL(1),
	BOX(2),
	ELLIPSE(3),
	ERASER(4);
	
	private final int value;
	
	private ToolList(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
