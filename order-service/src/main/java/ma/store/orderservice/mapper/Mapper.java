package ma.store.orderservice.mapper;

public interface Mapper <X,Y>{
    X mapTo(Y y);
    Y mapFrom(X x);
}
