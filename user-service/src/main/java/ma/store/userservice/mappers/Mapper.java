package ma.store.userservice.mappers;

public interface Mapper <X,Y>{
    X mapTo(Y y);
    Y mapFrom(X x);

}
