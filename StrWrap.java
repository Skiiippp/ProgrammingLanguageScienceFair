package originalcode;



public class StrWrap {
    public String string;
    public String id;

    public StrWrap(String string, String id) {
        this.id = id;
        this.string = string;
    }

    public String getString(){
        return string;
    }

    public String setString(String string){
        this.string = string;
        return this.string;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
