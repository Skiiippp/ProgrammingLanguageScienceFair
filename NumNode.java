package originalcode;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.frame.VirtualFrame;

public class NumNode extends SciFairNode{

    int num;

    public NumNode(int num){

        this.num = num;

    }

    public int getNum(){

        return num;

    }


    @Override
    public Object execute(VirtualFrame frame) {
        return num;
    }
}
