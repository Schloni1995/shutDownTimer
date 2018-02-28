package shutDownTimer.gui.tabs;

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

import shutDownTimer.operations.StartDurationTimer;
import shutDownTimer.operations.StopTimer;

public class Tab1 extends JPanel
{
	private final static String[] UNITS = new String[] { "Sekunden", "Minuten", "Stunden" };
	private final static String DEFAULTTEXT= "Bitte gib die gew\u00fcnschte Zeit ein.";
	private static final Logger LOG = Logger.getLogger(Tab1.class.getName());
	private static final long serialVersionUID = 1586228218070110746L;
	private JPanel contentMidPanel;
	private JPanel countDownPanel;
	private JPanel inputPanel;
	private JLabel countDownLabel;
	private JLabel einheitTextLabel;
	private KeyAdapter ka;
	private JTextField timeField;
	private JComboBox<String> unitBox;
	
	public Tab1()
	{
		LOG.info("Tab1 wird geladen");
		setOpaque(false);
		setLayout(new BorderLayout());
		add(getInputPanel(), BorderLayout.NORTH);
		add(getMidContent(), BorderLayout.CENTER);
	}

	public JComboBox<String> getCombobox()
	{
		unitBox = new JComboBox<>();
		for (final String unit : UNITS)
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

	public JPanel getCountDownPanel(final String time, final String unit)
	{
		if (countDownPanel == null)
		{
			countDownPanel = new JPanel();
			countDownPanel.setOpaque(false);
		}
		countDownPanel.add(getCountDownLabel());
		return countDownPanel;
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
		ka = new KeyAdapter()
		{
			private Timer timer;

			@Override
			public void keyPressed(final KeyEvent evt)
			{
				final String unitString = unitBox.getSelectedItem().toString();
				final String timeString = timeField.getText();

				if (evt.getKeyCode() == KeyEvent.VK_ENTER)
				{
					int cdH = 0, cdMin = 0, cdS = 0;
					int durationInSec = Integer.parseInt(timeString);

					switch (unitString)
					{
						case "Stunden":
							cdH = durationInSec;
							cdMin = 0;
							cdS = 0;
							durationInSec *= 3600;
							break;
						case "Minuten":
							cdH = durationInSec / 60;
							cdMin = durationInSec % 60;
							cdS = 0;
							durationInSec *= 60;
							break;
						case "Sekunden":
							cdH = durationInSec / 3600;
							cdMin = durationInSec / 60;
							cdS = durationInSec % 60;
							break;
						default:
							cdH = 0;
							cdMin = 0;
							cdS = 0;
							break;
					}
					String msg = "Soll der Rechner in " + timeString + " " + unitString + " herunterfahren?";
					final int response = JOptionPane.showConfirmDialog(null, msg, "Sicher?", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION)
					{
						LOG.fine(cdH + " " + cdMin + " " + cdS);
						timer = new StartDurationTimer(Tab1.this, unitString, timeString, cdH, cdMin, cdS,
								durationInSec).getTimer();
					}
					else
						JOptionPane.showMessageDialog(Tab1.this, "Pc wird nicht heruntergefahren");
				}
				else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) if (timer != null)
				{
					getCountDownPanel("", "");
					new StopTimer(timer);
					JOptionPane.showMessageDialog(Tab1.this, "Herunterfahren wurde abgebrochen");
				}
				else
					JOptionPane.showMessageDialog(Tab1.this, "Timer nicht vorhanden");
			}
		};
		return ka;
	}

	public JPanel getMidContent()
	{
		contentMidPanel = new JPanel();
		contentMidPanel.setOpaque(false);
		contentMidPanel.add(getCountDownPanel("", ""));
		return contentMidPanel;
	}

	public JTextField getTimeField()
	{
		if (timeField == null)
		{
			timeField = new JTextField(DEFAULTTEXT);
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

	// public String getTargetTime()
	// {
	// final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	// final String now = sdf.format(new Date());
	//
	// System.out.println(now);
	// return now;
	// }

	public void setCountDownText(final String timeLeft)
	{
		countDownLabel.setText(timeLeft);
	}
}
