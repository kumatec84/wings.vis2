import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class QUEUE_MESSAGE_TYPERef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "AQADM.QUEUE_MESSAGE_TYPE";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final QUEUE_MESSAGE_TYPERef _QUEUE_MESSAGE_TYPERefFactory = new QUEUE_MESSAGE_TYPERef();

  public static ORADataFactory getORADataFactory()
  { return _QUEUE_MESSAGE_TYPERefFactory; }
  /* constructor */
  public QUEUE_MESSAGE_TYPERef()
  {
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _ref;
  }

  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    QUEUE_MESSAGE_TYPERef r = new QUEUE_MESSAGE_TYPERef();
    r._ref = (REF) d;
    return r;
  }

  public static QUEUE_MESSAGE_TYPERef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (QUEUE_MESSAGE_TYPERef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to QUEUE_MESSAGE_TYPERef: "+exn.toString()); }
  }

  public QUEUE_MESSAGE_TYPE getValue() throws SQLException
  {
     return (QUEUE_MESSAGE_TYPE) QUEUE_MESSAGE_TYPE.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(QUEUE_MESSAGE_TYPE c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
