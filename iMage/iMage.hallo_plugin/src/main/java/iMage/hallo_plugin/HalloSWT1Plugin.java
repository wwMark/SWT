package iMage.hallo_plugin;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.iMage.plugins.JmjrstPlugin;
import org.jis.Main;
import org.kohsuke.MetaInfServices;

/**
 * A new plugin of iMage
 * 
 * @author Mark
 * @version 1.0
 *
 */
@MetaInfServices(JmjrstPlugin.class)
public class HalloSWT1Plugin extends JmjrstPlugin {

	/*
	 * Implement the abstract getMenuTest method
	 */
	public String getMenuText() {
		return "Hallo-SWT1-Plugin";
	}

	/*
	 * Implement the abstract getName method
	 */
	public String getName() {
		return "Hallo-SWT1-Plugin";
	}

	/*
	 * Implement the abstract init method
	 */
	public void init(Main main) {
		System.out.println("Ich initialisiere mich fleißig!");

	}

	/*
	 * Implement the abstract run method
	 */
	public void run() {
		// TODO Auto-generated method stub

	}

	/*
	 * Implement the abstract isConfigurable method
	 */
	public boolean isConfigurable() {
		return true;
	}

	/*
	 * Implement the abstract configure method
	 */
	public void configure() {
		JFrame jf = new JFrame("Hallo-SWT1-Plugin Configure Window");
		jf.getContentPane().add(new JLabel("You are configuring the Hallo-SWT1-Plugin!"));
	}

}
