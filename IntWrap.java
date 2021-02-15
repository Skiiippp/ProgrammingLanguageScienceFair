package originalcode;

public class IntWrap {
    public Integer num;
    public String id;

    public IntWrap(Integer num, String id) {
        this.num = num;
        this.id = id;
    }

    public int getNum(){
        return num;
    }

    public int setNum(Integer num){
        this.num = num;
        return this.num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
