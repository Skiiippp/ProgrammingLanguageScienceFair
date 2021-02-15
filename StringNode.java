package originalcode;

import com.oracle.truffle.api.frame.VirtualFrame;

public class StringNode extends SciFairNode{

    String string;

    public StringNode(String string) {
        this.string = string;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return string;
    }
}
