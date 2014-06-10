package tr.edu.ege.seagent.timer;

import java.util.concurrent.TimeUnit;

public class TimeWatch {

	long start;

	public static TimeWatch start() {
		return new TimeWatch();
	}

	private TimeWatch() {
		reset();
	}

	public TimeWatch reset() {
		start = System.currentTimeMillis();
		return this;
	}

	public long time() {
		long ends = System.currentTimeMillis();
		return ends - start;
	}

	public long time(TimeUnit unit) {
		return unit.convert(time(), TimeUnit.MILLISECONDS);
	}
}
