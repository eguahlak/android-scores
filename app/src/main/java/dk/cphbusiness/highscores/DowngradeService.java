package dk.cphbusiness.highscores;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class DowngradeService extends Service {
    private static final String LOG_TAG = "DowngradeService";
    private Runner runner;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
        }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "Downgrade service created");
        }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d(LOG_TAG, "Downgrade service starting...");
        new Thread(runner = new Runner()).start();
        // new Runner().run();
        Log.d(LOG_TAG, "...done");
        return START_STICKY;
        }

    @Override
    public void onDestroy() {
        runner.stop();
        Log.d(LOG_TAG, "Downgrade service destroyed");
        super.onDestroy();
        }

    private static final class Runner implements Runnable {
        private static boolean running = false;

        private void log(String text) {
            Log.d(LOG_TAG, text);
            }

        @Override
        public synchronized void run() {
            if (running) return;
            running = true;
            try {
                int i = 1;
                while (running) {
                    log("Still running, loop #"+i++);
                    Thread.sleep(2000);
                    }
//                for (int i = 0; i < 10; i++) {
//                    log("This is number " + i);
//                    Thread.sleep(100);
//                    }
                }
            catch (InterruptedException ex) {
                Log.e(LOG_TAG, "Interrupted", ex);
                }
            finally {
                running = false;
                }
            }

        public void stop() {
            running = false;
            }

        }

    }
