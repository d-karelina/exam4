import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ClimbingGroup.class)
public abstract class ClimbingGroup_ {

	public static volatile SingularAttribute<ClimbingGroup, Climber[]> climbers;
	public static volatile SingularAttribute<ClimbingGroup, Mountain> mountain;
	public static volatile SingularAttribute<ClimbingGroup, LocalDateTime> start;

	public static final String CLIMBERS = "climbers";
	public static final String MOUNTAIN = "mountain";
	public static final String START = "start";

}

