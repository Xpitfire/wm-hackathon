package wm.lib.model;

public enum WmState {
	QUALIFICATION(5), ROUND_OF_16(4), QUARTER_FINAL(3), SEMI_FINAL(2), FINAL(1);

	private final int id;

	WmState(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public static WmState toWmState(int id) {
		switch (id) {
		case 4:
			return ROUND_OF_16;
		case 3:
			return QUARTER_FINAL;
		case 2:
			return SEMI_FINAL;
		case 1:
			return FINAL;

		default:
			return QUALIFICATION;
		}
	}
}
