package originalcode;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class SciFairNode extends Node{

    public abstract Object execute(VirtualFrame frame);

}
