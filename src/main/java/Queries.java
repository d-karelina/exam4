import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

public class Queries {
    //2. Используя JPQL или Criteria API составить запросы для получения:


    // 2.1 всех гор
    public static List<Mountain> allMountains(EntityManager manager) {
        // SELECT * FROM Mountains ;
        CriteriaBuilder builder = manager.getCriteriaBuilder() ;
        CriteriaQuery<Mountain> criteriaQuery = builder.createQuery(Mountain.class) ;
        Root<Mountain> root = criteriaQuery.from(Mountain.class) ;

        criteriaQuery.select(root) ;

        TypedQuery<Mountain> typedQuery = manager.createQuery(criteriaQuery) ;
        List<Mountain> result = typedQuery.getResultList() ;

        return result ;
    }

    //2.2 гор с высотой от min до max
    public static List<Mountain> allMountainsFromMinToMax (EntityManager manager, int min, int max) {
        // SELECT * FROM Mountains WHERE height > ? AND height < ?;
        // SELECT g FROM Mountains WHERE g.height > ? AND g.height < ? ;

        CriteriaBuilder builder = manager.getCriteriaBuilder() ;
        CriteriaQuery<Mountain> criteriaQuery = builder.createQuery(Mountain.class) ;
        Root<Mountain> root = criteriaQuery.from(Mountain.class) ;

        Predicate condition1 = builder.greaterThan(root.get(Mountain_.height), min) ;
        Predicate condition2 = builder.lessThan(root.get(Mountain_.height), max) ;

        Predicate fullCondition = builder.and(condition1, condition2) ;

        TypedQuery<Mountain> typedQuery = manager.createQuery(criteriaQuery) ;
        List<Mountain> result = typedQuery.getResultList() ;

        criteriaQuery.select(root).where(fullCondition) ;

        return result ;
    }

    //2.3 групп, которые еще не начали восхождения но горы
    public static List<ClimbingGroup> groupsWhoDoNotStart (EntityManager manager) {
        // SELECT * FROM ClimbingGroups WHERE start < ? ;
        CriteriaBuilder builder = manager.getCriteriaBuilder() ;
        CriteriaQuery<ClimbingGroup> criteriaQuery = builder.createQuery(ClimbingGroup.class) ;
        Root<ClimbingGroup> root = criteriaQuery.from(ClimbingGroup.class) ;

        Predicate condition1 = builder.lessThan(root.get(ClimbingGroup_.start), LocalDateTime.now()) ;

        TypedQuery<ClimbingGroup> typedQuery = manager.createQuery(criteriaQuery) ;
        List<ClimbingGroup> result = typedQuery.getResultList() ;

        criteriaQuery.select(root).where(condition1) ;

        return result ;
    }

    //2.4 альпиниста по имени и email
    public static Climber climberByNameAndEmail (EntityManager manager, String name, String email) {

        CriteriaBuilder builder = manager.getCriteriaBuilder() ;
        CriteriaQuery<Climber> criteriaQuery = builder.createQuery(Climber.class) ;
        Root<Climber> root = criteriaQuery.from(Climber.class) ;

        Predicate condition1 = builder.equal(root.get(Climber_.fullName), name) ;
        Predicate condition2 = builder.equal(root.get(Climber_.email), email) ;

        Predicate fullCondition = builder.and(condition1, condition2) ;

        TypedQuery<Climber> typedQuery = manager.createQuery(criteriaQuery) ;
        Climber result = typedQuery.getSingleResult() ;

        criteriaQuery.select(root).where(fullCondition) ;

        return result ;
    }

    //2.5 гору по названию
    public static Mountain mountainByName (EntityManager manager, String name) {

        CriteriaBuilder builder = manager.getCriteriaBuilder() ;
        CriteriaQuery<Mountain> criteriaQuery = builder.createQuery(Mountain.class) ;
        Root<Mountain> root = criteriaQuery.from(Mountain.class) ;

        Predicate condition1 = builder.equal(root.get(Mountain_.name), name) ;

        TypedQuery<Mountain> typedQuery = manager.createQuery(criteriaQuery) ;
        Mountain result = typedQuery.getSingleResult() ;

        criteriaQuery.select(root).where(condition1) ;

        return result ;
    }
}
