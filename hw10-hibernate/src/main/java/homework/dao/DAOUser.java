package homework.dao;

public interface DAOUser<T> {
    void save(T objectData);
    <T> T read(long id, Class<T> clazz);
}
