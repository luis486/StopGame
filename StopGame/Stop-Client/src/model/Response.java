package model;

public class Response {

    private String type = "Response";

    private String name;

    private String animal;

    private String location;

    private String object;

    public Response(String type, String name, String animal, String location, String object) {
        this.type = type;
        this.name = name;
        this.animal = animal;
        this.location = location;
        this.object = object;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimal() {
        return this.animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getObject() {
        return this.object;
    }

    public void setObject(String thing) {
        this.object = thing;
    }

}
