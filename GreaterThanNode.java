package originalcode;

import com.oracle.truffle.api.frame.VirtualFrame;

public class GreaterThanNode extends SciFairNode{

    @Child private NumNode num1,num2;

    public GreaterThanNode(NumNode num1, NumNode num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        if((int)num1.execute(frame) > (int)num2.execute(frame))
            return true;
        return false;
    }
}