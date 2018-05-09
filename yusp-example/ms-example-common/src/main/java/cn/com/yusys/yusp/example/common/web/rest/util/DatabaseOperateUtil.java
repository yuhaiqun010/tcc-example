package cn.com.yusys.yusp.example.common.web.rest.util;

/**
 * 记录数据操作类型
 */
public class DatabaseOperateUtil {
    private static final String[] QUERY = {"find", "get", "search", "query"};
    private static final String[] INSERT = {"insert", "save", "create"};
    private static final String[] UPDATE = {"update", "alter", "modify"};
    private static final String[] DELETE = {"delete", "remove"};

    public static boolean isQuery(String methodName) {
        for (String s : QUERY) {
            if (methodName.contains(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInsert(String methodName) {
        for (String s : INSERT) {
            if (methodName.contains(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUpdate(String methodName) {
        for (String s : UPDATE) {
            if (methodName.contains(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDelete(String methodName) {
        for (String s : DELETE) {
            if (methodName.contains(s)) {
                return true;
            }
        }
        return false;
    }

    public static String getOperateType(String s) {
        return isQuery(s) ? "query" : isDelete(s) ? "delete" : isInsert(s) ? "insert" : isUpdate(s) ? "update" : "unknown";
    }
}
