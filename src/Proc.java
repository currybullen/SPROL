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
        Stack.getCellOf(a.left_hand_side)[0] = a.right_hand_side.eval();
    }

    private void execProcCall(ProcCall c) {
        //
        // fill in your code here to perform the procedure call


        if ((c.condition_y == null) || !c.condition_y.equals(c.condition_y)) {
            Proc sub = revursive(this, c.name);
            sub.initRecord(c.actual_par, static_depth - sub.static_depth + 1);
            sub.execute();
            Stack.deleteRecord();
        } else {
            return;
        }
    }

    private Proc revursive(Proc pc, String name) {

        for(int i = 0; i < pc.subproc.size(); i++) {
            if(pc.subproc.get(i).name.equals(name)) {
                return pc.subproc.get(i);
            }
        }

        return revursive(pc.parent, name);
    }
    protected void initRecord(Vector<String> actual, int calling_depth) {
        //
        // fill in your code here to create and initialize an activation record
        //

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
