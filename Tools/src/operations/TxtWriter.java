package operations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import constants.Paths;

public class TxtWriter
{
	public static void createTxtFile(final File zielPfad, final String imagePath)
	{
		if (zielPfad.exists())
		{
			PrintWriter pWriter = null;
			try
			{
				pWriter = new PrintWriter(new BufferedWriter(new FileWriter(zielPfad)));
				pWriter.println("Imagepath: " + imagePath);
				pWriter.println();

			}
			catch (final IOException ioe)
			{
				ioe.printStackTrace();
			}
			finally
			{
				if (pWriter != null)
				{
					pWriter.flush();
					pWriter.close();
				}
			}
		}
		else
		{
			File f = new File(zielPfad.getParent());
			if (f.mkdir()) createTxtFile(new File(Paths.SETTINGS), imagePath);
			else JOptionPane.showMessageDialog(null, "Erstelle bitte die entsprechende Ordnerstruktur:\r\n"+Paths.SETTINGS+"\r\nVersuch es nochmal!", "Zielpfad konnte nicht erstellt werden",JOptionPane.ERROR_MESSAGE);
		}

	}
}
