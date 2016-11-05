//package com.sharon.couponsystem.utils;
//
//import java.util.Random;
//
//import javax.jms.JMSException;
//import javax.jms.Queue;
//import javax.jms.QueueConnection;
//import javax.jms.QueueConnectionFactory;
//import javax.jms.QueueSender;
//import javax.jms.QueueSession;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//import javax.naming.InitialContext;
//import javax.servlet.ServletConfig;
//
//public class QueueSender {
////This is the class that sends to the queue
//	
//	private QueueConnectionFactory qconFactory;
//	private QueueConnection qcon;
//	private QueueSession qsession;
//	private QueueSender qsender;
//	private Queue queue;
//	
//	public static void sendToQueue(String message){
//		try {
//			// Creating a text message, which will be identical to the random number we've 
//			// just generated
//			TextMessage msg =  qsession.createTextMessage();
//			msg.setText(String.valueOf(num));
//			
//			// Sending the message to the queue
//			qsender.send(msg);		
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	// An initialization segement, which will eventually give us a functional QueueSender object
//		// connected to a queue named "Yossi"
//		public void init(ServletConfig config){
//			try{
//				// Connecting to the JNDI of the JBoss which runs the current Servlet
//				InitialContext ctx = new InitialContext(); 
//				
//				qconFactory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");		//create Factory
//				qcon = qconFactory.createQueueConnection();							//create Connection
//				qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);//create Session (A connection between two points)
//				
//				// Performing a lookup with the JNDI, getting a stub which enables us to communicate to 
//				// a queue named Yossi
//				//The following two lines are the only important ones from this technical block.
//				queue = (Queue) ctx.lookup("queue/Yossi");								//create Queue
//				qsender = qsession.createSender(queue);								//create Sender
//				qcon.start();
//				
//				// Initializing a Random object, in order to have random numbers sent to the receiver
//				rnd = new Random();
//			}
//			catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//	
//}
