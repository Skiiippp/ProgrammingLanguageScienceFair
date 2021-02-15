package originalcode;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RootNode;

public class SciFairRootNode extends RootNode {

    @Child SciFairNode sciFairNode;

    public SciFairRootNode(SciFairNode sciFairNode){
        super(null);
        this.sciFairNode = sciFairNode;
    }

    public Object execute(VirtualFrame frame){
        return sciFairNode.execute(frame);
    }

}
