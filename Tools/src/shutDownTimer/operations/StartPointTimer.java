package shutDownTimer.operations;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import shutDownTimer.tabs.Tab2;

public class StartPointTimer
{
	public int cdH, cdMin, cdS;
	private final Timer timer = new Timer();

	public StartPointTimer(final Tab2 tab, final Date time)
	{
		Calendar cal = Calendar.getInstance();
		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				//TODO ShowTimeDiff
				if (time == cal.getTime())
				{
					this.cancel();
				}
			}
		}, 0, 1000);
		try
		{
			int tNow = 0;
			Runtime.getRuntime().exec("shutdown -s -t " + tNow);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

	}

	public Timer getTimer()
	{
		return timer;
	}

}
