import java.sql.*;
import javax.jms.*;

import oracle.jms.*;

/** This is a sample message receiver class using the JMS Interface to AQ

	@author Rene Steiner, Akadia AG
*/
public class AQJmsSubscriber extends AQApplication {

	/** The subscription id is assigned according command line arguments, 
		set to 0 if not specified */
	private String _subscriptionId = "0";


	/** Main program that starts this application

		@param arguments Arguments passed at program start
	*/
	static public void main(String[] arguments) {

    AQJmsSubscriber application = new AQJmsSubscriber(arguments);
	}

	/** Constructor

		@param arguments Arguments passed at program start
	*/
	protected AQJmsSubscriber(String[] arguments) {

		try {

			if (arguments.length > 0) {
				_subscriptionId = arguments[0];
			}

    	JMSTopic aq = createJMSClient();
      doTest(aq);
		}
		catch (Exception ex) {
			System.err.println("AQJmsSubscriber.AQJmsSubscriber(): " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/** Test method

		@param session Used AQ connection and session

  	@exception javax.jms.JMSException Super exception for all JMS errors
  	@exception java.sql.SQLException JDBC SQL exceptions
	*/
	protected void doTest(JMSTopic aq) throws JMSException, SQLException {

    // Start the connection
    aq.connection.start();

    // Get topic object
    Topic topic = ((AQjmsSession) aq.session).getTopic(
                DB_AQ_ADMIN_NAME,     // Queue owner
                "MULTI_QUEUE");       // Queue name

    // Create topic receiver
    TopicReceiver subscriber = ((AQjmsSession) aq.session).createTopicReceiver(
                topic,                // Used topic
																			// Name and identification of the subscription
                "SUBSCRIPTION" + _subscriptionId,      
                null);                // No message selector

    // Receive message
		System.out.println(
			"Waiting 60 seconds to receive message for subscription SUBSCRIPTION" 
			+ _subscriptionId + " ...");
    ObjectMessage objectMessage = (ObjectMessage) subscriber.receive(
                60000);               // Wait in milliseconds
    if (objectMessage != null) {

      AQJmsMultiQueueItem messageData = (AQJmsMultiQueueItem) objectMessage.getObject();
      System.out.println("receive() successfully");

      // Print message
      System.out.println("Received no:    " + messageData.getNo());
      System.out.println("Received title: " + messageData.getTitle());
      System.out.println("Received text:  " + messageData.getText());
    }

    // Commit activities
    aq.session.commit();

    // Close session
    aq.session.close();

    // Close connection
    aq.connection.close();
	}

} // End of class AQJmsSubscriber

