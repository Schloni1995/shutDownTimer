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
import constants.Time;
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
	private final DecimalFormat df = Time.DF;

	/** Konstruktor für den zweiten Tab<br>
	 * Zeitpunktbestimmung */
	public Tab2()
	{
		LOG.info("Tab2 wird geladen");
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
		if (hBox == null)
		{
			hBox = new JComboBox<>(h);
			hBox.addKeyListener(getKA());
		}
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
					LOG.info("Now: " + LocalDateTime.now().toString());
					LocalDateTime ldt = null;
					try
					{
						int year, month, dayOfMonth, hour, minute, second;
						year = LocalDateTime.now().getYear();
						month = LocalDateTime.now().getMonthValue();
						dayOfMonth = LocalDateTime.now().getDayOfMonth();
						hour = Integer.parseInt(hBox.getSelectedItem().toString());
						minute = Integer.parseInt(minBox.getSelectedItem().toString());
						second = Integer.parseInt(sBox.getSelectedItem().toString());
						ldt = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
						LOG.info("Target: " + ldt.toString());
					}
					catch (final DateTimeException e)
					{
						LOG.severe(e.getMessage());
						System.exit(0);
					}
					final int h = ldt.getHour();
					final int m = ldt.getMinute();
					final int s = ldt.getSecond();
					LOG.info("Uhrzeit: " + df.format(h) + ":" + df.format(m) + ":" + df.format(s));

					final String msg = "Soll der Rechner " + df.format(h) + ":" + df.format(m) + ":" + df.format(s)
							+ " Uhr heruntergefahren werden?";
					final int response = JOptionPane.showConfirmDialog(null, msg, "Sicher?", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) timer = new StartPointTimer(Tab2.this, ldt).getTimer();
					else
					{
						JOptionPane.showMessageDialog(Tab2.this, Messages.NOT_SHUTDOWN_MESSAGE);
						LOG.info(Messages.NOT_SHUTDOWN_MESSAGE);
					}

				}
				else if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) if (timer != null)
				{
					new StopTimer(timer);
					LOG.info(Messages.ESCAPE_MESSAGE);
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
		if (minBox == null)
		{
			minBox = new JComboBox<>(min);
			minBox.addKeyListener(getKA());
		}
		return minBox;
	}

	private JComboBox<String> getSInputBox()
	{
		if (sBox == null)
		{
			sBox = new JComboBox<>(s);
			sBox.addKeyListener(getKA());
		}
		return sBox;
	}

	private void initArray()
	{
		h = new Vector<>();
		min = new Vector<>();
		s = new Vector<>();

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
