package shutDownTimer.operations;

import java.util.Timer;
import java.util.TimerTask;

import shutDownTimer.gui.tabs.Tab1;

public class StartDurationTimer
{
	public int cdH, cdMin, cdS;
	private final Timer timer = new Timer();

	public StartDurationTimer(final Tab1 tab, final String unitString, final String timeString, final int cdH, final int cdMin,
			final int cdS, final int duration)
	{
		StartDurationTimer.this.cdH = cdH;
		StartDurationTimer.this.cdMin = cdMin;
		StartDurationTimer.this.cdS = cdS;

		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				tab.setCountDownText(StartDurationTimer.this.cdH + " Stunden " + StartDurationTimer.this.cdMin
						+ " Minuten " + StartDurationTimer.this.cdS + " Sekunden");
				tab.revalidate();
				if (--StartDurationTimer.this.cdS < 0)
				{
					StartDurationTimer.this.cdS = 59;
					if (--StartDurationTimer.this.cdMin < 0)
					{
						StartDurationTimer.this.cdMin = 59;
						if (--StartDurationTimer.this.cdH < 0) timer.cancel();
					}
				}

			}
		}, 0, 1000);

		tab.setCountDownText(duration + " " + unitString);
		// tab.getCountDownTimePanel(tab.getTargetTime());
		new ShutDown(duration);
		tab.revalidate();

	}

	public Timer getTimer()
	{
		return timer;
	}

}
