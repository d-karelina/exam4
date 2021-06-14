import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_climber")
public class Climber {
    @Column(nullable = false, length = 100)
    private String fullName ;
    @Column(nullable = false, length = 2)
    private int age ;
    @Column(nullable = false, length = 50)
    private String email ;
    @Column(nullable = false)
    @Id
    private UUID uuid ;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "climbers")
    List<ClimbingGroup> groups = new ArrayList<>() ;


    //methods
    // методы, которые устанавливают значения свойств (с проверками) называют сеттеры
    public void setFullName(String fullName){
        if (fullName == null || fullName.trim().length() < 3) {
            throw new IllegalArgumentException("Значение fullName < 3") ;
        }
        this.fullName = fullName ;
    }

    public void setAge (int age) {
        if (age < 18) throw new IllegalArgumentException("Значение age 18 или больше!") ;
        this.age = age ;
    }

    //ALT + Inert или ALT + Fn + Insert или правая кнопка мыши

    public void setEmail(String email) {
        if (email == null || !email.trim().contains("@"))
            throw new IllegalArgumentException("Это не email") ;
        this.email = email ;
    }

    public void setUuid() {
        uuid = UUID.randomUUID();
    }

    public String getFullName() {
        return fullName ;
    }

    public int getAge() {
        return age ;
    }

    public String getEmail() {
        return email;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    protected Climber clone()  {
        Climber copy = new Climber() ;

        copy.uuid = this.uuid ;
        copy.email = this.email ;
        copy.age = this.age ;
        copy.fullName = this.fullName ;

        return copy;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Climber)) return false;
        Climber climber = (Climber) o;
        return age == climber.age && fullName.equals(climber.fullName) && email.equals(climber.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, age, email, uuid);
    }

    @Override
    public String toString() {
        return "Climber{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}