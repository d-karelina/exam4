import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "tb_climbing_group")
public class ClimbingGroup {

    @ManyToOne
    private Mountain mountain ;

    @ManyToMany(fetch = FetchType.LAZY)
    @OrderColumn
    @JoinTable(name = "tb_climbers_groups", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "climber_id"))
    private Climber[] climbers ;
    @Column @Id
    private LocalDateTime start ;

    public ClimbingGroup(Mountain mountain, int climberCount, LocalDateTime start) {
        this.mountain = Objects.requireNonNull(mountain, "mountain не может быть null");
        climbers = new Climber[climberCount] ;
        this.start = start ;
    }

    public void addClimber(Climber climber){
        Objects.requireNonNull(climber, "climber не может быть null");
        for (int i = 0; i < climbers.length; i++) {
            if (climbers[i] == null) {
                climbers[i] = climber ;
                return; //в void методах return работает для завершения работы метода
            }
        }
        System.out.println("мест нет");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClimbingGroup)) return false;
        ClimbingGroup that = (ClimbingGroup) o;
        return mountain.equals(that.mountain) && Arrays.equals(climbers, that.climbers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mountain);
        result = 31 * result + Arrays.hashCode(climbers);
        return result;
    }

    @Override
    protected ClimbingGroup clone() {
        Mountain copyM = this.mountain.clone();
        ClimbingGroup copy = new ClimbingGroup(copyM, climbers.length, start) ;
        copy.climbers = climbers.clone();
        return copy;
    }

    @Override
    public String toString() {
        return "ClimbingGroup{" +
                "mountain=" + mountain +
                ", climbers=" + Arrays.toString(climbers) +
                '}';
    }
}