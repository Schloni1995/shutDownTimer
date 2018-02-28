package gui.tabs;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import constants.Messages;
import operations.StartPointTimer;
import operations.StopTimer;

public class Tab2 extends JPanel
{
	private static final Logger LOG = Logger.getLogger(Tab2.class.getName());
	/** Tab2 Zeitpunkt */
	private static final long serialVersionUID = 1347L;
	private JPanel contentMidPanel;
	private JLabel countDownLabel;
	private Vector<String> h, min, s;
	private JComboBox<String> hBox, minBox, sBox;
	private JPanel inputPanel;

	/** Konstruktor für den zweiten Tab<br>
	 * Zeitpunktbestimmung */
	public Tab2()
	{
		initArray();
		setOpaque(false);
		setLayout(new BorderLayout());
		add(getInputPanel(), BorderLayout.NORTH);
		add(getMidContent(), BorderLayout.CENTER);
	}

	public JLabel getCountDownLabel()
	{
		if (countDownLabel == null) countDownLabel = new JLabel("Restzeit: ");
		return countDownLabel;
	}

	private JComboBox<String> getHInputBox()
	{
		if (hBox == null) hBox = new JComboBox<>(h);
		return hBox;
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

	private KeyAdapter getKA()
	{
		final KeyAdapter ka = new KeyAdapter()
		{
			private Timer timer;

			@Override
			public void keyReleased(final KeyEvent ke)
			{
				if (ke.getKeyCode() == KeyEvent.VK_ENTER)
				{
					LocalDateTime ldt = null;
					try
					{
						ldt = LocalDateTime.now().plusHours(Long.parseLong(hBox.getSelectedItem().toString()))
								.plusMinutes(Long.parseLong(minBox.getSelectedItem().toString()))
								.plusSeconds(Long.parseLong(sBox.getSelectedItem().toString()));
					}
					catch (final DateTimeException e)
					{
						LOG.severe(e.getMessage());
						System.exit(0);
					}
					final int h = ldt.getHour();
					final int m = ldt.getMinute();
					final int s = ldt.getSecond();
					final String msg = "Soll der Rechner " + h + ":" + m + ":" + s + " Uhr heruntergefahren werden?";
					final int response = JOptionPane.showConfirmDialog(null, msg, "Sicher?", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION)
					{
						LOG.fine("Uhrzeit: " + h + ":" + m + ":" + s);
						timer = new StartPointTimer(Tab2.this, ldt).getTimer();
					}
					else
						JOptionPane.showMessageDialog(Tab2.this, Messages.NOT_SHUTDOWN_MESSAGE);
				}
				else if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) if (timer != null)
				{
					new StopTimer(timer);
					JOptionPane.showMessageDialog(Tab2.this, Messages.ESCAPE_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(Tab2.this, Messages.NOT_TIMER_MESSAGE);
			}
		};
		return ka;
	}

	private JPanel getMidContent()
	{
		if (contentMidPanel == null)
		{
			contentMidPanel = new JPanel();
			contentMidPanel.add(getCountDownLabel());
			contentMidPanel.setOpaque(false);
		}
		return contentMidPanel;
	}

	private JComboBox<String> getMinInputBox()
	{
		if (minBox == null) minBox = new JComboBox<>(min);
		return minBox;
	}

	private JComboBox<String> getSInputBox()
	{
		if (sBox == null) sBox = new JComboBox<>(s);
		sBox.addKeyListener(getKA());
		return sBox;
	}

	private void initArray()
	{
		h = new Vector<>();
		min = new Vector<>();
		s = new Vector<>();
		final DecimalFormat df = new DecimalFormat("00");
		for (int i = 0; i < 24; i++)
			h.add(df.format(i));

		for (int i = 0; i < 60; i++)
			min.add(df.format(i));

		for (int i = 0; i < 60; i++)
			s.add(df.format(i));

	}

	public void setCountDownText(final String timeLeft)
	{
		countDownLabel.setText(timeLeft);
	}

}
