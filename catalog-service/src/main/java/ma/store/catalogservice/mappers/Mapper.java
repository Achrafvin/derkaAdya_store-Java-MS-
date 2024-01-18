package ma.store.catalogservice.mappers;

public interface Mapper <X,Y>{
    X mapTo(Y y);
    Y mapFrom(X x);

}
