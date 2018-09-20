package com.example.quizuno.cliente2;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Rceptor extends Thread{
	
	 Socket s;

	 //paso numbero 2
	 OnMessage observer;

	public Rceptor(Socket s) {
		this.s = s;
	}
	

	@Override
	public void run() {
		try {
			InputStream in = s.getInputStream();
			
			//lector del mensaje que llega
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			while(true) {
				String line = reader.readLine();
				Log.e("RECIBIDO", line);

				//paso 4: solo funciona cuando observer es diferente de null
				observer.OnRecieve(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	//observable
	//paso numbero 1
	public interface OnMessage{
		public void OnRecieve(String mensaje);
	}

	//paso 3
	public void setObserver(OnMessage observer){
		this.observer = observer;
	}
	
	

}
