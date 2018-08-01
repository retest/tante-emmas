package net.amygdalum.tanteemmas.external;

public class SimulatedTimeProvider {

	private long hours;
	private long speed;

	public SimulatedTimeProvider() {
		this.hours = 0;
		this.speed = 1;
	}

	public void setSpeed(long speed) {
		if (speed <= 0) {
			speed = 1;
			return;
		}
		this.speed = speed;
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
