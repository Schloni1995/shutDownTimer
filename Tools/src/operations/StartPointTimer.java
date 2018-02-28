package operations;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.logging.Logger;

import gui.tabs.Tab2;
import timertask.ShutDownTask;

public class StartPointTimer
{
	private final static LocalDateTime LDT_NOW = LocalDateTime.now();
	private static final Logger LOG = Logger.getLogger(StartPointTimer.class.getName());
	public int cdH, cdMin, cdS;
	private final TimeDiff timeDiff;
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
		timeDiff = new TimeDiff(targetTime, StartPointTimer.LDT_NOW);

		cdH = timeDiff.getCdH();
		cdMin = timeDiff.getCdMin();
		cdS = timeDiff.getCdS();
		

		timer.schedule(new ShutDownTask(cdH, cdMin, cdS, tab), 0, 1000);

		StartPointTimer.LOG.finer("Rechner fährt herunter");
		// new ShutDown(0);
	}

	/** Gibt den Timer zurück
	 *
	 * @return Timer timer */
	public Timer getTimer()
	{
		return timer;
	}

}
