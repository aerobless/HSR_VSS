package Ex02_5_Fraktal;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ex02_5_FraktalClient {

	public static void main(String[] args) throws Exception {
        ImageIcon icon = fetchFraktalFromServer();
        showInWindow(icon);
	}

    private static void showInWindow(final ImageIcon icon) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Apfelmännchen");
                frame.getContentPane().add(new ImagePanel(icon));
                frame.setSize(icon.getIconWidth() + 6, icon.getIconHeight() + 25);
                frame.setResizable(false);
                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) {
                        System.exit(0);
                    }
                });
                frame.setVisible(true);
            }
        });
    }

    private static ImageIcon fetchFraktalFromServer() {
    	ImageIcon fractalImage = null;
    	try {
	    	Socket s = new Socket("mc.blood-cloud.com",5222);  
	    	OutputStream os = s.getOutputStream();  
	    	ObjectOutputStream oos = new ObjectOutputStream(os);  
	    	
	    	Ex02_5_FraktalHelper fractalHelp = new Ex02_5_FraktalHelper(-2.25, 0.75, -1.5, 1.5, 900, 900);
			oos.writeObject(fractalHelp);
			System.out.println("Fractal request sent. Trying to receive now...");
			
			InputStream is = s.getInputStream();  
        	ObjectInputStream ois = new ObjectInputStream(is);
        	fractalImage = (ImageIcon)ois.readObject();
        	System.out.println("Successfuly received fractalImage");
			
	    	oos.close();  
	    	os.close();  
	    	s.close();
		} catch (IOException anEx) {
			anEx.printStackTrace();
		} catch (ClassNotFoundException anEx) {
			anEx.printStackTrace();
		} 
    	
        return fractalImage;
    }

    private static class ImagePanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private final ImageIcon image;

        public ImagePanel(ImageIcon image) {
            this.image = image;
        }

        public void paintComponent(Graphics g) {
            g.drawImage(image.getImage(), 0, 0, this);
        }
    }
}