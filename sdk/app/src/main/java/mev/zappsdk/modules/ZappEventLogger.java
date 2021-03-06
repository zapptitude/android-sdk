package mev.zappsdk.modules;

import android.util.Log;

import java.util.HashMap;

import mev.loggersdk.modules.Logger;

/**
 * Created by andrew on 24.03.16.
 */
public class ZappEventLogger {

    //region Constants

    public static final String Z_EVENT_KEY = "ZEvent";
    public static final String Z_BEGIN_TASK_KEY = "ZBeginTask";
    public static final String Z_SOLVE_TASK_KEY = "ZSolveTask";

    public static final String EVENT_KEY = "event";
    public static final String TASK_KEY = "task";
    public static final String CONTEXT_KEY = "context";
    public static final String TYPE_KEY = "type";
    public static final String EXPECTED_KEY = "expected";
    public static final String TOPICS_KEY = "topics";
    public static final String ACTUAL_KEY = "actual";
    public static final String AMONG_KEY = "among";
    public static final String BINARY_KEY = "binary";
    public static final String INT_KEY = "int";
    public static final String FLOAT_KEY = "float";
    public static final String MC_KEY = "mc";
    public static final String GRAD_KEY = "grad";

    //endregion

    //region Singleton
    private static ZappEventLogger instance;

    public static synchronized ZappEventLogger getInstance() {
        return instance != null ? instance : (instance = new ZappEventLogger());
    }
    //endregion

    //region General methods

    public void logEvent(String event, HashMap<String, String> info)
    {
        HashMap<String, String> loggedMap = new HashMap(info);

        loggedMap.put(EVENT_KEY, checkEmptyString(event));

        Logger.getInstance().addLogEvent(Z_EVENT_KEY, loggedMap);
    }

    public void logBeginTask(String task, String context, HashMap<String, String> info) {

        info.put(TASK_KEY, checkEmptyString(task));
        info.put(CONTEXT_KEY, checkEmptyString(context));

        Logger.getInstance().addLogEvent(Z_BEGIN_TASK_KEY, info);
    }

    public void logSolveBinaryTask(String task, String context, String topics, boolean expected, boolean actual, HashMap<String, String> info) {

        info.put(TYPE_KEY, BINARY_KEY);
        info.put(EXPECTED_KEY, String.valueOf(expected));
        info.put(ACTUAL_KEY, String.valueOf(actual));
        info.put(TASK_KEY, checkEmptyString(task));
        info.put(CONTEXT_KEY, checkEmptyString(context));
        info.put(TOPICS_KEY, checkEmptyString(topics));

        Logger.getInstance().addLogEvent(Z_SOLVE_TASK_KEY, info);
        ZappInternal.getInstance().resetTaskStartTime();
    }

    public void logSolveIntTask(String task, String context, String topics, int expected, int actual, HashMap<String, String> info) {

        info.put(TYPE_KEY, INT_KEY);
        info.put(EXPECTED_KEY, String.valueOf(expected));
        info.put(ACTUAL_KEY, String.valueOf(actual));
        info.put(TASK_KEY, checkEmptyString(task));
        info.put(CONTEXT_KEY, checkEmptyString(context));
        info.put(TOPICS_KEY, checkEmptyString(topics));

        Logger.getInstance().addLogEvent(Z_SOLVE_TASK_KEY, info);
        ZappInternal.getInstance().resetTaskStartTime();
    }

    public void logSolveFloatTask(String task, String context, String topics, float expected, float actual, HashMap<String, String> info) {

        info.put(TYPE_KEY, FLOAT_KEY);
        info.put(EXPECTED_KEY, String.format("%.4f", expected));
        info.put(ACTUAL_KEY, String.format("%.4f", actual));
        info.put(TASK_KEY, checkEmptyString(task));
        info.put(CONTEXT_KEY, checkEmptyString(context));
        info.put(TOPICS_KEY, checkEmptyString(topics));

        Logger.getInstance().addLogEvent(Z_SOLVE_TASK_KEY, info);
        ZappInternal.getInstance().resetTaskStartTime();
    }

    public void logSolveMCTask(String task, String context, String topics, char expected, char actual, int among, HashMap<String, String> info) {

        info.put(TYPE_KEY, MC_KEY);
        info.put(EXPECTED_KEY, String.valueOf(expected));
        info.put(ACTUAL_KEY, String.valueOf(actual));
        info.put(AMONG_KEY, String.valueOf(among));

        info.put(TASK_KEY, checkEmptyString(task));
        info.put(CONTEXT_KEY, checkEmptyString(context));
        info.put(TOPICS_KEY, checkEmptyString(topics));

        Logger.getInstance().addLogEvent(Z_SOLVE_TASK_KEY, info);
        ZappInternal.getInstance().resetTaskStartTime();
    }

    public void logSolveGradTask(String task, String context, String topics, int expected, int actual, int among, HashMap<String, String> info) {

        info.put(TYPE_KEY, GRAD_KEY);
        info.put(EXPECTED_KEY, String.valueOf(expected));
        info.put(ACTUAL_KEY, String.valueOf(actual));
        info.put(AMONG_KEY, String.valueOf(among));

        info.put(TASK_KEY, checkEmptyString(task));
        info.put(CONTEXT_KEY, checkEmptyString(context));
        info.put(TOPICS_KEY, checkEmptyString(topics));

        Logger.getInstance().addLogEvent(Z_SOLVE_TASK_KEY, info);
        ZappInternal.getInstance().resetTaskStartTime();
    }

    public String checkEmptyString(String string)
    {
        return !string.isEmpty() ? string : null;
    }

    //endregion

}
