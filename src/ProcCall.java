import java.util.Vector;

public class ProcCall {

    public String name;
    public Vector<String> actual_par = new Vector<String>();
  
    public String condition_x = null;
    public String condition_y = null;

    public ProcCall(String name) { this.name = name; }
  
    public void setCondition(String x, String y) {
        condition_x = x;
        condition_y = y;
    }
  
}
