package operations;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.logging.Logger;

import constants.Messages;
import constants.Time;
import gui.tabs.Tab2;
import timertask.ShutDownTask;

public class StartPointTimer
{

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
		LOG.fine(Messages.TIMER_START_MESSAGE);
		timer = new Timer();
		timeDiff = new TimeDiff(targetTime, Time.LDT_NOW);
		cdH = timeDiff.getCdH();
		cdMin = timeDiff.getCdMin();
		cdS = timeDiff.getCdS();
		LOG.info("TimeDiff " + cdH + " Hours " + cdMin + " Minutes " + cdS + " Seconds");
		timer.schedule(new ShutDownTask(cdH, cdMin, cdS, tab), 0, 1000);
		
		//TODO Falsches Kommando
		int duration= (int)new Converter("", (cdH * 3600) + (cdMin * 60) + cdS).getDurationInSec();
		LOG.info("Shutdown in " + duration +" Seconds");
		new ShutDown(duration);
	}

	/** Gibt den Timer zurück
	 *
	 * @return Timer timer */
	public Timer getTimer()
	{
		return timer;
	}

}
