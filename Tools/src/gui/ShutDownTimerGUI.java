package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import constants.Colors;
import constants.Time;
import gui.tabs.Tab1;
import gui.tabs.Tab2;

public class ShutDownTimerGUI extends JFrame
{
	private static final Logger LOG = Logger.getLogger(ShutDownTimerGUI.class.getName());
	private static final long serialVersionUID = -1219716251617291329L;
	private Tab1 tab1;
	private Tab2 tab2;
	private JTabbedPane tabbedPane;
	private JLabel timeLabel;
	private JPanel timePanel;
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem bgMI;

	/** Konstruktor für die GUI<br>
	 * Oberflächenklasse */
	public ShutDownTimerGUI()
	{
		ShutDownTimerGUI.LOG.info("Gui wird hochgefahren");
		setTitle("ShutDownTimer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(new BackGroundPanel());
		getContentPane().setLayout(new BorderLayout());
		add(getTimePanel(), BorderLayout.NORTH);
		add(getTabbedPane(), BorderLayout.CENTER);
		setJMenuBar(getMenubar());

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
			UIManager.put("TabbedPane.contentOpaque", false);
			tabbedPane = new JTabbedPane();
			tabbedPane.add("Dauer", getTab1());
			tabbedPane.add("Zeitpunkt", getTab2());
		}
		return tabbedPane;
	}

	private JLabel getTimeLabel()
	{
		if (timeLabel == null) timeLabel = new JLabel();
		timeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		timeLabel.setForeground(Colors.TEXT_COLOR);
		final Timer actuallTime = new Timer();
		final DecimalFormat df = Time.DF;
		actuallTime.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				timeLabel.setText("Uhrzeit: " + df.format(LocalDateTime.now().getHour()) + ":"
						+ df.format(LocalDateTime.now().getMinute()) + ":"
						+ df.format(LocalDateTime.now().getSecond()));
			}
		}, 0, 1000);

		return timeLabel;
	}

	private JPanel getTimePanel()
	{
		if (timePanel == null)
		{
			timePanel = new JPanel();
			timePanel.add(getTimeLabel());
			timePanel.setOpaque(false);
		}
		return timePanel;
	}

	/** @return the menubar */
	public JMenuBar getMenubar()
	{
		menubar = new JMenuBar();
		menubar.add(getMenu());
		return menubar;
	}

	/** @return the menu */
	public JMenu getMenu()
	{
		menu = new JMenu("Optik");
		return menu;
	}

	/** @return the bgMI */
	public JMenuItem getBgMI()
	{
		bgMI = new JMenuItem("Hintergrund ändern");
		bgMI.addActionListener(e ->
		{
			
		});
		return bgMI;
	}

}