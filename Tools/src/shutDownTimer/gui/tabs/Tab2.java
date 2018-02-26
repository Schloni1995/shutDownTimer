package shutDownTimer.gui.tabs;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tab2 extends JPanel
{
	private static final long serialVersionUID = 6235627806339949540L;
	private JPanel inputPanel;
	private JPanel contentMidPanel;
	private JComboBox<String> hBox, minBox, sBox;

	private Vector<String> h, min, s;

	public Tab2()
	{
		initArray();

		setOpaque(false);
		setLayout(new BorderLayout());
		add(getInputPanel(), BorderLayout.NORTH);
		add(getMidContent(), BorderLayout.CENTER);
	}

	private JPanel getInputPanel()
	{
		if (inputPanel == null)
		{
			inputPanel = new JPanel();
			inputPanel.setOpaque(false);
			inputPanel.add(getHInputBox());
			inputPanel.add(new JLabel(":"));
			inputPanel.add(getMinInputBox());
			inputPanel.add(new JLabel(":"));
			inputPanel.add(getSInputBox());
		}
		return inputPanel;
	}

	private JPanel getMidContent()
	{
		if (contentMidPanel == null) 
		{
			contentMidPanel = new JPanel();
			contentMidPanel.setOpaque(false);
		}
		return contentMidPanel;
	}

	private JComboBox<String> getHInputBox()
	{
		if (hBox == null) hBox = new JComboBox<>(h);
		return hBox;
	}

	private JComboBox<String> getMinInputBox()
	{
		if (minBox == null) minBox = new JComboBox<>(min);
		return minBox;
	}

	private JComboBox<String> getSInputBox()
	{
		if (sBox == null) sBox = new JComboBox<>(s);
		sBox.addKeyListener(getKL());
		return sBox;
	}

	private KeyListener getKL()
	{
		KeyListener kl = new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent ke)
			{
				if (ke.getKeyCode() == KeyEvent.VK_ENTER)
				{
					//DO STUFF
					
				}
			}
		};
		return kl;
	}

	private void initArray()
	{
		h = new Vector<>();
		min = new Vector<>();
		s = new Vector<>();
		DecimalFormat df = new DecimalFormat("00");
		for (int i = 0; i < 24; i++)
		{	
			h.add(df.format(i));
		}

		for (int i = 0; i < 60; i++)
		{
			min.add(df.format(i));
		}

		for (int i = 0; i < 60; i++)
		{
			s.add(df.format(i));
		}

	}

}
