//javac -cp ..\Uebung32a\apache-activemq-5.16.3\lib\*;. MyProducer.java
//java -cp ..\Uebung32a\apache-activemq-5.16.3\lib\*;. MyProducer ConnectionFactory "Martin Kubbillum" null

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.QueueSender;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;
import java.util.UUID;

public class MyProducer {
	private QueueConnectionFactory qFactory = null;
	private QueueConnection qConnect = null;
	private QueueSession qSession = null;
	private Queue sQueue = null;
	private QueueSender qSender = null;

	/* Constructor. Establish the Producer */
	public MyProducer(String broker, String username, String password) throws Exception {
		Properties env = new Properties();
		env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		env.setProperty(Context.PROVIDER_URL, "tcp://127.0.0.1:61616");
		env.setProperty("queue.hello", "hello");
		javax.naming.Context jndi = new InitialContext(env);

		// Look up a JMS QueueConnectionFactory
		qFactory = (QueueConnectionFactory) jndi.lookup(broker);

		// Create a JMS QueueConnection object
		qConnect = qFactory.createQueueConnection(username, password);

		// Create one JMS QueueSession object
		qSession = qConnect.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

		// Look up for a JMS Queue hello
		sQueue = (Queue) jndi.lookup("hello");

		// Create a sender
		qSender = qSession.createSender(sQueue);

		// Start the Connection
		qConnect.start();

	}

	/* Create and send message using qSender */
	protected void SendMessage(String username) throws JMSException {
		// Create message
		TextMessage message = qSession.createTextMessage();
		// generate and set JMS
		String id = UUID.randomUUID().toString();
		message.setJMSCorrelationID(id);
		// Set payload
		message.setText(username + ": Hello!");
		// Send Message
		qSender.send(message);
	}

	/* Close the JMS connection */
	public void close() throws JMSException {
		qConnect.close();
	}

	/* Run the Producer */
	public static void main(String argv[]) throws Exception {
		String broker, username, password;
		if (argv.length == 3) {
			broker = argv[0];
			username = argv[1];
			password = argv[2];
		} else {
			return;
		}
		// Create Producer
		MyProducer producer = new MyProducer(broker, username, password);

		producer.SendMessage(username);
		// Close connection
		producer.close();
	}
}