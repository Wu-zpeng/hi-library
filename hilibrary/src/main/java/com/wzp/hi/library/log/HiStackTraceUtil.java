package com.wzp.hi.library.log;

/**
 * 堆栈信息工具类
 */
public class HiStackTraceUtil {

    /**
     *  对堆栈信息进行裁剪
     * @param callStack     堆栈信息
     * @param maxDepth      最大的长度
     * @return
     */
    private static StackTraceElement[] cropStackTrace(StackTraceElement[] callStack, int maxDepth){
        //获取堆栈信息的长度
        int realDepth = callStack.length;
        if (maxDepth > 0) {
            realDepth = Math.min(maxDepth, realDepth);
        }
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(callStack, 0, realStack, 0, realDepth);
        return realStack;
    }


    /**
     * 获取除忽略包之外的堆栈信息
     * @param stackTrace        堆栈信息
     * @param ignorePackage     忽略的包名
     * @return
     */
    private static StackTraceElement[] getRealStackTrack(StackTraceElement[] stackTrace, String ignorePackage){
        //忽略的长度是0
        int ignoreDepth = 0;
        //获取堆栈信息的长度
        int allDepth = stackTrace.length;

        String className;
        for (int i = allDepth - 1; i >= 0; i--) {
            className = stackTrace[i].getClassName();
            if (ignorePackage != null && className.startsWith(ignorePackage)) {
                ignoreDepth = i + 1;
                break;
            }
        }
        int realDepth = allDepth - ignoreDepth;
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(stackTrace, ignoreDepth, realStack, 0, realDepth);
        return realStack;
    }

    /**
     * 获取裁剪过后真实的堆栈信息
     * @param stackTrace
     * @param ignorePackage
     * @param maxDepth
     * @return
     */
    public static StackTraceElement[] getCroppedRealStackTrace(StackTraceElement[] stackTrace, String ignorePackage, int maxDepth){
        return cropStackTrace(getRealStackTrack(stackTrace, ignorePackage), maxDepth);
    }


}
