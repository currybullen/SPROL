import java.util.Vector;

public class Proc {

    private String name;
    private Proc parent;
    private int static_depth = 0;
    public Vector<Proc> subproc = new Vector<Proc>();
    public Vector<String> local_var = new Vector<String>();
    public Vector<String> formal_par = new Vector<String>();
    public Vector statement = new Vector();
  
    public Proc(String name, Proc parent) {
        this.name = name;
        this.parent = parent;
        if (parent != null) static_depth = parent.static_depth + 1;
    }
  
    public void execute() {
        // dr f
        // execute the statements of this procedure one by one
        // using exec_assignment(...) or exec_proc_call(...) depending
        // on the type of the respective statement.
        //
        for (int i = 0; i < statement.size(); i++) {
            if (statement.elementAt(i) instanceof Assignment)
                execAssignment((Assignment)statement.elementAt(i));
            else execProcCall((ProcCall)statement.elementAt(i));
        }
    }
  
    private void execAssignment(Assignment a) {
        //
        // fill in your code here to execute the assignment
        //
        System.err.println("Gör execassign på "+ a.left_hand_side + " till " + a.right_hand_side.eval());
        Stack.getCellOf(a.left_hand_side)[0] = a.right_hand_side.eval();
    }
  
    private void execProcCall(ProcCall c) {
        //
        // fill in your code here to perform the procedure call
        System.err.println("helkjarg");



        if ((c.condition_y == null) || !c.condition_y.equals(c.condition_y)) {
            Boolean exists = false;
            for (int i = 0; i < subproc.size(); i++) {
                System.err.println(subproc.get(i).name + " " + c.name);

                if (subproc.get(i).name.equals(c.name)) {

                    exists = true;
                    System.err.println("DEN FINNS");
                }
            }
            if (exists) {
                for (int i = 0; i < subproc.size(); i++) {
                    System.err.println(subproc.get(i).name + " " + c.name);

                    if (subproc.get(i).name.equals(c.name)) {
                        Proc sub = subproc.get(i);
                        sub.initRecord(c.actual_par, static_depth);
                        sub.execute();
                        Stack.deleteRecord();
                        break;
                    }
                }

            } else {
                if (parent != null) parent.execProcCall(c);
                else System.err.println("PARENT ÄR NULL");
            }

        } else {
            System.err.println("nu är det null eller lika");
            return;
        }
    }
  
    protected void initRecord(Vector<String> actual, int calling_depth) {
        //
        // fill in your code here to create and initialize an activation record
        //

        System.err.println("Gör init record\n");
        Stack.addRecord(calling_depth);

        for (int i = 0; i<actual.size(); i++){
            Object[] stackobj = Stack.getCellOf(actual.get(i));
            Stack.addVariable(formal_par.get(i), stackobj);

        }

        for (int i = 0; i<local_var.size(); i++) {
            Stack.addVariable(local_var.get(i), null);
        }

    }
  
}
