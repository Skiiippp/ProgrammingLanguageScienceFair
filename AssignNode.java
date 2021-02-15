package originalcode;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;



public class AssignNode extends SciFairNode{

    @Child private SciFairNode y;
    private IntWrap x;


    public AssignNode(SciFairNode y, IntWrap x) {
        this.y = y;
        this.x = x;
    }

    public SciFairNode getNode(){
        return y;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        x.num = (Integer) y.execute(frame);
        return null;
    }
}
