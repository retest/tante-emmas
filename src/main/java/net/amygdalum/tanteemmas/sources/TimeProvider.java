package net.amygdalum.tanteemmas.sources;

public interface TimeProvider {

	long hours();

	long days();

	void setSpeed(long speed);

	long getSpeed();

}
