package operations;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeDiff
{
	private int cdH, cdMin, cdS;

	public TimeDiff(final LocalDateTime targetTime, final LocalDateTime now)
	{
		final Duration dur = Duration.between(now, targetTime);

		// total seconds of difference (using Math.abs to avoid negative values)
		long seconds = Math.abs(dur.getSeconds());
		final long hours = seconds / 3600;
		seconds -= (hours * 3600);
		final long minutes = seconds / 60;
		seconds -= (minutes * 60);
		
		setCdH(Math.toIntExact(hours));
		setCdMin(Math.toIntExact(minutes));
		setCdS(Math.toIntExact(seconds));
	}

	/** @return the cdH */
	public int getCdH()
	{
		return cdH;
	}

	/** @return the cdMin */
	public int getCdMin()
	{
		return cdMin;
	}

	/** @return the cdS */
	public int getCdS()
	{
		return cdS;
	}

	/** @param cdH
	 *            the cdH to set */
	public void setCdH(final int cdH)
	{
		this.cdH = cdH;
	}

	/** @param cdMin
	 *            the cdMin to set */
	public void setCdMin(final int cdMin)
	{
		this.cdMin = cdMin;
	}

	/** @param cdS
	 *            the cdS to set */
	public void setCdS(final int cdS)
	{
		this.cdS = cdS;
	}

}
