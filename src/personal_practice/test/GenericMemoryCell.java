package personal_practice.test;

public class GenericMemoryCell<T> {
    private T storedValue;
    public T read(){
        return storedValue;
    }
    public void write(T x){
        storedValue = x;
    }

    //static方法不能引用类中的泛型参数,只能用自己定义的
    static <T> T test(T x){
        return x;
    }
}
