package shutDownTimer.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import shutDownTimer.gui.tabs.Tab1;
import shutDownTimer.gui.tabs.Tab2;

public class ShutDownTimerGUI extends JFrame
{
	private static final long serialVersionUID = -9007491578895215140L;
	private JTabbedPane tabbedPane;

	public ShutDownTimerGUI()
	{
		setTitle("ShutDownTimer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(getTabbedPane());
		setVisible(true);
		pack();
	}

	private JTabbedPane getTabbedPane()
	{
		if (tabbedPane == null)
		{
			tabbedPane = new JTabbedPane();
			tabbedPane.add("Dauer", new Tab1());
			tabbedPane.add("Zeitpunkt", new Tab2());
		}
		return tabbedPane;
	}

}