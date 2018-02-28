package shutDownTimer.operations;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import shutDownTimer.gui.tabs.Tab1;

public class StartDurationTimer
{
	private static final Logger log = Logger.getLogger(StartDurationTimer.class.getName());
	public int h;
	public int m;
	public int s;
	private final Timer timer;

	public StartDurationTimer(final Tab1 tab, final String unitString, final String timeString, final int cdH,
			final int cdMin, final int cdS, final int durationInSec)
	{
		StartDurationTimer.log.finer(cdH + " " + cdMin + " " + cdS);
		timer = new Timer();
		timer.schedule(getTask(cdH, cdMin, cdS, tab), 0, 1000);
		new ShutDown(durationInSec);
		// TODO tab.getCountDownTimePanel(tab.getTargetTime());
	}

	private TimerTask getTask(final int cdH, final int cdMin, final int cdS, final Tab1 tab)
	{
		h = cdH;
		m = cdMin;
		s = cdS;
		final TimerTask tt = new TimerTask()
		{
			@Override
			public void run()
			{
				StartDurationTimer.log.info(h + " " + m + " " + " " + s);
				tab.setCountDownText(h + " Stunden " + m + " Minuten " + s + " Sekunden");

				if (h <= 0 && m <= 0 && s == 0) timer.cancel();
				if (--s < 0)
				{
					s = 59;
					m--;
				}
				if (m < 0)
				{
					m = 59;
					h--;
				}
			}
		};
		return tt;
	}

	public Timer getTimer()
	{
		return timer;
	}

}
