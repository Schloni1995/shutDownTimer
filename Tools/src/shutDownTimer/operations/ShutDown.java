package shutDownTimer.operations;

import java.io.IOException;

public class ShutDown
{
	public ShutDown(final int tNow)
	{
		try
		{
			Runtime.getRuntime().exec("shutdown -s -t " + tNow);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}
}
