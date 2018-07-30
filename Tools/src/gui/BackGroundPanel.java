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

public class BackGroundPanel extends JPanel
{
	private static final Logger LOG = Logger.getLogger(BackGroundPanel.class.getName());
	private static final long serialVersionUID = 1L;
	Image img = null;

	// public BackGroundPanel()
	// {
	// final MediaTracker mt = new MediaTracker(this);
	// final String path =
	// this.getClass().getClassLoader().getResource(Paths.BACKGROUND2).toString()
	// .replace("file:/", "").replace("%20", " ");
	//
	// BackGroundPanel.LOG.info("Imagepath: " + path);
	//
	// final File f = new File(path);
	// if (f.exists())
	// {
	// img = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
	// mt.addImage(img, 0);
	// try
	// {
	// mt.waitForAll();
	// }
	// catch (final InterruptedException e)
	// {
	// BackGroundPanel.LOG.severe(e.getMessage());
	// }
	//
	// int w, h;
	// w = (int) (img.getWidth(null) * 0.3);
	// h = (int) (img.getHeight(null) * 0.3);
	// setPreferredSize(new Dimension(w, h));
	//
	// BackGroundPanel.LOG.info("Image-Dimension: " + w + "x" + h);
	// }
	//
	// }

	public BackGroundPanel(String path)
	{

		final MediaTracker mt = new MediaTracker(this);
		LOG.info("Raw path: " + path);
		URL res = BackGroundPanel.class.getResource(path);
		if (res == null)
		{
//			path = path.replace(path.substring(0, path.indexOf("images")), "").replace("\\", "/");
			path = path.replace("\\", "/");
			LOG.info("Replaced path: " + path);
			path = BackGroundPanel.class.getResource(path).toString().replace("file:/", "").replace("%20",
					" ");
		}
		else
		{
			path = BackGroundPanel.class.getResource(path).toString().replace("file:/", "").replace("%20",
					" ");
		}
		BackGroundPanel.LOG.info("Imagepath: " + path);

		final File f = new File(path);
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
				BackGroundPanel.LOG.severe(e.getMessage());
			}

			int w, h;
			w = (int) (img.getWidth(null) * 0.3);
			h = (int) (img.getHeight(null) * 0.3);
			setPreferredSize(new Dimension(w, h));

			// BackGroundPanel.LOG.info("Image-Dimension: " + w + "x" + h);
		}
		// else
		// {
		// BackGroundPanel.LOG.info(path + " existiert nicht");
		// }

	}

	@Override
	protected void paintComponent(final Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
