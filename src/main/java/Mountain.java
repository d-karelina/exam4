import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_mountain")
public class Mountain implements Cloneable{
    @Id
    @Column(nullable = false, length = 50)
    private String name ;
    @Column(nullable = false, length = 4)
    private int height ;

    @OneToMany(mappedBy = "mountain")
    List<ClimbingGroup> groups = new ArrayList<>() ;

    //конструктор
    public Mountain(String name, int height){
        setName(name);
        setHeight(height);
    }

    public Mountain(){
        //вызов конструктора
        this ("Гора по умолчанию", 300);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().length() < 2)
            throw new IllegalArgumentException() ;
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        if (height < 100)
            throw new IllegalArgumentException("height меньше 100") ;
        this.height = height;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mountain)) return false;
        Mountain mountain = (Mountain) o;
        return height == mountain.height && name.equals(mountain.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, height);
    }

    @Override
    public Mountain clone () {
        try {
            return (Mountain) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }

    }

    @Override
    public String toString() {
        return "Mountain{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}