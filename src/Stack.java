import java.util.Vector;

public class Stack extends Vector<Stack.ActRecord> {

	// the runtime stack of SPROL
	private static Stack st = new Stack();

	public static void addRecord(int nesting_depth) {
		int link = st.size() - 1;
		for (int i = 0; i < nesting_depth; i++) {
			ActRecord r = st.elementAt(link);
			link = r.static_link;
		}
		st.addElement(new ActRecord(link));
	}

	public static void deleteRecord() {
		st.setSize(st.size() - 1);
	}

	public static void addVariable(String name, Object[] mem_cell) {
		ActRecord r = st.elementAt(st.size() - 1);
		r.addElement(new Variable(name, mem_cell));
	}

	public static Object[] getCellOf(String name) {
		//
		// fill in your code here
		//

		for (int i = st.size()-1; i >= 0; i--) {
			ActRecord v = st.elementAt(i);
			if (v.getCellOf(name) != null) {
				return v.getCellOf(name);
			}
		}

		return null; // dummy return to be able to compile this version
	}

	public static Object getCellContentsOf(String name) {
		Object value = getCellOf((String) name)[0];
		if (value == null) {
			System.err.println("Error: uninitialized variable '" + name
					+ "' used");
			System.exit(0);
		}
		return value;
	}

	protected static class ActRecord extends Vector<Object> {
		public int static_link;

		public ActRecord(int static_link) {
			this.static_link = static_link;
		}

		public Object[] getCellOf(String name) {
			for (int i = 0; i < size(); i++) {
				Variable v = (Variable) elementAt(i);
				if (v.name.equals(name))
					return v.mem_cell;
			}
			return null;
		}
	}

	private static class Variable {
		public String name;

		public Object[] mem_cell;

		public Variable(String name, Object[] mem_cell) {
			this.name = name;
			if (mem_cell == null) {
				mem_cell = new Object[1];
				mem_cell[0] = null;
			}
			this.mem_cell = mem_cell;
		}
	}

}
