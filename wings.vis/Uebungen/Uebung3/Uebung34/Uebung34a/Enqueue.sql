CONNECT aquser/aquser;

DECLARE
    queue_options       DBMS_AQ.ENQUEUE_OPTIONS_T;
    message_properties  DBMS_AQ.MESSAGE_PROPERTIES_T;
    message_id          RAW(16);
    my_message          aqadm.queue_message_type;
BEGIN
    my_message := aqadm.queue_message_type(
            1,
            'This is a sample message',
            'This message has been posted on ' ||
            TO_CHAR(SYSDATE,'DD.MM.YYYY HH24:MI:SS'));
    DBMS_AQ.ENQUEUE(
        queue_name => 'aqadm.message_queue',
        enqueue_options => queue_options,
        message_properties => message_properties,
        payload => my_message,
        msgid => message_id);
    COMMIT;
END;
/