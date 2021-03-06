package operations;

import java.io.IOException;
import java.util.logging.Logger;

import constants.Messages;

public class ShutDown
{
	private static final Logger LOG = Logger.getLogger(ShutDown.class.getName());

	public ShutDown(final int tNow)
	{
		final String cmd = "shutdown -s -t " + tNow;
		ShutDown.LOG.finer(Messages.SHUTDOWN_MESSAGE);
		ShutDown.LOG.info("Command ->" + cmd);
		try
		{
			Runtime.getRuntime().exec(cmd);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
			ShutDown.LOG.severe(e.getMessage());
		}
	}
}
