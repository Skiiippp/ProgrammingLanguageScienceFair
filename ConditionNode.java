package originalcode;

import com.oracle.truffle.api.frame.VirtualFrame;

public class ConditionNode extends SciFairNode{

    @Child private NumNode num1, num2;
    final String comparator;


    public ConditionNode(NumNode num1, NumNode num2, String comparator){
        this.num1 = num1;
        this.num2 = num2;
        this.comparator = comparator;


    }


    @Override
    public Object execute(VirtualFrame frame) {
        if (comparator=="<")
            return((int)num1.execute(frame)<(int)num2.execute(frame));
        return((int)num1.execute(frame)>(int)num2.execute(frame));

    }
}
