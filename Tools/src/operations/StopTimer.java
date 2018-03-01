package operations;

import java.io.IOException;
import java.util.Timer;

public class StopTimer
{
	/** Bricht den <i>windows</i>-timer und den Timer als <i>Objekt</i> ab
	 * 
	 * @param timer
	 */
	public StopTimer(final Timer timer)
	{
		try
		{
			Runtime.getRuntime().exec("shutdown -a");
			timer.cancel();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

}
