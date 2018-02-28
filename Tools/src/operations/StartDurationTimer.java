package operations;

import java.util.Timer;
import java.util.logging.Logger;

import gui.tabs.Tab1;
import timertasks.ShutDownTask;

public class StartDurationTimer
{
	private static final Logger LOG = Logger.getLogger(StartDurationTimer.class.getName());

	private final Timer timer;

	public StartDurationTimer(final Tab1 tab, final String unitString, final String timeString, final int cdH,
			final int cdMin, final int cdS, final int durationInSec)
	{
		StartDurationTimer.LOG.finer(cdH + " " + cdMin + " " + cdS);
		timer = new Timer();
		timer.schedule(new ShutDownTask(cdH, cdMin, cdS, tab), 0, 1000);
		new ShutDown(durationInSec);
		// TODO tab.getCountDownTimePanel(tab.getTargetTime());
	}

	public Timer getTimer()
	{
		return timer;
	}

}
