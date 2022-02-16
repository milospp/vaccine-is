package zajednicko.service;

public interface MarshallingService {

    <T> T unmarshall(String xmlString, Class<T> clazz);

    <T> String marshall(T object, Class<T> clazz);
}