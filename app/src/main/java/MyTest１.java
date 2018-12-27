

import org.reactivestreams.Publisher;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import testmodel.Cat;
import testmodel.dog;


/**
 * Crate by E470PD on 2018/8/13
 */
public class MyTest１ {
    public static String TAG = "TAG";

    public static void main(String args[]) {
//        map();
        maptwo();
        flatMapTest();
    }

    /*map 转换符*/
    public static void maptwo() {
        dog dog1 = new dog("M", 10);
        Flowable.just(dog1).map(new Function<dog, Cat>() {
            @Override
            public Cat apply(dog dog) throws Exception {
                Cat cat1 = new Cat(dog.name, dog.age);
                return cat1;
            }
        }).subscribe(
                new Consumer<Cat>() {
                    @Override
                    public void accept(Cat cat) throws Exception {
                        System.out.println("MyTest１.accept" + cat.toString() + (cat instanceof Cat));
                    }
                }
        );
    }

    /*flatmap转换*/
    public static void flatMapTest() {

        List<Integer> list = Arrays.asList(1, 2, 3);
        Flowable.just(list)
                .flatMap(new Function<List<Integer>, Publisher<Integer>>() {
                    @Override
                    public Publisher<Integer> apply(List<Integer> integers) throws Exception {
                        return Flowable.fromIterable(integers);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });
        Flowable
                .just(list)
                .flatMap(new Function<List<Integer>, Publisher<Integer>>() {
                    @Override
                    public Publisher<Integer> apply(List<Integer> integers) throws Exception {
                        return Flowable.fromIterable(integers);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("MyTest１.accept"+integer);
                    }
                });
        Flowable.fromIterable(list).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("MyTest１.accept" + integer.toString());
            }
        });
    }
}
