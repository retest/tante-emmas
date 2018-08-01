package net.amygdalum.tanteemmas.external;

public class SimulatedTimeProvider {

	private final long hours;
	private final long speed;

	public SimulatedTimeProvider() {
		this.hours = 0;
		this.speed = 1;
	}

	public void setSpeed(long speed) {
		// do nothing
	}

	public long getSpeed() {
		return speed;
	}

	public long hours() {
		return hours;
	}

	public long days() {
		return hours() / 24;
	}

}
