package com.example.test.testthread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ExecutorService mSingleExecutor = null;
    private static final long SLEEP_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				Toast.makeText(MainActivity.this, "早上好，么么哒，我爱你", Toast.LENGTH_LONG).show();
//			}
//		}).start();
//        initSingleThreadExecutor();
//        initCachedThreadPool();
//        initFixedThreadPool();
        initScheduledThreadPool();
    }

    private void initScheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//        for(int i=0;i<11;i++) {
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("y延迟3秒执行，我要变成一个大富婆，包养全天下的小鲜肉");
            }
        }, 3, TimeUnit.SECONDS);
//        }

//        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("延迟一秒后，每3秒执行一次");
//            }
//        }, 1, 3, TimeUnit.SECONDS);
    }

    private void initFixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.e("你是那个对的人吗", String.valueOf(index));
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void initCachedThreadPool() {
        ExecutorService cachedThreadpool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;

            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadpool.execute(new Runnable() {
                @Override
                public void run() {
                    Log.e("老鼠爱大米", String.valueOf(index));
                }
            });
        }
    }

    private void initSingleThreadExecutor() {
        //实例化单一线程池
        mSingleExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            mSingleExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.e("你好，思密达", String.valueOf(index));
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
