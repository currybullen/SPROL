import org.omg.PortableInterceptor.DISCARDING;

public class Expression {

    private static final int CONSTANT = 0;
    private static final int VARIABLE = 1;
    private static final int DOT_APPLICATION = 2;
    private static final int TILDE_APPLICATION = 3;
  
    private int type;
    private String var_name;
    private String const_value;
    private Expression[] subexpr;
  
    private Expression(int type, int number_of_subexpr) {
        this.type = type;
        subexpr = new Expression[number_of_subexpr];
    }
  
    public static Expression constant(String value) {
        Expression result = new Expression(CONSTANT, 0);
        result.const_value = value;
        return result;
    }
  
    public static Expression variable(String name) {
        Expression result = new Expression(VARIABLE, 0);
        result.var_name = name;
        return result;
    }
  
    public static Expression dotAppl(Expression sub1, Expression sub2) {
        Expression result = new Expression(DOT_APPLICATION, 2);
        result.subexpr[0] = sub1;
        result.subexpr[1] = sub2;
        return result;
    }
  
    public static Expression tildeAppl(Expression sub) {
        Expression result = new Expression(TILDE_APPLICATION, 1);
        result.subexpr[0] = sub;
        return result;
    }
    
    public Object eval() {
        //
        // fill in your code here to evaluate this expression
        //
        if (type == TILDE_APPLICATION) {

            return Integer.valueOf(subexpr[0].toString()) * -1;
        }

        if (type == DOT_APPLICATION) {

            return Integer.valueOf(subexpr[0].toString()) + Integer.valueOf(subexpr[1].toString());
        }


        return null; // dummy return to be able to compile this version
    }
  
}
