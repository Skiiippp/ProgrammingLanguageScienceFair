package originalcode;

import com.oracle.truffle.api.frame.VirtualFrame;

public class IfNode extends SciFairNode{

    @Child private ConditionNode conditionNode;
    @Child private BodyNode bodyNodeIf;
    //@Child private BodyNode bodyNodeElse;

    public IfNode(ConditionNode conditionNode, BodyNode bodyNodeIf /*BodyNode bodyNodeElse*/) {
        this.conditionNode = conditionNode;
        this.bodyNodeIf = bodyNodeIf;
        //this.bodyNodeElse = bodyNodeElse;
    }

    @Override
    public Object execute(VirtualFrame frame) {

        if((Boolean) (conditionNode.execute(frame)))
            bodyNodeIf.execute(frame);
        /*else
            bodyNodeElse.execute(frame);*/
        return null;
    }
}
