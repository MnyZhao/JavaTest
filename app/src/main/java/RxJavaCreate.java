import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.content.ContentValues.TAG;

/**
 * 基本的被观察者 观察者 消息订阅的体现
 */
public class RxJavaCreate {
    /*rxjava创建*/
    public static void main(String args[]) {
        createRx();
    }

    static Disposable disposable;

    /*简单的被观察者 观察者 订阅 关系的体现*/
    private static void createRx() {
        /*创建被观察者*/
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //ObservableEmitter 发射器用来发送消息
                //通过调用onNext onError onComplete 来发送事件
                //上游 observable 可以无限发送onNext事件
                //下游obsever 可以无限接受onNext事件
                //当上游发送了onComplete 事件后不会中断上游的事件发射 下游接收到oncomplete后不再接收事件
                //当上游发送了onError 事件后不会中断上游的事件发射 下游接收到oncomplete后不再接收事件
                //不可同时发送多个onError onComplete
                //onError 与onComplete 互斥 不能先发送onError在发送onComplete 反过来也是一样
                /*注: 关于onComplete和onError唯一并且互斥这一点,  是需要自行在代码中进行控制,
                    如果你的代码逻辑中违背了这个规则, 并不一定会导致程序崩溃.  比如发送多个onComplete
                    是可以正常运行的, 依然是收到第一个onComplete就不再接收了,
                    但若是发送多个onError, 则收到第二个onError事件会导致程序会崩溃.*/
                System.out.println("Emitter:" + "1");
                e.onNext("1");
                System.out.println("Emitter:" + "2");
                e.onNext("2");
                System.out.println("Emitter:" + "3");
                e.onNext("3");
//                e.onError(new Throwable());
                System.out.println("Emitter:" + "4");
                e.onNext("4");
                e.onComplete();
                System.out.println("Emitter:" + "5");
                e.onNext("5");
            }
        });

        /*创建观察者*/
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //上游observable 与下游observer 通过onsubscribe链接
                //disposable调用dispose()会中断二者之间的联系但并不会导致上游不再继续发送事件,
                //上游会继续发送剩余的事件. 导致下游不再接收事件
                disposable = d;
                System.out.println("RxJavaCreate.onSubscribe");
            }

            @Override
            public void onNext(String value) {
                if (value.equals("4")) {
                    //调用dispose()并不会导致上游不再继续发送事件,
                    // 上游会继续发送剩余的事件. 下游不再接收事件
                    disposable.dispose();
                }
                System.out.println("RxJavaCreate.onNext:disposable" + disposable.isDisposed());
                System.out.println("RxJavaCreate.onNext:" + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("RxJavaCreate.onError" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        /*订阅事件*/
        observable.subscribe(observer);
    }
}
