public class Player {

    String name;
    String role;
    int life_point;

    public Player(String name, String role, int life_point) {
        this.name = name;
        this.role = role;
        this.life_point=life_point;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getLife_point() {
        return life_point;
    }



}
