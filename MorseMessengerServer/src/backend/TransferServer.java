package backend;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TransferServer {
	
	 public static void main(String[] args) throws IOException {
		    ServerSocket servsock = new ServerSocket(2004);
		    File myFile = new File("f.pdf");
		    
		    System.out.println("[TransferServer] I am running ...");
		    
		    while (true) {
		      Socket sock = servsock.accept();
		      
		      System.out.println("[TransferServer] Connection has been established ...");
		      
		      byte[] mybytearray = new byte[(int) myFile.length()];
		      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
		      bis.read(mybytearray, 0, mybytearray.length);
		      
		      OutputStream os = sock.getOutputStream();
		      os.write(mybytearray, 0, mybytearray.length);
		      os.flush();
		      
		      sock.close();
		      
		      System.out.println("[TransferServer] File is sent ...");
		    }
	 }
}
