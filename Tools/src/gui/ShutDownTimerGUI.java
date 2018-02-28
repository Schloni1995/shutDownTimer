package gui;

import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import gui.tabs.Tab1;
import gui.tabs.Tab2;

public class ShutDownTimerGUI extends JFrame
{

	private static final Logger LOG = Logger.getLogger(ShutDownTimerGUI.class.getName());
	private static final long serialVersionUID = -1219716251617291329L;
	private Tab1 tab1;
	private Tab2 tab2;
	private JTabbedPane tabbedPane;

	/** Konstruktor für die GUI<br>
	 * Oberflächenklasse */
	public ShutDownTimerGUI()
	{
		ShutDownTimerGUI.LOG.info("Gui wird hochgefahren");
		setTitle("ShutDownTimer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(getTabbedPane());
		setVisible(true);
		pack();

		tab1.getTimeField().requestFocus();
	}

	public Tab1 getTab1()
	{
		tab1 = new Tab1();
		return tab1;
	}

	public Tab2 getTab2()
	{
		tab2 = new Tab2();
		return tab2;
	}

	private JTabbedPane getTabbedPane()
	{
		if (tabbedPane == null)
		{
			tabbedPane = new JTabbedPane();
			tabbedPane.add("Dauer", getTab1());
			tabbedPane.add("Zeitpunkt", getTab2());
		}
		return tabbedPane;
	}

}