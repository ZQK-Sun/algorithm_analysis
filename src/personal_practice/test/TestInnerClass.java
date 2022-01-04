package personal_practice.test;

public class TestInnerClass {
    public static void main(String[] args) {
        Mytest mytest = new Mytest();
        Mytest.Node.play(mytest);
        Mytest.Node node = new Mytest.Node();
        node.settName("高中老师");
        node.testOuterMethod();//嵌套类里面可以引用外部内的static方法

    }
}
