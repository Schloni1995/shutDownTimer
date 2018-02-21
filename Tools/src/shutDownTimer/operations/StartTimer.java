package shutDownTimer.operations;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import shutDownTimer.tabs.Tab1;

public class StartTimer
{
	public int cdH, cdMin, cdS;
	private final Timer timer = new Timer();

	public StartTimer(final Tab1 tab, final int response, final String unit, final String time, final int cdH,
			final int cdMin, final int cdS, final int tNow)
	{
		StartTimer.this.cdH = cdH;
		StartTimer.this.cdMin = cdMin;
		StartTimer.this.cdS = cdS;
		if (response == JOptionPane.YES_OPTION)
		{
			timer.schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					tab.setCountDownText(StartTimer.this.cdH + " Stunden " + StartTimer.this.cdMin + " Minuten "
							+ StartTimer.this.cdS + " Sekunden");
					tab.revalidate();
					if (--StartTimer.this.cdS < 0)
					{
						StartTimer.this.cdS = 59;
						if (--StartTimer.this.cdMin < 0)
						{
							StartTimer.this.cdMin = 59;
							if (--StartTimer.this.cdH < 0) timer.cancel();
						}
					}

				}
			}, 0, 1000);
			try
			{
				tab.setCountDownText(tNow + " " + unit);
				// tab.getCountDownTimePanel(tab.getTargetTime());
				Runtime.getRuntime().exec("shutdown -s -t " + tNow);
				tab.revalidate();
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}
		}
		else
			JOptionPane.showMessageDialog(tab, "Pc wird nicht heruntergefahren");
	}

	public Timer getTimer()
	{
		return timer;
	}

}
