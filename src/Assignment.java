import java.util.Vector;

public class Assignment {

    public String left_hand_side;
    public Expression right_hand_side;
  
    public Assignment(String lhs, Expression rhs) {
        left_hand_side = lhs;
        right_hand_side = rhs;
    }
  
}
