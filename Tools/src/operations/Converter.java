package operations;

public class Converter
{
	private int cdH , cdMin , cdS;
	private int durInS;
	
	public Converter(String unit, int durationInSec)
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

	/**
	 * @return the cdH
	 */
	public int getCdH()
	{
		return cdH;
	}

	/**
	 * @return the cdMin
	 */
	public int getCdMin()
	{
		return cdMin;
	}

	/**
	 * @return the cdS
	 */
	public int getCdS()
	{
		return cdS;
	}

	/**
	 * @return the durInS
	 */
	public int getDurationInSec()
	{
		return durInS;
	}

}
