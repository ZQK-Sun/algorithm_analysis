package personal_practice.test;


public class CovariantTypeTest {
    public static void main(String[] args) {
        /**
         * java中的数组是协变
         * 基本数据类型的数组无法协变,如long[] l = new int[5];编译不通过
         * java中的引用类型的数组里面存的是对象的引用(8字节的地址值), C++中是具体对象吗???
         *
         *
         * 泛型在很大程度是java语言中的成分而不是虚拟机中的结构. 只是为了简化编码的一种语法,在运行时类型擦除
         *
         */
        Number[] longs = new Long[5];
        Person[] stu = new Student[5];
        stu[0] = new Teacher("张Sir",28,"teacher");//编译通过,运行报错java.lang.ArrayStoreException(无法报类型转换错误)
        System.out.println(stu[0].getName());


        //参数化类型的数组的实例化是非法的
        GenericMemoryCell<String>[] arr1 = new GenericMemoryCell[10];//只能不带泛型参数实例化(new)
        GenericMemoryCell<Double> cell = new GenericMemoryCell<>();cell.write(4.5);
        Object[] arr2 = arr1;
        arr2[0] = cell;
        String s = arr1[0].read();//报错ClassCastException, 而如果写成Double类型编译不通过,故无法解决此错误
        System.out.println(s);


    }
}
