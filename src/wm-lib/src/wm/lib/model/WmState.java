package wm.lib.model;

public enum WmState {
	QUALIFICATION(-1), ROUND_OF_16(0), QUARTER_FINAL(1), SEMI_FINAL(2), FINAL(3);

	private final int id;

	WmState(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public static WmState toWmState(int id) {
		switch (id) {
		case 0:
			return ROUND_OF_16;
		case 1:
			return QUARTER_FINAL;
		case 2:
			return SEMI_FINAL;
		case 3:
			return FINAL;

		default:
			return QUALIFICATION;
		}
	}
}
