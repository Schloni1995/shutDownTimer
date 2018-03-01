/**
 *
 */
package timertask;

import java.util.TimerTask;
import java.util.logging.Logger;

import gui.tabs.Tab1;
import gui.tabs.Tab2;

/** @author Toni Zinke */
public class ShutDownTask extends TimerTask
{
	private static final Logger LOG = Logger.getLogger(ShutDownTask.class.getName());
	private float h;
	private float m;
	private float s;
	private Tab1 tab1;
	private Tab2 tab2;

	/** TimerTaskObjekt - Konstruktor
	 *
	 * @param cdH
	 * @param cdMin
	 * @param cdS
	 * @param tab1
	 */
	public ShutDownTask(final float cdH, final float cdMin, final float cdS, final Tab1 tab1)
	{
		this.tab1 = tab1;
		h = cdH;
		m = cdMin;
		s = cdS;
	}

	/** TimerTaskObjekt - Konstruktor
	 *
	 * @param cdH
	 * @param cdMin
	 * @param cdS
	 * @param tab2
	 */
	public ShutDownTask(final float cdH, final float cdMin, final float cdS, final Tab2 tab2)
	{
		this.tab2 = tab2;
		h = cdH;
		m = cdMin;
		s = cdS;
	}

	@Override
	public void run()
	{
		LOG.info(h + " " + m + " " + " " + s);

		if (tab1 != null) tab1.setCountDownText(h + " Stunden " + m + " Minuten " + s + " Sekunden");
		else if (tab2 != null) tab2.setCountDownText(h + " Stunden " + m + " Minuten " + s + " Sekunden");

		if ((h <= 0) && (m <= 0) && (s == 0)) cancel();
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

}
