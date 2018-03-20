package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.util.logging.Logger;
import javax.swing.JPanel;

import constants.Paths;

public class BackGroundPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(BackGroundPanel.class.getName());
	Image img = null;

	public BackGroundPanel()
	{
		final MediaTracker mt = new MediaTracker(this);
		
		File f = new File(this.getClass().getClassLoader().getResource(Paths.BACKGROUND2).getFile());

		if (f.exists())
		{
			img = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
			mt.addImage(img, 0);
			try
			{
				mt.waitForAll();
			}
			catch (final InterruptedException e)
			{
				LOG.severe(e.getMessage());
			}

			int w, h;
			w = (int) (img.getWidth(null) * 0.45);
			h = (int) (img.getHeight(null) * 0.45);
			setPreferredSize(new Dimension(w, h));

			LOG.info("Image-Dimension: " + w + "x" + h);
		}

	}

	@Override
	protected void paintComponent(final Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
