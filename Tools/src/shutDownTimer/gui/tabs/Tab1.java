package shutDownTimer.gui.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import shutDownTimer.operations.StartDurationTimer;
import shutDownTimer.operations.StopTimer;

public class Tab1 extends JPanel
{
	private static final long serialVersionUID = 1586228218070110746L;
	private JPanel contentMidPanel;
	private JLabel countDownLabel;
	private JPanel countDownPanel;
	private final String defaultTextString;
	private JLabel einheitTextLabel;
	private JPanel inputPanel;
	private KeyAdapter ka;
	private JTextField timeField;
	@SuppressWarnings("rawtypes")
	private JComboBox unitBox;
	private final String[] units;

	public Tab1()
	{
		defaultTextString = "Bitte gib die gewünschte Zeit ein.";
		units = new String[] { "Sekunden", "Minuten", "Stunden" };

		setOpaque(false);
		setLayout(new BorderLayout());
		add(getInputPanel(), BorderLayout.NORTH);
		add(getMidContent(), BorderLayout.CENTER);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox getCombobox()
	{
		unitBox = new JComboBox<>();
		for (final String unit : units)
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
		inputPanel.add(getTextfield());
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
				final String unit = unitBox.getSelectedItem().toString();
				final String time = timeField.getText();
				int tNow = 0;
				int cdH = 0, cdMin = 0, cdS = 0;
				if (evt.getKeyCode() == KeyEvent.VK_ENTER)
				{
					tNow = Integer.parseInt(time);
					switch (unit)
					{
						case "Minuten":
							cdH = tNow / 60;
							cdMin = tNow % 60;
							cdS = 0;
							tNow *= 60;
							break;
						case "Stunden":
							cdH = tNow;
							cdMin = 0;
							cdS = 0;
							tNow *= 3600;
							break;
						case "Sekunden":
							cdH = tNow / 3600;
							cdMin = tNow / 60;
							cdS = tNow % 60;
							break;
						default:
							break;
					}

					final int response = JOptionPane.showConfirmDialog(null,
							"Soll der Rechner in " + time + " " + unit + " herunterfahren?", "Sicher?",
							JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION)
					{
						timer = new StartDurationTimer(Tab1.this, unit, time, cdH, cdMin, cdS, tNow).getTimer();
					}
					else
					{
						JOptionPane.showMessageDialog(Tab1.this, "Pc wird nicht heruntergefahren");

					}
				}
				else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					getCountDownPanel("", "");
					new StopTimer(timer);
					JOptionPane.showMessageDialog(Tab1.this, "Herunterfahren wurde abgebrochen");
				}
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

	public JTextField getTextfield()
	{
		timeField = new JTextField(defaultTextString);
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
