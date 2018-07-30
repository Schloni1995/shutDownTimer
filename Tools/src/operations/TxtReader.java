package operations;

import java.io.IOException;
import java.nio.file.Files;

public class TxtReader
{
	static String value;

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public static String getChosenImagePath(String settingsPfad)
	{
		try
		{
			Files.lines(java.nio.file.Paths.get(settingsPfad)).forEach((l) ->
			{
				String imageKey = "<Imagepath>";
				String line = l;
				if (line.contains(imageKey))
				{
					value = line.replace(imageKey, "");
				}
			});
		}
		catch (IOException e)
		{
			e.printStackTrace();
			
		}
		return value;
	}

}
