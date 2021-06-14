import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Climber.class)
public abstract class Climber_ {

	public static volatile SingularAttribute<Climber, String> fullName;
	public static volatile ListAttribute<Climber, ClimbingGroup> groups;
	public static volatile SingularAttribute<Climber, UUID> uuid;
	public static volatile SingularAttribute<Climber, Integer> age;
	public static volatile SingularAttribute<Climber, String> email;

	public static final String FULL_NAME = "fullName";
	public static final String GROUPS = "groups";
	public static final String UUID = "uuid";
	public static final String AGE = "age";
	public static final String EMAIL = "email";

}

