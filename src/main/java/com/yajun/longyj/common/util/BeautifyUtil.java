package com.yajun.longyj.common.util;

public class BeautifyUtil {

	/**
	 * 打印输入到控制台
	 * @Title: printJson
	 * @Description: 
	 * @param jsonStr void
	 * @author  LONGYAJUN_LYJ@163.com
	 * @date 2018年3月14日 下午12:02:17
	 */
    public static void printJson(String jsonStr) {
        System.out.println(formatJson(jsonStr));
    }

    /**
     * 格式化
     * @Title: formatJson
     * @Description: 
     * @param jsonStr
     * @return String
     * @author  LONGYAJUN_LYJ@163.com
     * @date 2018年3月14日 下午12:02:06
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr))
            return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
            case '"':
                                if (last != '\\'){
                    isInQuotationMarks = !isInQuotationMarks;
                                }
                sb.append(current);
                break;
            case '{':
            case '[':
                sb.append(current);
                if (!isInQuotationMarks) {
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                }
                break;
            case '}':
            case ']':
                if (!isInQuotationMarks) {
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                }
                sb.append(current);
                break;
            case ',':
                sb.append(current);
                if (last != '\\' && !isInQuotationMarks) {
                    sb.append('\n');
                    addIndentBlank(sb, indent);
                }
                break;
            default:
                sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加
     * @Title: addIndentBlank
     * @Description: 
     * @param sb
     * @param indent void
     * @author  LONGYAJUN_LYJ@163.com
     * @date 2018年3月14日 下午12:01:56
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}
