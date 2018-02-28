package operations;

import java.time.LocalDateTime;

public class TimeDiff
{
	private int cdH, cdMin, cdS;

	public TimeDiff(final LocalDateTime targetTime, final LocalDateTime now)
	{
		setCdH(targetTime.getHour() - now.getHour());
		setCdMin(targetTime.getMinute() - now.getMinute());
		setCdS(targetTime.getSecond() - now.getSecond());
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
