package operations;

import java.io.IOException;
import java.util.logging.Logger;

import constants.Messages;

public class ShutDown
{
	private static final Logger LOG = Logger.getLogger(ShutDown.class.getName());

	public ShutDown(final float tNow)
	{
		LOG.finer(Messages.SHUTDOWN_MESSAGE);
		try
		{
			Runtime.getRuntime().exec("shutdown -s -t " + Math.round(tNow));
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}
}
