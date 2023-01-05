import java.sql.DriverManager;
import java.util.Properties;

import javax.jms.Session;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

import oracle.AQ.AQDriverManager;
import oracle.AQ.AQSession;
import oracle.jms.AQjmsFactory;

/**
 * Base class for all AQ applications
 * 
 * @author Rene Steiner, Akadia AG
 */
abstract public class AQApplication {

	/**
	 * DB connection string, syntax (host):(port):(sid) Examples:
	 * "jdbc:oracle:thin:@cyrus:1521:CYR1",
	 * "jdbc:oracle:thin:scott/tiger@dlsun511:1721:dbms733"
	 * "jdbc:oracle:thin:@172.20.14.103:1521:eval112"
	 */
	// m.kubbillum: SID => select instance_name from v$instance => eval112
	private final String DB_CONNECTION = "jdbc:oracle:thin:@172.20.14.103:1521:eval112";

	/**
	 * DB AQ administrator name and password, it is also the owner of all queues and
	 * required objects
	 */
	protected final String DB_AQ_ADMIN_NAME = "aqadm";
	private final String DB_AQ_ADMIN_PASSWORD = "aqadm";

	/** DB AQ user agent name and password */
	private final String DB_AQ_USER_NAME = "aquser";
	private final String DB_AQ_USER_PASSWORD = "aquser";

	/**
	 * Native AQ references, DB connection for transaction handling and AQ session
	 */
	protected class NativeAQ {

		java.sql.Connection connection = null;
		AQSession session = null;
	}

	/** JMS AQ references, topic connection and AQ session */
	protected class JMSTopic {

		TopicConnection connection = null;
		TopicSession session = null;
	}

	/**
	 * Creates a native AQ administrator connection and session
	 * 
	 * @return AQ connection and session reference
	 */
	protected NativeAQ createNativeAdmin() {

		return createNative(DB_CONNECTION, DB_AQ_ADMIN_NAME, DB_AQ_ADMIN_PASSWORD);
	}

	/**
	 * Creates a native AQ user connection and session
	 * 
	 * @return AQ connection and session reference
	 */
	protected NativeAQ createNativeClient() {

		return createNative(DB_CONNECTION, DB_AQ_USER_NAME, DB_AQ_USER_PASSWORD);
	}

	/**
	 * Creates a JMS AQ administrator connection and session
	 * 
	 * @return AQ connection and session reference
	 */
	protected JMSTopic createJMSAdmin() {

		return createJMS(DB_CONNECTION, DB_AQ_ADMIN_NAME, DB_AQ_ADMIN_PASSWORD);
	}

	/**
	 * Creates a JMS AQ user connection and session
	 * 
	 * @return AQ connection and session reference
	 */
	protected JMSTopic createJMSClient() {

		return createJMS(DB_CONNECTION, DB_AQ_USER_NAME, DB_AQ_USER_PASSWORD);
	}

	/**
	 * Creates a native AQ connection and session.
	 * 
	 * The passed connectionString uses the syntax (host):(port):(sid).
	 * 
	 * Examples: "jdbc:oracle:thin:@cyrus:1521:CYR1",
	 * "jdbc:oracle:thin:scott/tiger@dlsun511:1721:dbms733"
	 * 
	 * @param connectString URL to connect to the database
	 * @param userName      Agent's user name
	 * @param userPassword  Agent's password
	 * 
	 * @return AQ connection and session reference
	 */
	private NativeAQ createNative(String connectString, String userName, String userPassword) {

		NativeAQ aq = new NativeAQ();

		try {

			// Loads the Oracle JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// DB connection
			aq.connection = DriverManager.getConnection(connectString, userName, userPassword);
			aq.connection.setAutoCommit(false);
			System.out.println("JDBC connection with user '" + userName + "' and password '" + userPassword
					+ "' opened to: " + connectString);

			// Loads the Oracle AQ driver
			Class.forName("oracle.AQ.AQOracleDriver");

			// Creates an AQ session
			aq.session = AQDriverManager.createAQSession(aq.connection);
			System.out.println("Successfully created AQ session");
		} catch (Exception ex) {
			System.err.println("AQApplication.createNative(): " + ex.getMessage());
			System.err.println("user = " + userName + ", password = " + userPassword + ", to = " + connectString);
			ex.printStackTrace();
		}

		return aq;
	}

	/**
	 * Creates a JMS AQ connection and session.
	 * 
	 * The passed connectionString uses the syntax (host):(port):(sid).
	 * 
	 * Examples: "jdbc:oracle:thin:@cyrus:1521:CYR1",
	 * 
	 * "jdbc:oracle:thin:scott/tiger@dlsun511:1721:dbms733"
	 * 
	 * 
	 * @param connectString URL to connect to the database
	 * @param userName      Agent's user name
	 * @param userPassword  Agent's password
	 * 
	 * @return AQ connection and session reference
	 */
	private JMSTopic createJMS(String connectString, String userName, String userPassword) {

		JMSTopic aq = new JMSTopic();

		try {

			TopicConnectionFactory topicConnectionFactory = null;

			// Get topic connection factory
			Properties info = new Properties();
			info.put(userName, userPassword);
			topicConnectionFactory = AQjmsFactory.getTopicConnectionFactory(connectString, info);
			System.out.println("JDBC connection with user '" + userName + "' and password '" + userPassword
					+ "' opened to: " + connectString);

			// Creates an AQ topic connection and session

			aq.connection = topicConnectionFactory.createTopicConnection(userName, userPassword);
			// If a session is transacted, message acknowledgment is handled automatically
			// by commit and recovery is handled automatically by rollback
			aq.session = aq.connection.createTopicSession(true, // Session is transacted
					Session.CLIENT_ACKNOWLEDGE); // Acknowledges by commit and rollback
			System.out.println("Successfully created AQ session");
		} catch (Exception ex) {
			System.err.println("AQApplication.createJMS(): " + ex.getMessage());
			System.err.println(
					"user = " + userName + ", password = " + userPassword + ", destination = " + connectString);
			ex.printStackTrace();
		}

		return aq;
	}

} // End of class AQApplication
