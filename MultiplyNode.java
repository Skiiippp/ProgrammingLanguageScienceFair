package originalcode;

import com.oracle.truffle.api.frame.VirtualFrame;

public class MultiplyNode extends SciFairNode{

    @Child private NumNode num1, num2;

    public MultiplyNode(NumNode num1, NumNode num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public NumNode getNum1(){
        return new NumNode(this.num1.getNum()*this.num2.getNum());
    }

    @Override
    public Object execute(VirtualFrame frame) {
        int number1 = (int)num1.execute(frame);
        int number2 = (int)num2.execute(frame);
        return number1 * number2;
    }
}
