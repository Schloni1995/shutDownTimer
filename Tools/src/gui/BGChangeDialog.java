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

public class BGChangeDialog extends JDialog
{
	private File[] images;
	private JPanel chooseImagePanel;
	private GridBagConstraints gbc;

	public static void main(String[] args)
	{
		new BGChangeDialog().setVisible(true);
	}

	public BGChangeDialog()
	{
		setModal(true);
		try
		{
			images = getImages(findPathInfo());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		setTitle("Hintergrund auswählen");
		setContentPane(getChooseImagePanel());
		pack();
	}

	private File[] getImages(String imagePath)
	{
		return new File(imagePath).listFiles();
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

		int x = 1, y = 0;
		for (File imageFile : images)
		{
			String imagePath = imageFile.getAbsolutePath();
			Image img = new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_FAST);
			ImageIcon icon = new ImageIcon(img);
			JLabel imageContainer = getImageContainer(icon);
			setGBC(x++, y);
			chooseImagePanel.add(imageContainer, gbc);
		}
		return chooseImagePanel;
	}

	private JLabel getImageContainer(ImageIcon icon)
	{
		JLabel label = new JLabel(icon);
		label.addMouseListener(getMA());
		return label;
	}

	private MouseListener getMA()
	{
		return new MouseAdapter()
		{
			private JLabel l;

			@Override
			public void mouseReleased(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				l = (JLabel) e.getComponent();
				l.setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				l = (JLabel) e.getComponent();
				l.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
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
