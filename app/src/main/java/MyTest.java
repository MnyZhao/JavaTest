

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Crate by E470PD on 2018/8/13
 */
public class MyTest {
    public static String TAG = "TAG";

    public static void main(String args[]) {
//        easyRx();
        Rx();
//        obseverable();
//        connection();
        Observable.interval(1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println(disposable.isDisposed());
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("MyTest.onNext");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("MyTest.onError");
            }

            @Override
            public void onComplete() {
                System.out.println("MyTest.onComplete");
            }
        });
    }

    public static void connection() {
        Observable.just("链式调用").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("MyTest.accept" + s);
            }
        });
    }

    public static void connectio1n() {
        Observable.just("链式调用").subscribe();
    }

    public static void obseverable() {
        Observable<String> observable1 = Observable.just("helloWorld");
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("MyTest.accept" + s);
            }
        };
        observable1.subscribe(consumer);

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                observableEmitter.onNext("hello world");
                observableEmitter.onComplete();
            }
        });
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
//                disposable.dispose();
            }

            @Override
            public void onNext(String s) {
                System.out.println("MyTest.onNext" + s);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.println("MyTest.onComplete");
            }
        };
//        observable.subscribe(observer);

    }

    public static void easyRx() {
        //类似被观察者？
        Flowable<String> flowable1 = Flowable.just("helloworld");
        //创建一个消费者
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("MyTest.accept:" + s);
            }
        };
        flowable1.subscribe(consumer);
    }

    public static void Rx() {
        // create a flowable
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("hello RxJava 2");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {//调用订阅请求？
                System.out.println("MyTest.onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {//发送数据通知
                System.out.println("MyTest.onNext" + ":" + s);
            }

            @Override
            public void onError(Throwable t) {//失败 即使再次调用也不会发送其他事件

            }

            @Override
            public void onComplete() {//成功 即使再次调用也不会发送其他事件
                System.out.println("MyTest.onComplete");
            }
        };
        flowable.subscribe(subscriber);
    }
}
