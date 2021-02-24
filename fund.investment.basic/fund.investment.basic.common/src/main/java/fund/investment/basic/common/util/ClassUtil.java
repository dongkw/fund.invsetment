package fund.investment.basic.common.util;

public final class ClassUtil {

	public static Class<?> instance(String qualifiedName) {
		try {
			return Class.forName(qualifiedName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}