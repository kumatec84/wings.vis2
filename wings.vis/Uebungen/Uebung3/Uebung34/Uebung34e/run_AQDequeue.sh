#!/bin/bash
export CLASSPATH=".:/oracle/oracle112/JRE:/oracle/oracle112/jlib:/oracle/oracle112/rdbms/jlib:/oracle/oracle112/jdbc/lib/ojdbc5.jar:/oracle/oracle112/oc4j/sqlj/lib/translator.jar:/oracle/oracle112/oc4j/sqlj/lib/runtime12.jar:/oracle/oracle112/oc4j/rdbms/jlib/aqapi.jar:/oracle/oracle112/oui/jlib/classes12.jar:/oracle/oracle112/rdbms/jlib/jmscommon.jar"
/oracle/oracle112/jdk/bin/java  oracle.jpub.Doit  -user=aqadm/aqadm -sql=QUEUE_MESSAGE_TYPE -usertypes=oracle -methods=false
/oracle/oracle112/jdk/bin/javac *.java
/oracle/oracle112/jdk/bin/java AQDequeue