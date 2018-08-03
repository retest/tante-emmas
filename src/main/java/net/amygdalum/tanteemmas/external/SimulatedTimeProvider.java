package net.amygdalum.tanteemmas.external;

import net.amygdalum.tanteemmas.sources.TimeProvider;

public class SimulatedTimeProvider implements TimeProvider{

	private final long hours;
	private final long speed;

	public SimulatedTimeProvider() {
		this.hours = 0;
		this.speed = 1;
	}

	@Override
	public void setSpeed(long speed) {
		// do nothing
	}

	@Override
	public long getSpeed() {
		return speed;
	}

	@Override
	public long hours() {
		return hours;
	}

	@Override
	public long days() {
		return hours() / 24;
	}

}
