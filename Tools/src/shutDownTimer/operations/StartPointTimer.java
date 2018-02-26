package shutDownTimer.operations;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import shutDownTimer.gui.tabs.Tab2;

public class StartPointTimer
{
	private static final Logger log = Logger.getLogger(StartPointTimer.class.getName());
	public int cdH, cdMin, cdS;
	private Timer timer;

	public StartPointTimer(final Tab2 tab)
	{
		timer = new Timer();
		timer.schedule(getTask(), 0, 1000);

		// new ShutDown(0);
	}

	private TimerTask getTask()
	{
		TimerTask tt = new TimerTask()
		{
			@Override
			public void run()
			{
				// TODO ShowTimeDiff
				// TODO if (time == cal.getTime()) timer.cancel();
			}
		};
		return tt;
	}

	public Timer getTimer()
	{
		return timer;
	}

}
