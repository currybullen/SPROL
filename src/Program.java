import java.util.Vector;

public class Program extends Proc {

    public Program(String name) { super(name, null); }
  
    public void execute() {
        Stack.addRecord(0);     
        for (int i = 0; i < local_var.size(); i++) {
            Stack.addVariable((String)local_var.elementAt(i), null);
        }
        super.execute();
        for (int i = 0; i < local_var.size(); i++) {
            String v = (String)local_var.elementAt(i);
            System.out.println(v + " = " + Stack.getCellContentsOf(v));
        }
    }

}
