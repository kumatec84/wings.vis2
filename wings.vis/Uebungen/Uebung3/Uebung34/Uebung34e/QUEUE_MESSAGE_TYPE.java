import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class QUEUE_MESSAGE_TYPE implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "AQADM.QUEUE_MESSAGE_TYPE";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[3];
  protected static final QUEUE_MESSAGE_TYPE _QUEUE_MESSAGE_TYPEFactory = new QUEUE_MESSAGE_TYPE();

  public static ORADataFactory getORADataFactory()
  { return _QUEUE_MESSAGE_TYPEFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[3], _sqlType, _factory); }
  public QUEUE_MESSAGE_TYPE()
  { _init_struct(true); }
  public QUEUE_MESSAGE_TYPE(java.math.BigDecimal no, String title, String text) throws SQLException
  { _init_struct(true);
    setNo(no);
    setTitle(title);
    setText(text);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(QUEUE_MESSAGE_TYPE o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new QUEUE_MESSAGE_TYPE();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getNo() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setNo(java.math.BigDecimal no) throws SQLException
  { _struct.setAttribute(0, no); }


  public String getTitle() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setTitle(String title) throws SQLException
  { _struct.setAttribute(1, title); }


  public String getText() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setText(String text) throws SQLException
  { _struct.setAttribute(2, text); }

}
