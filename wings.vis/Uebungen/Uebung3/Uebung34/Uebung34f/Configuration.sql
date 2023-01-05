--sqlplus /nolog
--1) 
CONNECT aqadm/aqadm;

--2) 
EXEC DBMS_AQADM.CREATE_QUEUE_TABLE(queue_table => 'multi_message_table',queue_payload_type => 'SYS.AQ$_JMS_OBJECT_MESSAGE',multiple_consumers => TRUE);
EXEC DBMS_AQADM.CREATE_QUEUE(queue_name => 'multi_queue',queue_table => 'multi_message_table');
EXEC DBMS_AQADM.START_QUEUE(queue_name => 'multi_queue');