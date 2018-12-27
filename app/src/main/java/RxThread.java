import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Crate by E470PD on 2018/8/15
 */
public class RxThread {
    public static void main(String args[]) {
        System.out.println("RxThread.main:" + Thread.currentThread().getName());

        createThread();
    }

    public static void createThread() {

    }
}
