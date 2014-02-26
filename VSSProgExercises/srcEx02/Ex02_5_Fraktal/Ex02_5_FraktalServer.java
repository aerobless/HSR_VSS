package Ex02_5_Fraktal;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;


public class Ex02_5_FraktalServer {
	public static final int PORT = 16666;

	public static void main(String[] args) throws Exception {
		while(true){
	        try (ServerSocket ss = new ServerSocket(PORT)) {
	        	System.out.println("waiting on client...");
	        	Socket s = ss.accept();  
	        	InputStream is = s.getInputStream();  
	        	ObjectInputStream ois = new ObjectInputStream(is);
	        	Ex02_5_FraktalHelper fractalHelp = (Ex02_5_FraktalHelper)ois.readObject();
	        	
	        	Ex02_5_FraktalGenerator generator = new Ex02_5_FraktalGenerator();
	        	ImageIcon result = generator.calcFraktal(fractalHelp.xMin, fractalHelp.xMax, fractalHelp.yMin, fractalHelp.yMax, fractalHelp.width, fractalHelp.maxDeep);
	        	
	        	System.out.println("Successfully received fractal request, sending fractal now.");
	        	
		    	OutputStream os = s.getOutputStream();  
		    	ObjectOutputStream oos = new ObjectOutputStream(os);  
		    	
		    	oos.writeObject(result);
		    	oos.close();  
		    	os.close();  
	
	        	is.close();  
	        	s.close();  
	        	ss.close();
	        }
        }
    }
}
