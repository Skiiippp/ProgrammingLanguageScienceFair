package originalcode;

import com.oracle.truffle.api.frame.VirtualFrame;

public class PrintNode extends SciFairNode{

    @Child private StringNode string;

    public PrintNode(StringNode string) {
        this.string = string;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        System.out.println(string.execute(frame));
        return(string.execute(frame));
    }
}
