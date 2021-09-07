package hr.brocom.generic.abstraction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;

public class AbstractSearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {

    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root r;

    public AbstractSearchQueryCriteriaConsumer(final Predicate predicate, final CriteriaBuilder builder, final Root r) {
        this.predicate = predicate;
        this.builder = builder;
        this.r = r;
    }

    @Override
    public void accept(final SearchCriteria param) {
        final OperationEnum operation = param.getOperation();

        switch (operation) {
            case GTE:
                predicate = builder.and(predicate, builder
                        .greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
                break;
            case GT:
                predicate = builder.and(predicate, builder
                        .greaterThan(r.get(param.getKey()), param.getValue().toString()));
                break;
            case LTE:
                predicate = builder.and(predicate, builder.lessThanOrEqualTo(
                        r.get(param.getKey()), param.getValue().toString()));
                break;
            case LT:
                predicate = builder.and(predicate, builder.lessThan(
                        r.get(param.getKey()), param.getValue().toString()));
                break;
            case EQ:
                if (r.get(param.getKey()).getJavaType() == String.class) {
                    predicate = builder.and(predicate, builder.like(
                            r.get(param.getKey()), "%" + param.getValue() + "%"));
                } else {
                    predicate = builder.and(predicate, builder.equal(
                            r.get(param.getKey()), param.getValue()));
                }
                break;
            case NEQ:
                predicate = builder.and(predicate, builder.notEqual(
                        r.get(param.getKey()), param.getValue()));
                break;

            default:
                throw new IllegalArgumentException();
        }

    }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(final Predicate predicate) {
        this.predicate = predicate;
    }

    public CriteriaBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(final CriteriaBuilder builder) {
        this.builder = builder;
    }

    public Root getR() {
        return r;
    }

    public void setR(final Root r) {
        this.r = r;
    }

}
