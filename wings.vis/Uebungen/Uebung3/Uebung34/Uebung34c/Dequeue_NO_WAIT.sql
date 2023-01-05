CONNECT aquser/aquser;

SET SERVEROUTPUT ON;

DECLARE
    dequeue_options     DBMS_AQ.DEQUEUE_OPTIONS_T;
    message_properties  DBMS_AQ.MESSAGE_PROPERTIES_T;
    message_id          RAW(2000);
    my_message          aqadm.queue_message_type;
BEGIN
	dequeue_options.wait       := DBMS_AQ.NO_WAIT;
    DBMS_AQ.DEQUEUE(
        queue_name => 'aqadm.message_queue',
        dequeue_options => dequeue_options,
        message_properties => message_properties,
        payload => my_message,
        msgid => message_id );
    COMMIT;
    DBMS_OUTPUT.PUT_LINE(
        'Dequeued no: ' || my_message.no);
    DBMS_OUTPUT.PUT_LINE(
        'Dequeued title: ' || my_message.title);
    DBMS_OUTPUT.PUT_LINE(
        'Dequeued text: ' || my_message.text);
END;
/