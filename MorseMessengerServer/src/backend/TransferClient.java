package backend;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class TransferClient {
	
  public static void main(String[] argv) throws Exception {
    Socket sock = new Socket("127.0.0.1", 2004);
    byte[] mybytearray = new byte[2048];
    
    System.out.println("[TransferClient] A client has been powered on ...");
    // First, you should be receiving the size of the transfer
    InputStream is = sock.getInputStream();
    FileOutputStream fos = new FileOutputStream("f2.pdf");
    
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
    bos.write(mybytearray, 0, bytesRead);
    
    bos.close();
    sock.close();
    
    System.out.println("[TransferClient] A client has written a file ...");
  }
  
}