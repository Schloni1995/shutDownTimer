package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Paths;
import operations.TxtWriter;

public class BGChangeDialog extends JDialog
{
	private static final long serialVersionUID = 1L;

	public static void main(final String[] args)
	{
		new BGChangeDialog().setVisible(true);
	}

	private JPanel chooseImagePanel;
	private GridBagConstraints gbc;
	private File[] images;

	public BGChangeDialog()
	{
		setModal(true);
		try
		{
			images = getImages(findPathInfo());
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		setTitle("Hintergrund auswählen");
		setContentPane(getChooseImagePanel());
		pack();
	}

	private String findPathInfo() throws IOException
	{
		return new File("").getCanonicalPath().concat("\\src\\images");
	}

	/** @return the chooseImagePanel */
	public JPanel getChooseImagePanel()
	{
		if (chooseImagePanel == null)
		{
			chooseImagePanel = new JPanel();
			chooseImagePanel.setLayout(new GridBagLayout());
		}

		int x = 1;
		final int y = 0;
		for (final File imageFile : images)
		{
			final String imagePath = imageFile.getAbsolutePath();
			final Image img = new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_FAST);
			final ImageIcon icon = new ImageIcon(img);
			final JLabel imageContainer = getImageContainer(icon);
			imageContainer.setName(imagePath);
			setGBC(x++, y);
			chooseImagePanel.add(imageContainer, gbc);
		}
		return chooseImagePanel;
	}

	private JLabel getImageContainer(final ImageIcon icon)
	{
		final JLabel label = new JLabel(icon);
		label.addMouseListener(getMA());
		return label;
	}

	private File[] getImages(final String imagePath)
	{
		return new File(imagePath).listFiles();
	}

	private MouseListener getMA()
	{
		return new MouseAdapter()
		{
			private JLabel l;

			@Override
			public void mouseEntered(final MouseEvent e)
			{
				l = (JLabel) e.getComponent();
				l.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
			}

			@Override
			public void mouseExited(final MouseEvent e)
			{
				l = (JLabel) e.getComponent();
				l.setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void mouseReleased(final MouseEvent e)
			{
				l = (JLabel) e.getComponent();
				TxtWriter.createTxtFile(new File(Paths.SETTINGS),l.getName());

			}
		};
	}

	private void setGBC(int x, int y)
	{
		if (gbc == null)
		{
			gbc = new GridBagConstraints();
			gbc.ipadx = 15;
			gbc.ipady = 15;
		}
		if (x % 6 == 0)
		{
			x = 0;
			y++;
		}

		gbc.gridx = x - 1;
		gbc.gridy = y;
	}
}
