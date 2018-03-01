package operations;

public class Converter
{
	private float cdH, cdMin, cdS;
	private float durInS;

	public Converter(final String unit, final float durationInSec)
	{
		this.durInS = durationInSec;
		switch (unit)
		{
			case "Stunden":
				cdH = durInS;
				cdMin = 0;
				cdS = 0;
				durInS *= 3600;
				break;
			case "Minuten":
				cdH = durInS / 60;
				cdMin = durInS % 60;
				cdS = 0;
				durInS *= 60;
				break;
			case "Sekunden":
				cdH = durInS / 3600;
				cdMin = durInS / 60;
				cdS = durInS % 60;
				break;
			default:
				cdH = 0;
				cdMin = 0;
				cdS = 0;
				break;
		}
	}

	/** @return the cdH */
	public float getCdH()
	{
		return cdH;
	}

	/** @return the cdMin */
	public float getCdMin()
	{
		return cdMin;
	}

	/** @return the cdS */
	public float getCdS()
	{
		return cdS;
	}

	/** @return the durInS */
	public float getDurationInSec()
	{
		return durInS;
	}

}
