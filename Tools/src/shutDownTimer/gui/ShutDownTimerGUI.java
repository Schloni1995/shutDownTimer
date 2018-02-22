package shutDownTimer.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import shutDownTimer.tabs.Tab1;
import shutDownTimer.tabs.Tab2;

public class ShutDownTimerGUI extends JFrame
{
	private static final long serialVersionUID = -9007491578895215140L;
	private JTabbedPane tabbedPane;

	public ShutDownTimerGUI()
	{
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
			tabbedPane.add("S/Min/H", new Tab1());
			tabbedPane.add("Inhalt folgt...", new Tab2());
		}
		return tabbedPane;
	}

}