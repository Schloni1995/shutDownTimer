package shutDownTimer.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BackGroundPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	Image img = null;

	public BackGroundPanel(final String imagefile)
	{
		if (imagefile != null)
		{
			final MediaTracker mt = new MediaTracker(this);
			img = Toolkit.getDefaultToolkit().getImage(imagefile);
			mt.addImage(img, 0);
			try
			{
				mt.waitForAll();
			}
			catch (final InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void paintComponent(final Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
