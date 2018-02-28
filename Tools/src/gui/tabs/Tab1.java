package gui.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.Messages;
import operations.Converter;
import operations.StartDurationTimer;
import operations.StopTimer;

public class Tab1 extends JPanel
{
	private final static String DEFAULTTEXT = "Bitte gib die gew\u00fcnschte Zeit ein.";
	private static final Logger LOG = Logger.getLogger(Tab1.class.getName());
	/** Tab1 Zeitdauer */
	private static final long serialVersionUID = 456496L;
	private final static String[] UNITS = new String[] { "Sekunden", "Minuten", "Stunden" };

	private JPanel contentMidPanel;
	private JLabel countDownLabel;
	private JLabel einheitTextLabel;
	private JPanel inputPanel;
	private JTextField timeField;
	private JComboBox<String> unitBox;

	/** Konstruktor für den ersten Tab <br>
	 * Zeitdauerbestimmung */
	public Tab1()
	{
		Tab1.LOG.info("Tab1 wird geladen");
		setOpaque(false);
		setLayout(new BorderLayout());
		add(getInputPanel(), BorderLayout.NORTH);
		add(getMidContent(), BorderLayout.CENTER);
	}

	public JComboBox<String> getCombobox()
	{
		unitBox = new JComboBox<>();
		for (final String unit : Tab1.UNITS)
			unitBox.addItem(unit);
		unitBox.addKeyListener(getKeyAdapter());
		unitBox.setOpaque(true);
		return unitBox;
	}

	public JLabel getCountDownLabel()
	{
		if (countDownLabel == null) countDownLabel = new JLabel("Restzeit: ");
		return countDownLabel;
	}

	public JLabel getEinheitTextLabel()
	{
		einheitTextLabel = new JLabel("Einheit");
		einheitTextLabel.setForeground(Color.black);
		return einheitTextLabel;
	}

	public JPanel getInputPanel()
	{
		inputPanel = new JPanel();
		inputPanel.setOpaque(false);
		inputPanel.add(getTimeField());
		inputPanel.add(getEinheitTextLabel());
		inputPanel.add(getCombobox());
		return inputPanel;
	}

	public KeyAdapter getKeyAdapter()
	{
		KeyAdapter ka = new KeyAdapter()
		{
			private Timer timer;

			@Override
			public void keyPressed(final KeyEvent evt)
			{
				final String unitString = unitBox.getSelectedItem().toString();
				final String timeString = timeField.getText();

				if (evt.getKeyCode() == KeyEvent.VK_ENTER)
				{
					Converter conv = new Converter(unitString,Integer.parseInt(timeString));
					float cdH = conv.getCdH();
					float cdMin = conv.getCdMin();
					float cdS = conv.getCdS();
					float durationInSec = conv.getDurationInSec();
					
					LOG.fine(cdH + " " + cdMin + " " + cdS);
					final String msg = "Soll der Rechner in " + timeString + " " + unitString + " herunterfahren?";
					final int response = JOptionPane.showConfirmDialog(null, msg, "Sicher?", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION)
					{
						timer = new StartDurationTimer(Tab1.this, unitString, timeString, cdH, cdMin, cdS,
								durationInSec).getTimer();
					}
					else
						JOptionPane.showMessageDialog(Tab1.this, Messages.NOT_SHUTDOWN_MESSAGE);
				}
				else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) if (timer != null)
				{
					new StopTimer(timer);
					JOptionPane.showMessageDialog(Tab1.this, Messages.ESCAPE_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(Tab1.this, Messages.NOT_TIMER_MESSAGE);
			}
		};
		return ka;
	}

	public JPanel getMidContent()
	{
		if (contentMidPanel == null)
		{
			contentMidPanel = new JPanel();
			contentMidPanel.setOpaque(false);
			contentMidPanel.add(getCountDownLabel());
		}

		return contentMidPanel;
	}

	public JTextField getTimeField()
	{
		if (timeField == null)
		{
			timeField = new JTextField(Tab1.DEFAULTTEXT);
			timeField.setMinimumSize(new Dimension(200, 20));
			timeField.setMaximumSize(new Dimension(200, 20));
			timeField.setPreferredSize(new Dimension(200, 20));
			timeField.selectAll();
			timeField.addKeyListener(getKeyAdapter());
			timeField.addFocusListener(new FocusAdapter()
			{
				@Override
				public void focusGained(final FocusEvent e)
				{
					timeField.selectAll();
				}
			});
			timeField.setOpaque(true);
		}
		return timeField;
	}

	public void setCountDownText(final String timeLeft)
	{
		countDownLabel.setText(timeLeft);
	}

	// public String getTargetTime()
	// {
	// final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	// final String now = sdf.format(new Date());
	//
	// System.out.println(now);
	// return now;
	// }
}
