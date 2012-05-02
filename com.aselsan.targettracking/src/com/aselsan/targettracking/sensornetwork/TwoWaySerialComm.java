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

public class TwoWaySerialComm
{
	
    public TwoWaySerialComm()
    {
        super();
        
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
                               
                (new Thread(new SerialWriter(out))).start();
                
                serialPort.addEventListener(new SerialReader(in));
                serialPort.notifyOnDataAvailable(true);

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
    }
    

    public static class SerialReader implements SerialPortEventListener 
    {
        private InputStream in;
        private byte[] buffer = new byte[1024];
        ArrayList<String> list;
        long prev = System.currentTimeMillis();
     //   SensorEventManager eventManager;
        public SerialReader ( InputStream in )
        {
            this.in = in;
            list = new ArrayList();
        //    eventManager = SensorEventManager.getInstance();
        }
        
        public void serialEvent(SerialPortEvent arg0) {
            int data;
        //    ArrayList<Alarm> alarmLists = new ArrayList();
           
            long t = System.currentTimeMillis();
            try
            {
                int len = 0;
                int start = 0;
             //   StringBuffer buf = new StringBuffer();
                while ( ( data = in.read()) > -1 )
                {
                	if ( data == '<' ){
                		start = len;
                	//	buf = new StringBuffer();
                	}
                	buffer[len] = (byte) data;
                    if ( data == '>' ) {
                    	len = len -start-1;
                    	while ( ( data = in.read()) > -1 );
                    }
                    len++;

                }
                
                String s = new String(buffer,start+1,len-1);
              //  System.out.println(s+" "+t);
                if(t-prev<400){
                	list.add(s);
                }else{
                	for(int i=0; i<list.size(); i++ )
                		System.out.print(list.get(i)+ "-");
                	System.out.println();
                	prev = t;
                	list.clear();
                	list.add(s);
                	
                }
                	
              //  System.out.println(s);
         //       Scanner scan = new Scanner(s);
           //     StringTokenizer tkn = new StringTokenizer(new String(buffer,start+1,len)," \t");
              //  System.out.println("Alii");
            //    System.out.println(scan.nextInt()+ " " + scan.nextInt());
               // int x = Integer.parseInt(scan.next());
               // System.out.println(x);
            //    System.out.println(buf);
                //System.out.println(tkn.nextToken());
                //System.out.println(tkn.nextToken());
              //  System.out.println("Alii");
                //System.out.println(new String(buffer,start+1,len));
                
              //TODO - parsing
                
              //TODO - send alarms
          //      eventManager.alarm(alarmLists);
                
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
    /** */
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
   
  /*  public static void main ( String[] args )
    {
    	listPorts();
    	
        try
        {
           (new TwoWaySerialComm()).connect("COM4");
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
*/

}