package operations;

import java.io.IOException;
import java.util.Timer;

public class StopTimer
{
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
