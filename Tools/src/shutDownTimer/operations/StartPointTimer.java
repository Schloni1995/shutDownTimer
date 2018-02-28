package shutDownTimer.operations;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import shutDownTimer.gui.tabs.Tab2;

public class StartPointTimer
{
	private static final Logger LOG = Logger.getLogger(StartPointTimer.class.getName());
	public int cdH, cdMin, cdS;
	private final Timer timer;

	/** Kontruktor für den Zeitpunkt des Herunterfahrens
	 *
	 * @param tab
	 * @param targetTime
	 */
	public StartPointTimer(final Tab2 tab, final LocalDateTime targetTime)
	{
		StartPointTimer.LOG.fine("Timer gestartet");
		timer = new Timer();
		timer.schedule(getTask(), 0, 1000);

		StartPointTimer.LOG.finer("Rechner fährt herunter");
		// new ShutDown(0);
	}

	/** Gibt die Aufgabe des Timers zurück
	 *
	 * @return TimerTask tt */
	public TimerTask getTask()
	{
		final TimerTask tt = new TimerTask()
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

	/** Gibt den Timer zurück
	 *
	 * @return Timer timer */
	public Timer getTimer()
	{
		return timer;
	}

}
