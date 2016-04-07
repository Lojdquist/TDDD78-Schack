public enum  State{
    TESTING(false);

    private boolean state;

    State(boolean state) {
	this.state = state;
    }

    public boolean getState() {
	return state;
    }

    public void setState(boolean state) {
	this.state = state;
    }
}
