--1) 
--ssh oracle@172.20.14.103 -oKexAlgorithms=+diffie-hellman-group14-sha1
--(alternativ: Eclipse-SSH-Terminal) 

--2) 
--sqlplus /nolog
connect / as sysdba    

--3a) CREATE ROLE my_aq_adm_role;
--3b) GRANT CONNECT, RESOURCE, aq_administrator_role TO my_aq_adm_role;

--4a) 
CREATE ROLE my_aq_user_role;
--4b) 
GRANT CREATE SESSION, aq_user_role TO my_aq_user_role;
--4c)
EXEC DBMS_AQADM.GRANT_SYSTEM_PRIVILEGE(privilege => 'ENQUEUE_ANY',grantee => 'my_aq_user_role',admin_option => FALSE);
--4d) 
EXEC DBMS_AQADM.GRANT_SYSTEM_PRIVILEGE(privilege => 'DEQUEUE_ANY',grantee => 'my_aq_user_role',admin_option => FALSE);

--5a.1) Falls bei 5a.2: "ORA-00959: tablespace 'TAB' does not exist"
--CREATE TABLESPACE tab DATAFILE 'tab_data.dbf' SIZE 10m;
--5a.2) 
CREATE USER aqadm IDENTIFIED BY aqadm DEFAULT TABLESPACE tab TEMPORARY TABLESPACE temp;
--5b) 
GRANT my_aq_adm_role TO aqadm;

--6a) 
CREATE USER aquser IDENTIFIED BY aquser DEFAULT TABLESPACE tab TEMPORARY TABLESPACE temp;
--6b) 
GRANT my_aq_user_role TO aquser;

--7) 
CONNECT aqadm/aqadm;

--8)
CREATE TYPE queue_message_type AS OBJECT(no NUMBER,title VARCHAR2(30),text VARCHAR2(2000) );
/
GRANT EXECUTE ON queue_message_type TO my_aq_user_role;

--9a) 
EXEC DBMS_AQADM.CREATE_QUEUE_TABLE(queue_table => 'queue_message_table',queue_payload_type => 'aqadm.queue_message_type');
--9b) 
EXEC DBMS_AQADM.CREATE_QUEUE(queue_name => 'message_queue',queue_table => 'queue_message_table');
--9c) 
EXEC DBMS_AQADM.START_QUEUE(queue_name => 'message_queue');