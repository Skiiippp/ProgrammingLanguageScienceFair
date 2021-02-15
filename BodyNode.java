package originalcode;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.VirtualFrame;

public class BodyNode extends SciFairNode{

    @Children private SciFairNode[] nodes;

    public BodyNode(SciFairNode[] nodes) {
        this.nodes = nodes;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        CallTarget target;

        for(int i = 0; i < nodes.length; i++){
            target = Truffle.getRuntime().createCallTarget(new SciFairRootNode(nodes[i]));
            target.call();
        }
        return null;
    }
}
