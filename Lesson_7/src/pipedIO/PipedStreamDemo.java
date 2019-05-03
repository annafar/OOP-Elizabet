package pipedIO;
/**
 * A piped output stream can be connected to a piped input stream to create a communications pipe. 
 * The piped output stream is the sending end of the pipe. 
 * Typically, data is written to a PipedOutputStream object by one thread and data is read 
 * from the connected PipedInputStream by some other thread. 
 * Attempting to use both objects from a single thread is not recommended as it may deadlock the thread. 
 * The pipe is said to be broken  if a thread that was reading data bytes from the connected 
 * piped input stream is no longer alive.
 */
import java.io.*;
class PipedStreamDemo{
	public static void main(String args[]){
		try{
			PipedOutputStream pos = new PipedOutputStream();
			PipedInputStream pis = new PipedInputStream(pos);
			NumbersGenerator numbersGenerator = new NumbersGenerator(pos);
			NumbersReciever numbersReciever = new NumbersReciever(pis);
			Thread thread1 = new Thread(numbersGenerator);
			Thread thread2 = new Thread(numbersReciever);
			thread1.start();
			thread2.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
class NumbersGenerator implements Runnable{
	PipedOutputStream out;
	public NumbersGenerator(PipedOutputStream pOut){
		out = pOut;
	}
	public void run(){
		byte val;
		try{
			for(int i=0; i<5; i++){
				val = (byte)(Math.random()*100);
				out.write(val);
				System.out.println(val + " was sent");
				//Thread.sleep(1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				out.close();
				System.out.println("out is closed");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
class NumbersReciever implements Runnable{
	PipedInputStream in;
	public NumbersReciever(PipedInputStream pIn){
		in = pIn;
	}
	public void run(){
		byte val;
		try{
			while((val = (byte)in.read())!=-1){
				System.out.println(val + " was recieved");
			}
			//Thread.sleep(1);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				in.close();
				System.out.println("in is closed");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}


