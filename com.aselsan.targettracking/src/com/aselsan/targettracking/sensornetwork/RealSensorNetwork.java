package com.aselsan.targettracking.sensornetwork;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collection;



//import org.eclipse.core.commands.common.EventManager;

public class RealSensorNetwork extends SensorNetwork
{
	private static String comport;
	private static RealSensorNetwork instance = null;
	private Thread t;
    public RealSensorNetwork(String c)
    {
        super();
        comport=c;
        
    }
	public static RealSensorNetwork getInstance(){
		if(instance == null)
			instance = new RealSensorNetwork(comport);
		return instance;
	}
    void connect ( String portName ) throws Exception
    {
    	System.out.println("dasa");
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(38400,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();
                               
                t = (new Thread(new SerialWriter(out)));
                t.start();
                
                serialPort.addEventListener(new SerialReader(in));
                serialPort.notifyOnDataAvailable(true);

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
    }
    
    public  class SerialReader implements SerialPortEventListener 
    {
        private InputStream in;
        private byte[] buffer = new byte[1024];
        ArrayList<Alarm> list;
        long prev = System.currentTimeMillis();
        SensorEventManager eventManager;
        public SerialReader ( InputStream in )
        {
            this.in = in;
            list = new ArrayList<Alarm>();
            eventManager = SensorEventManager.getInstance();
        }
        
        public void serialEvent(SerialPortEvent arg0) {
            int data;
                  
            long t = System.currentTimeMillis();
            try
            {
                int len = 0;
                int start = 0;
                while ( ( data = in.read()) > -1 )
                {
                	if ( data == '<' ){
                		start = len;
                   	}
                	buffer[len] = (byte) data;
                    if ( data == '>' ) {
                    	len = len -start-1;
                        String s = new String(buffer,start+1,len);
                      //  
                        Scanner scan = new Scanner(s);
                        if(t-prev<200){
                        	Alarm a = new Alarm(scan.nextInt(), scan.nextInt()-20, t);
                        	System.out.print(list);
                        	System.out.println(" " + a.sensorId + " " + a.strength );
                        	list.add(a);
                        }else{
                        	//System.out.println("!!" + list.size());
                        	
                        	eventManager.alarm(list);
                        	System.out.println("geldim");
                        	prev = t;
                        	list = new ArrayList<Alarm>();
                        	list.add(new Alarm(scan.nextInt(), scan.nextInt(), t));
                        	
                        }
                        
                    	//while ( ( data = in.read()) > -1 );
                    }
                    len++;
                }
                if(len == 0)
                	return;

            }
            catch ( IOException e )
            {
                e.printStackTrace();
                System.exit(-1);
            }             
        }

    }
    
    @SuppressWarnings("unchecked")
	public static Collection<String> listPorts()
    {
    	ArrayList<String> list = new ArrayList<String>();
        java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        while ( portEnum.hasMoreElements() ) 
        {
            CommPortIdentifier portIdentifier = portEnum.nextElement();
            list.add(portIdentifier.getName());
            //System.out.println(portIdentifier.getName()  +  " - " +  getPortTypeName(portIdentifier.getPortType()) );
        }
        return list;
    }
    
    static String getPortTypeName ( int portType )
    {
        switch ( portType )
        {
            case CommPortIdentifier.PORT_I2C:
                return "I2C";
            case CommPortIdentifier.PORT_PARALLEL:
                return "Parallel";
            case CommPortIdentifier.PORT_RAW:
                return "Raw";
            case CommPortIdentifier.PORT_RS485:
                return "RS485";
            case CommPortIdentifier.PORT_SERIAL:
                return "Serial";
            default:
                return "unknown type";
        }
    }
    
    public static class SerialWriter implements Runnable 
    {
        OutputStream out;
        
        public SerialWriter ( OutputStream out )
        {
            this.out = out;
        }
        
        public void run ()
        {
            try
            {                
                int c = 0;
                while ( ( c = System.in.read()) > -1 )
                {
                    this.out.write(c);
                }                
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                System.exit(-1);
            }            
        }
    }    
   
    public void start()
    {
        try
        {
           connect(comport);
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void stop(){
    	t.stop();
    }


}