package exercise013;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class EggDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	public EggDialog() {
		setTitle("Eieruhr");
		setSize(new Dimension(120, 80));
		setResizable(false);
		setLocation(600, 400);
		setVisible(false);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(new JLabel("Zeit ist abgelaufen!"), BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}
}
