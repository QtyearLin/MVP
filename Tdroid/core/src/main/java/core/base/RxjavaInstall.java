package core.base;

/**
 * Created by Qtyearlin on 2017/2/6.
 */

public class RxjavaInstall {

    /*前两篇为大家介绍了使用RxJava打印多个字符串的方法

    Observable.just("Hellow", "Wrold").subscribe(new Action1<String>() {
        @Override
        public void call(String s) {
            Log.i(TAG, s);
        }
    });
    这样的例子基本没有实际用处，只是为大家演示如何使用Rxjava。今天就抛开这个例子。

    map

    在使用map之前要先说道一个接口：Func1，Func1和上一篇提到的Action1相似。Func1 和 Action的区别在于， Func1 包装的是有返回值的方法。
    接下来就是map的用法，看代码更直观点；
    例：得到多个Student对象中的name，保存到nameList中

    Observable.just(student1, student2, student2)
            //使用map进行转换，参数1：转换前的类型，参数2：转换后的类型
            .map(new Func1<Student, String>() {
        @Override
        public String call(Student i) {
            String name = i.getName();//获取Student对象中的name
            return name;//返回name
        }
    })
            .subscribe(new Action1<String>() {
        @Override
        public void call(String s) {
            nameList.add(s);
        }
    });
    可以看到Observable中原来的参数是Student对象，而最后我们需要的是name，这里使用了map来实现这一转换的过程。当然，map可以多次使用。

            //多次使用map，想用几个用几个
            Observable.just("Hello", "World")
            .map(new Func1<String, Integer>() {//将String类型的转化为Integer类型的哈希码
        @Override
        public Integer call(String s) {
            return s.hashCode();
        }
    })
            .map(new Func1<Integer, String>() {//将转化后得到的Integer类型的哈希码再转化为String类型
        @Override
        public String call(Integer integer) {
            return integer.intValue() + "";
        }
    })
            .subscribe(new Action1<String>() {
        @Override
        public void call(String s) {
            Log.i(TAG, s);
        }
    });
    flatMap

    flatMap是一个比教难理解的一个转换，在这里先假设一个需求，需要打印多个Student所学的课程。这跟之前获取Student的name又不同了，这里先确定一下关系，一个Student类中只有一个name，而一个Student却有多门课程（Course），Student我们可以理解成这样：

    *//**
     * 学生类
     *//*
    class Student {
        private String name;//姓名
        private List<Course> coursesList;//所修的课程
        ...
    }
    *//**
     * 课程类
     *//*
    class  Course {
        private String name;//课程名
        private String id;
        ...
    }
    如果使用map来实现打印所有学生所修个课程名，实现的代码是这样的：

    List<Student> students = new ArrayList<Student>();
    students.add...
            ...

    Action1<List<Course>> action1 = new Action1<List<Course>>() {
        @Override
        public void call(List<Course> courses) {
            //遍历courses，输出cuouses的name
            for (int i = 0; i < courses.size(); i++){
                Log.i(TAG, courses.get(i).getName());
            }
        }
    };
    Observable.from(students)
            .map(new Func1<Student, List<Course>>() {
        @Override
        public List<Course> call(Student student) {
            //返回coursesList
            return student.getCoursesList();
        }
    })
            .subscribe(action1);
    可以看到，在Action1中出现了for来循环打印课程名，使用RxJava就是为了剔除这样的嵌套结构，使得整体的逻辑性更强。这时候就可以使用flatMap了，使用flatMap实现的代码是这样的：

    List<Student> students = new ArrayList<Student>();
    students.add...
            ...

            Observable.from(students)
            .flatMap(new Func1<Student, Observable<Course>>() {
        @Override
        public Observable<Course> call(Student student) {
            return Observable.from(student.getCoursesList());
        }
    })
            .subscribe(new Action1<Course>() {
        @Override
        public void call(Course course) {
            Log.i(TAG, course.getName());
        }
    });
    这样就实现了跟上面代码一样的效果，看起来有点懵？确实，flatMap理解起来有点绕，刚接触flatMap的时候我也是懵逼一个。下面我将flatMap的示意图，希望能帮助理解：


    flatMap示意图

    由上图可以看出Student1、Student2经过flatMap后，按顺序依次经历了Observable1、Observable2，分别转化为Course。最后按顺序得到Course1、Course2、Course3、Course4、Course5、Course6，其中1-3由Student1得到，4-6由Student2得到。
    结合代码和示意图，是不是对flatMap有了一定的理解。

    其他操作符

    除了map和flatMap之外，还有其他操作符以供使用。这里就不一一列举他们的用法了，其他常用的操作符如下：

    filter：集合进行过滤
    each：遍历集合
    take：取出集合中的前几个
    skip：跳过前几个元素
    unique：相当于按照数学上的集合处理，去重*/
}
