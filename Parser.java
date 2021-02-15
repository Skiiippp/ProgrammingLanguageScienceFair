package originalcode;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;

import java.util.ArrayList;


public class Parser {

    private  ArrayList<Token> tokenList;
    private  ArrayList<Object> toDo = new ArrayList<Object>();

    public void setTokenList(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public ArrayList<Object> parse(ArrayList<Token> tokenList) {
        //System.out.println("");

        for (int i = 0; i < tokenList.size(); i++) {

            String lexem = tokenList.get(i).getLexem();
            String type = tokenList.get(i).getType();

            if (lexem == "num") {
                toDo.add(new IntWrap(Integer.parseInt(tokenList.get(i + 3).getLexem()), tokenList.get(i + 1).getLexem()));
                i += 4;
            }

            else if (lexem == "String") {
                toDo.add(new StrWrap(tokenList.get(i + 3).getLexem(), tokenList.get(i + 2).getLexem()));
                i += 4;
            }

            else if (lexem == "print") {
                toDo.add(new PrintNode(new StringNode(tokenList.get(i + 2).getLexem())));
                i += 3;
            }

            else if (type == "id" && lexem != "=" && (tokenList.get(i - 1).getLexem() == "|" || tokenList.get(i - 1).getLexem() == "[")) {
                if (tokenList.get(i + 3).getType() != "oper") {
                    if (tokenList.get(i + 2).getType() == "id") {
                        for (int j = 0; j < toDo.size(); j++) {
                            if (((IntWrap) toDo.get(j)).id == tokenList.get(i + 2).getLexem()) {
                                for (int k = 0; k < toDo.size(); k++) {
                                    if (toDo.get(k).getClass() == IntWrap.class && ((IntWrap) toDo.get(k)).id == lexem)
                                        toDo.add(new AssignNode(new NumNode(((IntWrap) toDo.get(j)).num), (IntWrap) toDo.get(k)));
                                }
                            }
                        }
                    } else {
                        for (int j = 0; j < toDo.size(); j++) {
                            if (((IntWrap) toDo.get(j)).id == tokenList.get(i + 2).getLexem()) {
                                toDo.add(new AssignNode(new NumNode(Integer.parseInt(lexem)), (IntWrap) toDo.get(j)));
                            }
                        }
                    }
                } else {
                    int j = 0;
                    int k = 0;

                    //2nd Condition on all internal if loops always saying false!

                    if (tokenList.get(i + 2).getType() == "id" && tokenList.get(i + 4).getType() == "lit") {
                        for (int m = 0; m < toDo.size(); m++) {
                            if (toDo.get(m).getClass() == IntWrap.class && ((IntWrap) toDo.get(m)).id.equals(tokenList.get(i + 2).getLexem())) {
                                j = ((IntWrap) toDo.get(m)).num;
                            }
                        }
                        k = Integer.parseInt(tokenList.get(i + 4).getLexem());
                    } else if (tokenList.get(i + 2).getType() == "lit" && tokenList.get(i + 4).getType() == "id") {
                        for (int m = 0; m < toDo.size(); m++) {
                            if (toDo.get(m).getClass() == IntWrap.class && ((IntWrap) toDo.get(m)).id.equals(tokenList.get(i + 4).getLexem())) {
                                j = ((IntWrap) toDo.get(m)).num;

                            }
                        }
                        k = Integer.parseInt(tokenList.get(i + 2).getLexem());
                    } else if (tokenList.get(i + 2).getType() == "id" && tokenList.get(i + 4).getType() == "id") {
                        for (int m = 0; m < toDo.size(); m++) {
                            if (toDo.get(m).getClass() == IntWrap.class && ((IntWrap) toDo.get(m)).id.equals(tokenList.get(i + 2).getLexem())) {
                                j = ((IntWrap) toDo.get(m)).num;
                            }
                        }
                        for (int m = 0; m < toDo.size(); m++) {
                            if (toDo.get(m).getClass() == IntWrap.class && ((IntWrap) toDo.get(m)).id.equals(tokenList.get(i + 4).getLexem())) {
                                k = ((IntWrap) toDo.get(m)).num;
                            }
                        }
                    } else if (tokenList.get(i + 2).getType() == "lit" && tokenList.get(i + 4).getType() == "lit") {
                        j = Integer.parseInt(tokenList.get(i + 2).getLexem());
                        k = Integer.parseInt(tokenList.get(i + 4).getLexem());
                    }
                    for (int l = 0; l < toDo.size(); l++) {
                        if (toDo.get(l).getClass() == IntWrap.class && ((IntWrap) toDo.get(l)).id.equals(lexem)) {
                            toDo.add(new AssignNode(new MultiplyNode(new NumNode(j), new NumNode(k)), (IntWrap) toDo.get(l)));
                        }
                    }
                }
            }


           else if (lexem=="if") {

               for(int x = 0; x < toDo.size(); x++){
                   if(toDo.get(x).getClass() == AssignNode.class){
                       CallTarget epic = Truffle.getRuntime().createCallTarget(new SciFairRootNode((SciFairNode) toDo.get(x)));
                       epic.call();
                   }
               }

                for (int m = 0; m < toDo.size(); m++) {
                    if (toDo.get(m).getClass() == IntWrap.class && ((IntWrap) toDo.get(m)).id.equals(tokenList.get(i + 2).getLexem())) {
                         //((IntWrap) toDo.get(m)).num;
                    }
                }

               int aa = 0;
               for(int z = i + 7; z < tokenList.size();z++){
                   if(tokenList.get(z).getLexem() == "]") {
                       aa = z;
                       break;
                   }
               }


                String comp = "";
                comp = tokenList.get(i+3).getLexem();

                int num1 = 0;
                int num2 = 0;

                if(tokenList.get(i+2).getType()=="id"&&tokenList.get(i+4).getType()=="lit"){

                    for (int m = 0; m < toDo.size(); m++) {
                        if (toDo.get(m).getClass() == IntWrap.class && ((IntWrap) toDo.get(m)).id.equals(tokenList.get(i + 2).getLexem())) {
                            num1 = ((IntWrap) toDo.get(m)).num;
                        }
                    }

                    num2 = Integer.parseInt(tokenList.get(i+4).getLexem());

                }
                if(tokenList.get(i+2).getType()=="lit"&&tokenList.get(i+4).getType()=="id"){

                    for (int m = 0; m < toDo.size(); m++) {
                        if (toDo.get(m).getClass() == IntWrap.class && ((IntWrap) toDo.get(m)).id.equals(tokenList.get(i + 4).getLexem())) {
                            num1 = ((IntWrap) toDo.get(m)).num;
                        }
                    }

                    num2 = Integer.parseInt(tokenList.get(i+2).getLexem());

                }
                if(tokenList.get(i+2).getType()=="id"&&tokenList.get(i+4).getType()=="id"){

                    for (int m = 0; m < toDo.size(); m++) {
                        if (toDo.get(m).getClass() == IntWrap.class && ((IntWrap) toDo.get(m)).id.equals(tokenList.get(i + 2).getLexem())) {
                            num1 = ((IntWrap) toDo.get(m)).num;
                        }
                    }

                    for (int m = 0; m < toDo.size(); m++) {
                        if (toDo.get(m).getClass() == IntWrap.class && ((IntWrap) toDo.get(m)).id.equals(tokenList.get(i + 4).getLexem())) {
                            num2 = ((IntWrap) toDo.get(m)).num;
                        }
                    }

                }
                if(tokenList.get(i+2).getType()=="lit"&&tokenList.get(i+4).getType()=="lit"){

                    num1 = Integer.parseInt(tokenList.get(i+2).getLexem());

                    num2 = Integer.parseInt(tokenList.get(i+4).getLexem());

                }

                ConditionNode con = null;

                if(comp=="<") {
                    con = new ConditionNode(new NumNode(num1), new NumNode(num2), "<");
                } else{
                    con = new ConditionNode(new NumNode(num1), new NumNode(num2), ">");
                }

                ArrayList<Token> miniList = new ArrayList<Token>();
                ArrayList<SciFairNode> miniDo = new ArrayList<SciFairNode>();
                for(int x = i + 7;x < aa;x++){
                    miniList.add(tokenList.get(x));
                }

                for(int x = 0; x < miniList.size(); x++){



                    if (miniList.get(x).getLexem() == "print") {
                        miniDo.add(new PrintNode(new StringNode(miniList.get(x+2).getLexem())));
                        x += 3;
                    }



                }

                //System.out.println(miniDo.size());

                SciFairNode[] cringe = new SciFairNode[miniDo.size()];
                for(int x = 0; x < miniDo.size(); x++){
                    cringe[x] = miniDo.get(x);

                }



                toDo.add(new IfNode(con,new BodyNode(cringe)));

                /*ArrayList<Token> miniList = new ArrayList<Token>();
                ArrayList<SciFairNode> miniDo = new ArrayList<SciFairNode>();
                for(int x = i + 7;x < aa;x++){
                    miniList.add(tokenList.get(x));
                }

                for(int x = 0; x < miniList.size(); x++)
                    System.out.println(miniList.get(x).getLexem());

                ArrayList<Object> temp = parse(miniList);


                for(int x = 0; x < temp.size(); x++)
                    System.out.println(temp.get(x).getClass());

                for(int x = 0; x < temp.size(); x++){
                    if(temp.get(x).getClass() == SciFairNode.class){
                        System.out.println((SciFairNode) temp.get(x));
                        miniDo.add((SciFairNode) temp.get(x));
                    }
                }

                SciFairNode[] miniminiDo = new SciFairNode[miniDo.size()];

                for(int x = 0; x< miniDo.size(); x++)
                    miniminiDo[x] = miniDo.get(x);

                toDo.add(new IfNode(con,new BodyNode(miniminiDo)));*/

                i = aa;
            }



        }

        return toDo;
    }

    public ArrayList getToDo() {
        return toDo;
    }
}







































